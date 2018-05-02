package com.jt.cart.service;

import java.util.List;

import com.jt.cart.pojo.Cart;

public interface CartService {

	/**
	 * 查询购物车商品
	 * @param userId
	 * @return
	 */
	List<Cart> findCartByUserId(Long userId);

	/**
	 * 修改商品数量
	 * @param cart
	 */
	void updateCartNum(Cart cart);

	/**
	 * 增加商品
	 * @param cart
	 */
	void saveCart(Cart cart);

	/**
	 * 删除商品
	 * @param cart
	 */
	void deleteCart(Cart cart);

}