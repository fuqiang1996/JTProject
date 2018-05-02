package com.jt.manage.mapper;

import com.jt.common.mapper.SysMapper;
import com.jt.manage.pojo.ItemCat;

/**
 * 商品分类映射接口
 * @author 富强
 *
 */
public interface ItemCatMapper extends SysMapper<ItemCat>{
	
	/**根据商品ID 查询商品名称*/
	String findItemNameById(Long itemCatId);
	
	/***
	 * 使用了通用Mapper插件 就不需要些普通的CRUD
	 */
}
