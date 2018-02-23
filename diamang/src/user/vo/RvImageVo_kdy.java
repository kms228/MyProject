package user.vo;

public class RvImageVo_kdy {
	private int num;
	private int rv_num;
	private String savename;
	
	public RvImageVo_kdy() {}
	
	public RvImageVo_kdy(int num, int rv_num, String savename) {
		super();
		this.num = num;
		this.rv_num = rv_num;
		this.savename = savename;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getRv_num() {
		return rv_num;
	}

	public void setRv_num(int rv_num) {
		this.rv_num = rv_num;
	}

	public String getSavename() {
		return savename;
	}

	public void setSavename(String savename) {
		this.savename = savename;
	}
}
