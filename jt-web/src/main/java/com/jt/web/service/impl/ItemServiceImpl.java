package com.jt.web.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.HttpClientService;
import com.jt.web.pojo.Item;
import com.jt.web.service.ItemService;

import redis.clients.jedis.JedisCluster;

@Service
public class ItemServiceImpl implements ItemService {

	
	@Autowired
	private JedisCluster jedisCluster;
	@Value("${keyItem}")
	private String key;
	@Autowired
	private HttpClientService httpClientService;
	private  static ObjectMapper objectMapper = new ObjectMapper();
	/**
	 * 经过京淘前台的业务层，去访问后台的业务代码?
	 * 解决策略:跨域
	 * 问题:在业务层中不能采用JSONP的形式进行跨域调用
	 * 解决:采用HttpClient方式进行调用
	 * 
	 */
	@Override
	public Item findItemByid(Long itemId) {

		key = key+itemId;
		System.out.println("key:"+key);
		//添加缓存处理
		String resultData =  jedisCluster.get(key);
		try {

			if(StringUtils.isEmpty(resultData)){
				//进行跨域访问数据
				String uri = "http://manage.jt.com/web/item/findItemById/"+itemId;
				String jsonData = httpClientService.doGet(uri);
				System.out.println("jsonData:"+jsonData);
				if(!StringUtils.isEmpty(jsonData)){
					//需要将JSON串转化为Item对象
					Item item = objectMapper.readValue(jsonData, Item.class);
					//将数据写入redis中
					String itemJSON = objectMapper.writeValueAsString(item);
					jedisCluster.set(key, itemJSON);
					return item;
				}
			}else{
				Item item = objectMapper.readValue(resultData, Item.class);
				return item;	
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
