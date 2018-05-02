package com.jt.manage.pojo;

import javax.persistence.Id;
import javax.persistence.Table;

import com.jt.common.po.BasePojo;

/**
 * 商品描述类
 * @author 富强
 *
 */
@Table(name="tb_item_desc")
public class ItemDesc extends BasePojo{
	
	/**
	 * 版本号
	 */
	private static final long serialVersionUID = -412881442814454261L;
	@Id
	private Long itemId;
	private String itemDesc;
	
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public String getItemDesc() {
		return itemDesc;
	}
	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}
	
	@Override
	public String toString() {
		return "ItemDesc [itemId=" + itemId + ", itemDesc=" + itemDesc + "]";
	}
	
	
	
}
