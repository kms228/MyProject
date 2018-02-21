package shs.admin.vo.order;

public class PrepProdVo {
	private int buy_num;
	private int order_num;
	private int amount;
	private int pnum;
	private int price;
	private String item_name;
	private String buy_date;
	private int accprice;
	private String name;
			
	public PrepProdVo() {}

	public PrepProdVo(int buy_num, int order_num, int amount, int pnum, int price, String item_name, String buy_date,
			int accprice, String name) {
		this.buy_num = buy_num;
		this.order_num = order_num;
		this.amount = amount;
		this.pnum = pnum;
		this.price = price;
		this.item_name = item_name;
		this.buy_date = buy_date;
		this.accprice = accprice;
		this.name = name;
	}

	public int getBuy_num() {
		return buy_num;
	}

	public void setBuy_num(int buy_num) {
		this.buy_num = buy_num;
	}

	public int getOrder_num() {
		return order_num;
	}

	public void setOrder_num(int order_num) {
		this.order_num = order_num;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getPnum() {
		return pnum;
	}

	public void setPnum(int pnum) {
		this.pnum = pnum;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public String getBuy_date() {
		return buy_date;
	}

	public void setBuy_date(String buy_date) {
		this.buy_date = buy_date;
	}

	public int getAccprice() {
		return accprice;
	}

	public void setAccprice(int accprice) {
		this.accprice = accprice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	};		
}
