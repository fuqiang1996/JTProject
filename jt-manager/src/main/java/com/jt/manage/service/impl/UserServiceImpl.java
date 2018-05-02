package com.jt.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.manage.mapper.UserMapper;
import com.jt.manage.pojo.User;
import com.jt.manage.service.UserService;
/***
 * 用户业务处理类
 * @author 富强
 *
 */
@Service
public class UserServiceImpl implements UserService {
	/**user映射*/
	@Autowired
	private UserMapper userMapper;
	@Override
	public List<User> findUser() {
		return userMapper.findAll();
	}

}
