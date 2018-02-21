package shs.admin.vo.order;

public class SearchOptionVo {
	private String optName;
	private String optValue;
	private String buy_date;
	private String item_name;
	
	public SearchOptionVo(String optName, String optValue, String buy_date, String item_name) {
		this.optName = optName;
		this.optValue = optValue;
		this.buy_date = buy_date;
		this.item_name = item_name;
	}

	public String getOptName() {
		return optName;
	}

	public void setOptName(String optName) {
		this.optName = optName;
	}

	public String getOptValue() {
		return optValue;
	}

	public void setOptValue(String optValue) {
		this.optValue = optValue;
	}

	public String getBuy_date() {
		return buy_date;
	}

	public void setBuy_date(String buy_date) {
		this.buy_date = buy_date;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}		
}
