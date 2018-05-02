package com.jt.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jt.manage.pojo.User;
import com.jt.manage.service.UserService;

/**
 * 控制层
 * @author 富强
 *
 */
@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("findAll")
	private String findAll(Model model){
		List<User> user = userService.findUser();
		//将user存入到request域中
		model.addAttribute("userList", user);
		//返回页面
		return "userList";
	}
}
