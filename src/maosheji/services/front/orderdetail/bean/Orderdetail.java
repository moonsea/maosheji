package maosheji.services.front.orderdetail.bean;import java.io.Serializable;public class Orderdetail extends maosheji.services.common.Orderdetail implements Serializable {	private static final long serialVersionUID = 1L;	private String picture;// 商品图片地址		public void clear() {		super.clear();		picture = null;	}	public String getPicture() {		return picture;	}	public void setPicture(String picture) {		this.picture = picture;	}}