package user.vo;

public class OrderVo_kdy {
	private int pnum;
	private String item_name;
	private String savename;
	private int price;
	private String grade;
	private int amount;
	private int total;
	
	public OrderVo_kdy() {}
	
	public OrderVo_kdy(int pnum,String item_name, String savename, int price, String grade, int amount, int total) {
		super();
		this.pnum=pnum;
		this.item_name = item_name;
		this.savename=savename;
		this.price = price;
		this.grade = grade;
		this.amount = amount;
		this.total = total;
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
	public String getSavename() {
		return savename;
	}
	public void setSaveName(String savename) {
		this.savename = savename;
	}
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
}
