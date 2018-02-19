package shs.admin.vo.board;

public class BoardqnaVo {
	private int mnum;
	private int qnum;
	private String title;
	private String content;
	private String regdate;
	private int hit;
	private int refer;
	private int lev;
	private int step;
	
	public BoardqnaVo() {};
	public BoardqnaVo(int mnum, int qnum, String title, String content, String regdate, int hit, int refer, int lev,
			int step) {
		this.mnum = mnum;
		this.qnum = qnum;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
		this.hit = hit;
		this.refer = refer;
		this.lev = lev;
		this.step = step;
	}
	
	public int getMnum() {
		return mnum;
	}
	public void setMnum(int mnum) {
		this.mnum = mnum;
	}
	public int getQnum() {
		return qnum;
	}
	public void setQnum(int qnum) {
		this.qnum = qnum;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getRefer() {
		return refer;
	}
	public void setRefer(int refer) {
		this.refer = refer;
	}
	public int getLev() {
		return lev;
	}
	public void setLev(int lev) {
		this.lev = lev;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}	
}
