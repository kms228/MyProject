package shs.admin.vo.order;

public class SwitchShippedLogVo {
	private int buy_num;
	private int mnum;
	private String buy_date;
	private String state;
	private String caddr;
	private String name;
	private String addr;
	private int accprice;
	
	//toString
		@Override
		public String toString() {
			return "{"+"buy_num : "+buy_num +", mnum : "+ mnum +", buy_date : "+ buy_date +", state : "+ state +", caddr : "+ caddr +", name : "+ name +", addr : "+ addr +", accprice : "+ accprice +"}"; 
		}
	
	public SwitchShippedLogVo(int buy_num, int mnum, String buy_date, String state, String caddr, String name,
			String addr, int accprice) {
		this.buy_num = buy_num;
		this.mnum = mnum;
		this.buy_date = buy_date;
		this.state = state;
		this.caddr = caddr;
		this.name = name;
		this.addr = addr;
		this.accprice = accprice;
	}
	public SwitchShippedLogVo() {}
	
	//getter,setter		
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

	public String getBuy_date() {
		return buy_date;
	}

	public void setBuy_date(String buy_date) {
		this.buy_date = buy_date;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCaddr() {
		return caddr;
	}

	public void setCaddr(String caddr) {
		this.caddr = caddr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public int getAccprice() {
		return accprice;
	}

	public void setAccprice(int accprice) {
		this.accprice = accprice;
	}
	
}
