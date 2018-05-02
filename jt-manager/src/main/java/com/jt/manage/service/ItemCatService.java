package com.jt.manage.service;

import java.util.List;

import com.jt.common.vo.ItemCatResult;
import com.jt.manage.pojo.ItemCat;

/**
 * 商品分类业务接口
 * @author 富强
 *
 */
public interface ItemCatService {
	/**
	 * 查询商品分类信息
	 * @param parentId
	 * @return
	 */
	List<ItemCat> findItemCat(Long parentId);
	/**
	 * 查询全部商品信息
	 * @return
	 */
	ItemCatResult findItemCatAll();
	/**
	 * 查询全部商品信息 并缓存到redis中
	 * @return
	 */
	ItemCatResult findCacheItemCatAll();
	

}
