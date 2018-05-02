package com.jt.manage.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.RedisService;
import com.jt.common.vo.ItemCatData;
import com.jt.common.vo.ItemCatResult;
import com.jt.manage.mapper.ItemCatMapper;
import com.jt.manage.pojo.ItemCat;
import com.jt.manage.service.ItemCatService;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

/**
 * 商品分类业务实现类
 * @author 富强
 *
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {
	
	@Autowired
	private ItemCatMapper itemCatMapper;

	private static ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	private JedisCluster jedisCluster;
	@Override
	public List<ItemCat> findItemCat(Long parentId) {
		
		//创建list集合 存储itemcat对象
		List<ItemCat> itemCatList = new ArrayList<>();
		//定义key  定义前缀保证key不重复
		String key = "ITEM_CAT_"+parentId;
		//从缓存中获取数据
		String jsonData = jedisCluster.get(key);
		
		try {
			//判断缓存是否为空
			if(StringUtils.isEmpty(jsonData)){
				//表示没有缓存  就查询数据库
				ItemCat itemCat = new ItemCat();
				itemCat.setParentId(parentId);
				itemCat.setStatus(1);
				itemCatList = itemCatMapper.select(itemCat);
				//将数据转化为JSON串
				String resultJSON = objectMapper.writeValueAsString(itemCatList);
				//将数据存入缓存中
				jedisCluster.set(key, resultJSON);
			}else{
				//表示redis已经缓存了此数据
				ItemCat[] itemCats = objectMapper.readValue(jsonData	, ItemCat[].class);
				//将返回值转化为list集合
				itemCatList = Arrays.asList(itemCats);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return itemCatList;
	}
	/*
	 * 分析:	由于商品的分类信息较多,如果按照1级查询2级查询3级这样的结构
	 * 会查询很多次,数据库压力较大
	 * 解决:	
	 * 	首先查询全部的商品分类信息.之后进行数据的整理
	 * 	Map<parentId,List<ItemCat> itemcatLists>
	 *  parentId 表示父级的Id
	 *  itemCatLists.表示的是当前父级Id下所有的子级菜单
	 *  
	 * 步骤:
	 * 	1.封装一级商品分类信息
	 *  2.封装二级商品分类信息
	 *  3.封装三级商品分类信息
	 * 
	 */
	@Override
	public ItemCatResult findItemCatAll() {
		//查询全部的商品分类信息
				ItemCat tempItemCat = new ItemCat();
				tempItemCat.setStatus(1); //查询正常的商品分类信息
				List<ItemCat> itemCatLists = 
						itemCatMapper.select(tempItemCat);
				
				//整理数据 一个parentId 下的全部的自己list集合
				Map<Long, List<ItemCat>> itemCatMap = 
						new HashMap<Long, List<ItemCat>>();
				
				for (ItemCat itemCat : itemCatLists) {
					if(itemCatMap.containsKey(itemCat.getParentId())){
						//表示已经含有parentId,做数据的追加操作
						itemCatMap.get(itemCat.getParentId()).add(itemCat);
					}else{
						//为父级创建List集合
						List<ItemCat> itemCatList = new ArrayList<ItemCat>();
						itemCatList.add(itemCat);
						itemCatMap.put(itemCat.getParentId(), itemCatList);
					}
				}
				
				//构建返回对象
				ItemCatResult itemCatResult = new ItemCatResult();
				
				//封装一级商品分类菜单
				List<ItemCatData> itemCatDataList = new ArrayList<ItemCatData>();
				
				for (ItemCat itemCat1 : itemCatMap.get(0L)) {
					
					//定义一级商品分类对象
					ItemCatData itemCatData1 = new ItemCatData();
					itemCatData1.setUrl("/products/"+ itemCat1.getId()+".html");
					itemCatData1.setName
					("<a href='"+itemCatData1.getUrl()+"'>"+itemCat1.getName()+"</a>");
					
					
					//封装商品分类的二级菜单
					List<ItemCatData> itemCatDataList2 = new ArrayList<ItemCatData>();
					for (ItemCat itemCat2 : itemCatMap.get(itemCat1.getId())) {
						
						ItemCatData itemCatData2 = new ItemCatData();
						itemCatData2.setUrl("/products/"+itemCat2.getId());
						itemCatData2.setName(itemCat2.getName());
						
						//准备三级商品分类菜单
						List<String> itemCatDataList3 = new ArrayList<String>();
						
						for (ItemCat itemCat3 : itemCatMap.get(itemCat2.getId())) {
							itemCatDataList3.add("/products/"+itemCat3.getId() + "|" +itemCat3.getName());
						}
						
						//将三级分类的集合注入到二级对象中
						itemCatData2.setItems(itemCatDataList3);
						itemCatDataList2.add(itemCatData2);
					}
					itemCatData1.setItems(itemCatDataList2);
					itemCatDataList.add(itemCatData1);
					
					//封装参数 多余的直接跳出
					if(itemCatDataList.size() >13)
					break;
				}
			
				//封装一级商品分类的菜单
				itemCatResult.setItemCats(itemCatDataList);
				
				return itemCatResult;
	}
	@Override
	public ItemCatResult findCacheItemCatAll() {
		//使用redis缓存中的key
		String key = "ITEM_CAT_ALL";
		String jsonData = jedisCluster.get(key);
		try {
			//判断jsonData是否为空 也就是是否有缓存
			if(StringUtils.isEmpty(jsonData)){//缓存为空
				//调用方法查询所有商品信息
				ItemCatResult itemCatResult = findItemCatAll();
				//把对象转换成JSON格式
				String jsonResult = objectMapper.writeValueAsString(itemCatResult);
				//存储到redis
				jedisCluster.set(key, jsonResult);
				return itemCatResult;
			}else{   //表示已经存在缓存
				ItemCatResult itemCatResult = objectMapper.readValue(jsonData, ItemCatResult.class);
				return itemCatResult;
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
