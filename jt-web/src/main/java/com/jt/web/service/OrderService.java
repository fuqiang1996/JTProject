package com.jt.web.service;

import com.jt.web.pojo.Order;

public interface OrderService {

	/**
	 * 保存定单
	 * @param order
	 * @return
	 */
	String saveOrder(Order order);

	/**
	 * 根据ID查询订单
	 * @param id
	 * @return
	 */
	Order findOrderById(String id);

}
