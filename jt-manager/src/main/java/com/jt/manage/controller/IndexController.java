package com.jt.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 初始化界面
 * @author 富强
 *
 */
@RequestMapping("/")
@Controller
public class IndexController {
	/**
	 * 跳转到主页
	 */
	@RequestMapping("index")
	public String index(){
		
		return "index";
	}
	
	/**
	 * 跳转新增/查询/编辑页面
	 * 'url':'/page/item-list'
	 */
	@RequestMapping("page/{module}")
	public String pageUI(@PathVariable String module){
		
		return module;
	}
}
