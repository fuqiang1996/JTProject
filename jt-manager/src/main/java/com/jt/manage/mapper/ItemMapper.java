package com.jt.manage.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jt.common.mapper.SysMapper;
import com.jt.manage.pojo.Item;

/***
 * 商品映射接口
 * @author 富强
 *
 */
public interface ItemMapper extends SysMapper<Item>{
	
	/**分页查询商品*/
	List<Item> findItemByPage(@Param("start")int start,@Param("rows")Integer rows);

	/**查询总记录数*/
	int findCount();
	
	/**改变商品状态*/
	void updateStatus(@Param("status")int status,@Param("ids")Long[] ids);
}
