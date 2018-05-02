package com.jt.web.service;

import java.util.List;

import com.jt.web.pojo.Item;

public interface SearchService {
	
	//根据关键字查询商品信息
	public List<Item> findItemListByKeyWord(String keyWord, Integer page, Integer rows);

}
