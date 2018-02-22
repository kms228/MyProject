package admin.vo;

import java.sql.Date;

public class ItemVo {
	private int pnum;
	private String item_name;
	private int price;
	private Date regdate;
	private int stock;
	private int fieldnum;
	
	public ItemVo() {}

	public ItemVo(int pnum, String item_name, int price, Date regdate, int stock, int fieldnum) {
		super();
		this.pnum = pnum;//1
		this.item_name = item_name;//2
		this.price = price;//3
		this.regdate = regdate;//4
		this.stock = stock;//5
		this.fieldnum = fieldnum;//6
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

	@Override
	public String toString() {
		return "ItemVo [pnum=" + pnum + ", item_name=" + item_name + ", price=" + price + ", regdate=" + regdate
				+ ", stock=" + stock + ", fieldnum=" + fieldnum
				+ "]";
	}
	
	
}
