package user.vo;

public class QnaImgVo_kdy {
	private int num;
	private int qnum;
	private String savename;
	
	public QnaImgVo_kdy() {}
	
	public QnaImgVo_kdy(int num, int qnum, String savename) {
		super();
		this.num = num;
		this.qnum = qnum;
		this.savename = savename;
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getQnum() {
		return qnum;
	}
	public void setQnum(int qnum) {
		this.qnum = qnum;
	}
	public String getSavename() {
		return savename;
	}
	public void setSavename(String savename) {
		this.savename = savename;
	}
	
}
