package com.jt.manage.mapper;

import java.util.List;

import com.jt.manage.pojo.User;
/**
 * user映射
 * @author 富强
 *
 */
public interface UserMapper {
	/**
	 * 查找所有用户
	 */
	List<User> findAll();
}
