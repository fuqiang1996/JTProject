package com.jt.manage.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.EasyUIResult;
import com.jt.common.vo.SysResult;
import com.jt.manage.pojo.Item;
import com.jt.manage.pojo.ItemDesc;
import com.jt.manage.service.ItemService;

@Controller
@RequestMapping("/item/")
public class ItemController {
	
	//增加日志
	private static final Logger logger = Logger.getLogger(ItemController.class);
	@Autowired
	private ItemService itemService;
	
	//分页查询商品
	@RequestMapping("query")
	@ResponseBody
	public EasyUIResult query(Integer page,Integer rows){
		
		EasyUIResult easyUIResult = itemService.findItemByPage(page, rows);
		return easyUIResult;
	}
	
	//根据商品分类ID查询商品分类名称
	@RequestMapping(value="cat/queryItemCatName",produces="text/html;charset=utf-8")
	@ResponseBody
	public String findItemNameById(Long itemCatId){
		return itemService.findItemCatById(itemCatId);
	}
	
	//保存商品信息
	@RequestMapping("save")
	@ResponseBody
	public SysResult saveItem(Item item , String desc){
		try {
			itemService.saveItem(item , desc);
			logger.info("{!!!!!!!!!!!!!!!!!!!!!!保存成功}");
			return SysResult.build(200, "保存成功!");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("!!!!!!!!!!!!!!!!!!!!!!!保存失败");
			return SysResult.build(201, "保存失败");
		}
	}
	
	//删除商品
	@RequestMapping("delete")
	@ResponseBody
	public SysResult deleteItem(Long[] ids){
		try {
			itemService.deleteItem(ids);
			logger.info("{!!!!!!!!!!!!!!!!!!!!!!删除成功}");
			return SysResult.build(200, "删除成功!");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("!!!!!!!!!!!!!!!!!!!!!!!删除失败");
			return SysResult.build(201, "删除失败");
		}
	}
	
	//更新商品
	@RequestMapping("update")
	@ResponseBody
	public SysResult updateItem(Item item ,String desc){
		try {
			itemService.updateItem(item ,desc);
			logger.info("{!!!!!!!!!!!!!!!!!!!!!!更新成功}");
			return SysResult.build(200, "更新成功!");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("!!!!!!!!!!!!!!!!!!!!!!!更新失败");
			return SysResult.build(201, "更新失败");
		}
	}
	
	//商品下架
	@RequestMapping("instock")
	@ResponseBody
	public SysResult itemInstock(Long[] ids){
		try {
			int status = 2;
			itemService.updateStatus(status,ids);
			logger.info("{!!!!!!!!!!!!!!!!!!!!!!下架成功}");
			return SysResult.build(200, "下架成功!");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("!!!!!!!!!!!!!!!!!!!!!!!下架失败");
			return SysResult.build(201, "下架失败");
		}
	}
	//商品上架
		@RequestMapping("reshelf")
		@ResponseBody
		public SysResult itemReshelf(Long[] ids){
			try {
				int status = 1;
				itemService.updateStatus(status,ids);
				logger.info("{!!!!!!!!!!!!!!!!!!!!!上架成功}");
				return SysResult.build(200, "上架成功!");
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("!!!!!!!!!!!!!!!!!!!!!!!上架失败");
				return SysResult.build(201, "上架失败");
			}
		}
		
		//商品描述分类回显
		@RequestMapping("query/item/desc/{itemId}")
		@ResponseBody
		public SysResult findItemDescById(@PathVariable Long itemId){
			try {
				ItemDesc itemDesc = itemService.findItemDescById(itemId);
				System.out.println(itemDesc);
				return SysResult.oK(itemDesc);
				
			} catch (Exception e) {
				e.printStackTrace();
				return SysResult.build(201,"查询商品描述失败");
			}
		}
		
}
