package user.vo;

public class RvItemVo_kdy {
	private int pnum;
	private String item_name;
	private int price;
	private String savename;
	
	public RvItemVo_kdy() {}

	public RvItemVo_kdy(int pnum, String item_name, int price,String savename) {
		super();
		this.pnum = pnum;
		this.item_name = item_name;
		this.price = price;
		this.savename=savename;
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
	
	public String getSavename() {
		return savename;
	}
	
	public void setSavename(String savename) {
		this.savename=savename;
	}
	
}
