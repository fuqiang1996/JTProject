package com.jt.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jt.web.pojo.Item;
import com.jt.web.service.ItemService;

@RequestMapping("/items/")
@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("{itemId}")
	public String findItemCatById(@PathVariable Long itemId,Model model){
		Item item = itemService.findItemByid(itemId);
		model.addAttribute("item",item);
		return "item";
	}
}
