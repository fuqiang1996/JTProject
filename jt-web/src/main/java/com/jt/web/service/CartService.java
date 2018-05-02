package com.jt.web.service;

import java.util.List;

import com.jt.web.pojo.Cart;

public interface CartService {

	/**
	 * 根据用户ID查询商品购物车
	 * @param userId
	 * @return
	 */
	List<Cart> findCartByUserId(Long userId);

	/**
	 * 更新商品数量
	 */
	void updateCartNum(Cart cart);

	/**
	 * 保存商品到购物车
	 * @param cart
	 */
	void saveCart(Cart cart);

	/**
	 * 删除商品
	 * @param userId
	 * @param itemId
	 */
	void deleteCart(Long userId, Long itemId);

}
