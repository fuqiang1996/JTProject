package com.jt.web.service;

import com.jt.web.pojo.Item;
/**
 * 商品业务接口类
 * @author 富强
 *
 */
public interface ItemService {
	/**
	 * 根据itemID查询商品信息
	 * @param itemId
	 * @return
	 */
	Item findItemByid(Long itemId);

}
