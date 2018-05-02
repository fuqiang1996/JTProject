package com.jt.web.pojo;

import java.io.Serializable;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jt.common.po.BasePojo;

/**
 * 商品实体类
 * @author 富强
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Item extends BasePojo implements Serializable{
	/**
	 * 版本号
	 */
	private static final long serialVersionUID = -8180033268052381774L;
	
	private Long id;		//商品的ID号
	private String title;	//商品的标题
	private String sellPoint;//商品的卖点信息
	private Long price;		//定义Long类型的目的是为了计算速度快
	private Integer num;	//商品的数量
	private String barcode;	//商品二维码信息
	private String image;	//商品的图片信息
	private Long cid;		//商品分类的ID
	private Integer status;	//默认值为1，可选值：1正常，2下架，3删除
	
	public String[] getImages(){
		return image.split(",");
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSellPoint() {
		return sellPoint;
	}
	public void setSellPoint(String sellPoint) {
		this.sellPoint = sellPoint;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Long getCid() {
		return cid;
	}
	public void setCid(Long cid) {
		this.cid = cid;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Item [id=" + id + ", title=" + title + ", sellPoint=" + sellPoint + ", price=" + price + ", num=" + num
				+ ", barcode=" + barcode + ", image=" + image + ", cid=" + cid + ", status=" + status + "]";
	}
	
	
}
