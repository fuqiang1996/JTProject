package com.jt.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.HttpClientService;
import com.jt.common.vo.SysResult;
import com.jt.web.pojo.Cart;
import com.jt.web.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private HttpClientService httpClientService;
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	public List<Cart> findCartByUserId(Long userId) {
		//定义uri跨域查询路径
		String uri = "http://cart.jt.com/cart/query/"+userId;
		try {
			String jsonResult = httpClientService.doGet(uri);
			SysResult result = objectMapper.readValue(jsonResult, SysResult.class);
			@SuppressWarnings("unchecked")
			List<Cart> cart = (List<Cart>) result.getData();
			return cart;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void updateCartNum(Cart cart) {
	
		String uri = "http://cart.jt.com/cart/update/num/"+cart.getUserId()+"/"+cart.getItemId()+"/"+cart.getNum();
		try {
			httpClientService.doGet(uri);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	@Override
	public void saveCart(Cart cart) {
		String uri = "http://cart.jt.com/cart/save";
		//封装数据
		Map<String, String> map = new HashMap<String,String>();
		map.put("userId",cart.getUserId()+"");
		map.put("itemId",cart.getItemId()+"");
		map.put("itemTitle",cart.getItemTitle());
		map.put("itemImage",cart.getItemImage());
		map.put("itemPrice",cart.getItemPrice()+"");
		map.put("num",cart.getNum()+"");
		
		try {
			httpClientService.doPost(uri,map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteCart(Long userId, Long itemId) {
		String uri = "http://cart.jt.com/cart/delete/"+userId+"/"+itemId;
		try {
			httpClientService.doGet(uri);			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
