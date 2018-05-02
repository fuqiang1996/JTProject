package com.jt.cart.mapper;

import com.jt.cart.pojo.Cart;
import com.jt.common.mapper.SysMapper;

public interface CartMapper extends SysMapper<Cart>{

	/**
	 * 修改商品数量
	 * @param cart
	 */
	void updateCartNum(Cart cart);

}
