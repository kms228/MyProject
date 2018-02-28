package admin.vo;

public class BasicInfoVo {
	private String info;
	private int num;
	
	public BasicInfoVo() {}

	public BasicInfoVo(String info, int num) {
		super();
		this.info = info;
		this.num = num;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	@Override
	public String toString() {
		return "BasicInfoVo [info=" + info + ", num=" + num + "]";
	}
	
	
	
	
}
