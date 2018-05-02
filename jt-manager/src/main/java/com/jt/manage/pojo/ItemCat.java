package com.jt.manage.pojo;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jt.common.po.BasePojo;
/**
 * 商品分类
 * @author 富强
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@Table(name="tb_item_cat")
public class ItemCat extends BasePojo implements Serializable{

	/**
	 * 版本号
	 */
	private static final long serialVersionUID = -6743901432068608446L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Long parentId;  //父级ID
	private String name;	//商品分类名称
	private Integer status; //商品状态 默认值为1, 删除为 2
	private Integer sortOrder; //排序号
	private Boolean isParent;	//是否为父ID
	/**
	 * 根据 responsebody传输数据格式
	 * easyUI树需要获取固定的变量名来封装数据
	 * @return
	 */
	public String getText(){
		return name;
	}
	
	public String getState(){
		return isParent?"closed":"open";
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}
	public Boolean getIsParent() {
		return isParent;
	}
	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}
	
}
