package com.jt.web.service;

import com.jt.web.pojo.User;

/**
 * 前台业务接口类
 * @author 富强
 *
 */
public interface UserService {

	/**
	 * 注册用户
	 * @param user
	 * @return
	 */
	String saveUser(User user);

	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	String findUserByUP(User user);
	
}
