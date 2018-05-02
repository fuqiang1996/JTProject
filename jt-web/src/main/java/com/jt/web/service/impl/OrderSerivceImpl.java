package com.jt.web.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.HttpClientService;
import com.jt.common.vo.SysResult;
import com.jt.web.pojo.Order;
import com.jt.web.service.OrderService;
@Service
public class OrderSerivceImpl implements OrderService {

	@Autowired
	private HttpClientService httpClientService;
	private static ObjectMapper objectMapper = new ObjectMapper();
	@Override
	public String saveOrder(Order order) {
		String uri = "http://order.jt.com/order/create";
		try {
			String orderJSON = objectMapper.writeValueAsString(order);
			Map<String,String> map = new HashMap<>();
			map.put("orderJSON", orderJSON);
			String resultJSON = httpClientService.doPost(uri, map);
			SysResult sysResult = objectMapper.readValue(resultJSON, SysResult.class);
			//判断后台调用是否正确
			if(sysResult.getStatus() == 200){
				String orderId = (String) sysResult.getData();
				return orderId;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public Order findOrderById(String id) {
		String uri = "http://order.jt.com/order/query/" + id;
		try {
			System.out.println("id:"+id);
			String orderJSON = httpClientService.doGet(uri);
			System.out.println("orderJSON:"+orderJSON);
			if(!StringUtils.isEmpty(orderJSON)){
				Order order = objectMapper.readValue(orderJSON, Order.class);
				return order;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
