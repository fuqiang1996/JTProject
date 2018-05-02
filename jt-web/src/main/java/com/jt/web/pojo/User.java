package com.jt.web.pojo;

import java.io.Serializable;


import com.jt.common.po.BasePojo;
//将对象和数据表tb_user一一映射
public class User extends BasePojo implements Serializable{
	/**
	 * 版本号
	 */
	private static final long serialVersionUID = -9179961615975879076L;
	private Long id;
	private String username;
	private String password;
	private String phone;
	private String email;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", phone=" + phone + ", email="
				+ email + "]";
	}
	
	
}
