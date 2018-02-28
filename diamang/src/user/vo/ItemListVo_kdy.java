package user.vo;

import java.sql.Date;

public class ItemListVo_kdy {
	private int pnum;
	private String item_name;
	private int price;
	private Date regdate;
	private int stock;
	private int fieldnum;
	private String savename;
	private String detailImg;
	
	public ItemListVo_kdy() {}

	public ItemListVo_kdy(int pnum, String item_name, int price, Date regdate, int stock, String savename) {
		super();
		this.pnum = pnum;
		this.item_name = item_name;
		this.price = price;
		this.regdate = regdate;
		this.stock = stock;
		this.savename=savename;
	}
	
	public ItemListVo_kdy(int pnum, String item_name, int price, Date regdate, int stock, String savename, String detailImg) {
		super();
		this.pnum = pnum;
		this.item_name = item_name;
		this.price = price;
		this.regdate = regdate;
		this.stock = stock;
		this.savename=savename;
		this.detailImg=detailImg;
	}

	public int getPnum() {
		return pnum;
	}

	public void setPnum(int pnum) {
		this.pnum = pnum;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getFieldnum() {
		return fieldnum;
	}

	public void setFieldnum(int fieldnum) {
		this.fieldnum = fieldnum;
	}

	public String getSavename() {
		return savename;
	}

	public void setSavename(String savename) {
		this.savename = savename;
	}
	
	public String getDetailImg() {
		return detailImg;
	}

	public void setDetailImg(String detailImg) {
		this.detailImg=detailImg;
	}
	
}
