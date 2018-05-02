package com.jt.web.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jt.web.pojo.Item;
import com.jt.web.service.SearchService;

@Controller
public class SearchController {
	
	@Autowired
	private SearchService searchService;
	
	//根据关键字查询商品信息
	@RequestMapping("/search")
	public String findItemByKeyWord(@RequestParam("q")String keyWord,@RequestParam(defaultValue="1")Integer page,Model model){
		//由于是get请求 所以会有中文乱码问题
		
		try {
			keyWord = new String(keyWord.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		//定义每页的行数
		int rows = 20;
		List<Item> itemList = searchService.findItemListByKeyWord(keyWord,page,rows);
		model.addAttribute("itemList", itemList);
		model.addAttribute("query", keyWord);
		
		return "search";
	}

}
