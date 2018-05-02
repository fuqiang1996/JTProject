package com.jt.manage.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jt.common.vo.EasyUIResult;
import com.jt.manage.mapper.ItemCatMapper;
import com.jt.manage.mapper.ItemDescMapper;
import com.jt.manage.mapper.ItemMapper;
import com.jt.manage.pojo.Item;
import com.jt.manage.pojo.ItemDesc;
import com.jt.manage.service.ItemService;
/**
 * 业务实现类
 * @author 富强
 *
 */

import redis.clients.jedis.JedisCluster;
@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private ItemCatMapper itemCatMapper;
	@Autowired
	private ItemDescMapper itemDescMapper;
	@Autowired
	private JedisCluster jedisCluster;
	@Value("${keyItem}")
	private String key;
	/**
	 * 分页查询商品
	 */
	@Override
	public EasyUIResult findItemByPage(Integer page, Integer rows) {
		//检测参数的合法性
		
		//查询总记录数
		int total = itemMapper.findCount();
		
		//分页查询开始行数
		int startIndex = (page-1)*rows;
		
		//查询
		List<Item> list = itemMapper.findItemByPage(startIndex, rows);
		return new EasyUIResult(total,list);
	}
	@Override
	public String findItemCatById(Long itemCatId) {
		String nameById = itemCatMapper.findItemNameById(itemCatId);
		return nameById;
	}
	@Override
	public void saveItem(Item item,String desc) {
		item.setStatus(1); //表示启用
		item.setCreated(new Date());
		item.setUpdated(item.getCreated());
		//利用通用mapper 插入数据
		itemMapper.insert(item);
		
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(item.getId());
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		itemDescMapper.insert(itemDesc);
		
	}
	@Override
	public void deleteItem(Long[] ids) {
		/**
		 * 删除操作时
		 * 一般遵循先主后从
		 */
		itemMapper.deleteByIDS(ids);
		itemDescMapper.deleteByIDS(ids);
		
		//删除操作 删除原有缓存
		for (Long id : ids) {
			jedisCluster.del(key+id);
		}
	}
	@Override
	public void updateItem(Item item,String desc) {
		//更新商品表
		item.setUpdated(new Date());
		itemMapper.updateByPrimaryKeySelective(item);
		//更新商品描述表
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(item.getId());
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(item.getCreated());
		itemDesc.setUpdated(item.getUpdated());
		itemDescMapper.updateByPrimaryKeySelective(itemDesc);
		//更新操作删除原有缓存
		jedisCluster.del(key+item.getId());
	}
	@Override
	public void updateStatus(int status, Long[] ids) {
		itemMapper.updateStatus(status,ids);
	}
	@Override
	public ItemDesc findItemDescById(Long itemId) {
		
		return itemDescMapper.selectByPrimaryKey(itemId);
	}
	@Override
	public Item findItemById(Long itemId) {
		Item item = itemMapper.selectByPrimaryKey(itemId);
		return item;
	}

}
