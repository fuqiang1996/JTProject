package com.jt.web.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.SysResult;
import com.jt.web.pojo.Cart;
import com.jt.web.service.CartService;
import com.jt.web.util.UserThreadLocal;

/**
 * 购物车控制层
 * @author 富强
 *
 */
@Controller
@RequestMapping("/cart/")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	/**
	 * 跳转到购物车
	 * @param model
	 * @return
	 */
	@RequestMapping("show")
	public String toCart(Model model){
		Long userId = UserThreadLocal.getUser().getId();
		List<Cart> carts = cartService.findCartByUserId(userId);
		model.addAttribute("cartList", carts);
		return "cart";
	}
	
	@RequestMapping("update/num/{itemId}/{num}")
	@ResponseBody
	public SysResult updateCartNum(@PathVariable("itemId") Long itemId,@PathVariable("num") Integer num){
		Long userId = UserThreadLocal.getUser().getId();
		Cart cart = new Cart();
		cart.setCreated(new Date());
		cart.setUserId(userId);
		cart.setItemId(itemId);
		cart.setNum(num);
		cart.setUpdated(cart.getCreated());
		try {
			cartService.updateCartNum(cart);
			return SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.build(201, "修改失败");
		}
	}
	
	/**
	 * 商品添加到购物车
	 */
	@RequestMapping("add/{itemId}")
	public String saveCart(@PathVariable("itemId") Long itemId,Cart cart){
		Long userId = UserThreadLocal.getUser().getId();
		cart.setUserId(userId);
		cart.setItemId(itemId);
		cartService.saveCart(cart);
		//跳转到购物车展示页面
		return "redirect:/cart/show.html";
	}
	
	/**
	 * 删除商品
	 */
	@RequestMapping("delete/{itemId}")
	public String deleteCart(@PathVariable("itemId")Long itemId){
		Long userId = UserThreadLocal.getUser().getId();
		cartService.deleteCart(userId,itemId);
		return "redirect:/cart/show.html";
	}
}
