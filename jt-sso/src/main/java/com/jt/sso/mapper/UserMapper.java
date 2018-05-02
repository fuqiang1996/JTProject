package com.jt.sso.mapper;

import org.apache.ibatis.annotations.Param;

import com.jt.common.mapper.SysMapper;
import com.jt.sso.pojo.User;

/**
 * user映射
 * @author 富强
 *
 */
public interface UserMapper extends SysMapper<User>{

	/**
	 * 根据传来的值检测名称是否存在
	 * @param param
	 * @param column
	 * @return
	 */
	int findCheckUser(@Param("param")String param,@Param("column")String column);
	
	/**
	 * 根据用户名和密码查询信息
	 * @param username
	 * @param passwordMd5
	 * @return
	 */
	User selectUserByUP(@Param("username")String username, @Param("password")String passwordMd5);
	
}
