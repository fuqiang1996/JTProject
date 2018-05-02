package com.jt.sso.service;

import com.jt.sso.pojo.User;

/**
 * 单点登录业务接口
 * @author 富强
 *
 */
public interface UserService {

	/**
	 * 通过参数验证User名称是否已经存在
	 * @param param
	 * @param type
	 * @return
	 */
	Boolean findCheckUser(String param, int type);

	/**
	 * 注册用户
	 * @param user
	 * @return
	 */
	String saveUser(User user);

	/**
	 * 登录用户
	 * @param username
	 * @param password
	 * @return
	 */
	String findUserByPU(String username, String password);

	/**
	 * 回显用户信息
	 * @param ticket
	 * @return
	 */
	String queryUserData(String ticket);

}
