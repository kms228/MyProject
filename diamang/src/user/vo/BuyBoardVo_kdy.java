package user.vo;

import java.sql.Date;

public class BuyBoardVo_kdy {
	private int buy_num;
	private int mnum;
	private Date buy_date;
	private String state;
	private String name;
	private String addr;
	private String caddr;
	private int accprice;
	private int drate;
	
	public BuyBoardVo_kdy() {}

	public BuyBoardVo_kdy(int buy_num, int mnum, Date buy_date, String state, String name, String addr, String caddr, int accprice, int drate) {
		super();
		this.buy_num = buy_num;
		this.mnum = mnum;
		this.buy_date=buy_date;
		this.state = state;
		this.name=name;
		this.addr = addr;
		this.caddr = caddr;
		this.accprice = accprice;
		this.drate = drate;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBuy_num() {
		return buy_num;
	}

	public void setBuy_num(int buy_num) {
		this.buy_num = buy_num;
	}

	public int getMnum() {
		return mnum;
	}

	public void setMnum(int mnum) {
		this.mnum = mnum;
	}
	
	public Date getBuy_date() {
		return buy_date;
	}
	
	public void setBuy_date(Date buy_date) {
		this.buy_date=buy_date;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getCaddr() {
		return caddr;
	}

	public void setCaddr(String caddr) {
		this.caddr = caddr;
	}

	public int getAccprice() {
		return accprice;
	}

	public void setAccprice(int accprice) {
		this.accprice = accprice;
	}

	public int getDrate() {
		return drate;
	}

	public void setDrate(int drate) {
		this.drate = drate;
	}
	
	
}
