package user.vo;

import java.sql.Date;

public class OrderListVo_kdy {
	private Date buy_date;
	private int buy_num;
	private String item_name;
	private int accprice;
	private String state;
	private int cnt;
	
	public OrderListVo_kdy() {}
	
	public OrderListVo_kdy(Date buy_date, int buy_num, String item_name, int accprice, String state,int cnt) {
		super();
		this.buy_date = buy_date;
		this.buy_num = buy_num;
		this.item_name = item_name;
		this.accprice = accprice;
		this.state = state;
		this.cnt=cnt;
	}
	
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
	public Date getBuy_date() {
		return buy_date;
	}
	public void setBuy_date(Date buy_date) {
		this.buy_date = buy_date;
	}
	public int getBuy_num() {
		return buy_num;
	}
	public void setBuy_num(int buy_num) {
		this.buy_num = buy_num;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public int getAccprice() {
		return accprice;
	}
	public void setAccprice(int accprice) {
		this.accprice = accprice;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
}
