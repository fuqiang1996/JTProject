package com.jt.web.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.SysResult;
import com.jt.web.pojo.Cart;
import com.jt.web.pojo.Order;
import com.jt.web.service.CartService;
import com.jt.web.service.OrderService;
import com.jt.web.util.UserThreadLocal;

@Controller
@RequestMapping("/order/")
public class OrderController {

	@Autowired
	private CartService cartService;
	@Autowired
	private OrderService orderService;
	
	/**
	 * 1.需要跳转到订单的确认页面
	 * 2.准备购物车信息
	 * 		CartService 查询购物车信息
	 * 3.将信息会传到页面中 
	 * 4.将页面返回
	 * @return
	 */
	@RequestMapping("create")
	public String toCreate(Model model){
		Long userId = UserThreadLocal.getUser().getId();
		List<Cart> carts = cartService.findCartByUserId(userId);
		model.addAttribute("carts", carts);
		return "order-cart";
	}
	
	@RequestMapping("submit")
	@ResponseBody
	public SysResult saveOrder(Order order){
		Long userId = UserThreadLocal.getUser().getId();
		order.setUserId(userId);
		try {
			String orderId = orderService.saveOrder(order);
			if(!StringUtils.isEmpty(orderId)){
				return SysResult.oK(orderId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "新增订单失败");
	}
	/**
	 * 订单查询
	 * 三张表一起查询
	 * @param id
	 * @return
	 */
	
	@RequestMapping("success")
	public String findOrderById(String id,Model model){
		System.out.println("前台接收ID:"+id);
		Order order = orderService.findOrderById(id);
		model.addAttribute("order", order);
		return "success";
	}
	
	@RequestMapping("myOrder")
	public String myOrder(){
		return "redirect:/cart/show.html";
	}
}
