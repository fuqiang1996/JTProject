package com.jt.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.manage.pojo.ItemCat;
import com.jt.manage.service.ItemCatService;

/**
 * 商品分类控制层
 * @author 富强
 *
 */
@Controller
@RequestMapping("/item/cat/")
public class ItemCatController {
	
	@Autowired
	private ItemCatService itemCatService;
	//显示商品分类树
	@RequestMapping("list")
	@ResponseBody
	public List<ItemCat> findItemCatAll(@RequestParam(value="id",defaultValue="0") Long parentId){
			return itemCatService.findItemCat(parentId);
	}
}
