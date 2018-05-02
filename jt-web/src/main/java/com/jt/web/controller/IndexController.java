package com.jt.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 京淘前台首页展示
 * @author 富强
 *
 */
@Controller
@RequestMapping("/")
public class IndexController {

	//京淘首页
	@RequestMapping("index")
	public String index(){
		return "index";
	}
}
