package com.jt.manage.service;


import com.jt.common.vo.EasyUIResult;
import com.jt.manage.pojo.Item;
import com.jt.manage.pojo.ItemDesc;

/**
 * 业务接口
 * @author 富强
 *
 */
public interface ItemService {
	
	/**
	 * 分页查询商品
	 */
	EasyUIResult findItemByPage(Integer page,Integer rows);

	/**
	 * 根据商品I分类D查询商品分类名称
	 */
	
	String findItemCatById(Long itemCatId);
	
	/**
	 * 保存商品
	 * @param item
	 */
	void saveItem(Item item,String desc);
	
	/**
	 * 批量删除商品
	 * @param id
	 */
	void deleteItem(Long[] ids);

	/**
	 * 更新商品
	 * @param item
	 */
	void updateItem(Item item,String desc);

	/**
	 * 改变商品状态
	 * @param status
	 * @param ids
	 */
	void updateStatus(int status, Long[] ids);

	/**
	 * 查询商品描述信息
	 * @param id
	 * @return
	 */
	ItemDesc findItemDescById(Long itemId);
	/**
	 * 根据前台传过来的ID查询商品信息
	 * @param itemId
	 * @return
	 */
	Item findItemById(Long itemId);
}
