package com.jt.manage.service;

import java.util.List;

import com.jt.manage.pojo.User;
/**
 * 业务接口
 * @author 富强
 *
 */
public interface UserService {
	/**
	 * 查询所有用户
	 * @return
	 */
	List<User> findUser();
}
