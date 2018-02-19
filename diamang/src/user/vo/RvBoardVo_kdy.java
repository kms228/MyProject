package user.vo;

import java.sql.Date;

public class RvBoardVo_kdy {
	private int rv_num;
	private int mnum;
	private String title;
	private String content;
	private Date regdate;
	private int hit;
	private int ref;
	private int lev;
	private int step;
	private int star;
	private String writer;
	private String pwd;
	
	public RvBoardVo_kdy() {}

	public RvBoardVo_kdy(int rv_num, int mnum, String title, String content, Date regdate, int hit, int ref, int lev,
			int step, int star,String pwd) {
		super();
		this.rv_num = rv_num;
		this.mnum = mnum;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
		this.hit = hit;
		this.ref = ref;
		this.lev = lev;
		this.step = step;
		this.star = star;
		this.pwd=pwd;
	}
	
	public RvBoardVo_kdy(int rv_num, int mnum, String title, String writer, String content, Date regdate, int hit, int ref, int lev,
			int step, int star,String pwd) {
		super();
		this.rv_num = rv_num;
		this.mnum = mnum;
		this.title = title;
		this.writer= writer;
		this.content = content;
		this.regdate = regdate;
		this.hit = hit;
		this.ref = ref;
		this.lev = lev;
		this.step = step;
		this.star = star;
		this.pwd=pwd;
	}

	public int getRv_num() {
		return rv_num;
	}

	public void setRv_num(int rv_num) {
		this.rv_num = rv_num;
	}

	public int getMnum() {
		return mnum;
	}

	public void setMnum(int mnum) {
		this.mnum = mnum;
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

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
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

	public int getStar() {
		return star;
	}

	public void setStar(int star) {
		this.star = star;
	}
	
	public String getWriter() {
		return writer;
	}
	
	public void setWriter(String writer) {
		this.writer=writer;
	}
	public String getPwd() {
		return pwd;
	}
	
	public void setPwd(String pwd) {
		this.pwd=pwd;
	}
}
