package com.jt.web.util;

import com.jt.web.pojo.User;

/**
 * 本地线程工具类
 * @author 富强
 *
 */
public class UserThreadLocal {
	//创建本地变量
	private static ThreadLocal<User> threadLocal = new ThreadLocal<>();
	
	public static User getUser(){
		return threadLocal.get();
	}

	public static void setUser(User user){
		threadLocal.set(user);
	}
	
}
