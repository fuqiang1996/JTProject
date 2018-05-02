package com.jt.web.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.HttpClientService;
import com.jt.common.vo.SysResult;
import com.jt.web.pojo.User;
import com.jt.web.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private HttpClientService httpClientService;
	
	private static ObjectMapper objectMapper = new ObjectMapper();
	//跨域调用
	@Override
	public String saveUser(User user) {
		String uri = "http://sso.jt.com/user/register";
		Map<String,String> map = new HashMap<>();
		map.put("username", user.getUsername());
		map.put("password", user.getPassword());
		map.put("phone", user.getPhone());
		map.put("email", user.getEmail());
		
		try {
			String jsonResult = httpClientService.doGet(uri, map);
			SysResult sysResult = objectMapper.readValue(jsonResult, SysResult.class);
			return (String) sysResult.getData();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public String findUserByUP(User user) {
		String uri = "http://sso.jt.com/user/login";
		Map<String ,String> map = new HashMap<>();
		map.put("u", user.getUsername());
		map.put("p", user.getPassword());
		try {
			String jsonData = httpClientService.doPost(uri, map);
			SysResult sysResult = objectMapper.readValue(jsonData, SysResult.class);
			String ticket = (String) sysResult.getData();
			return ticket;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
