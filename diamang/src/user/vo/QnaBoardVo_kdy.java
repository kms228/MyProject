package user.vo;

import java.sql.Date;

public class QnaBoardVo_kdy {
	private int qnum;
	private int mnum;
	private String title;
	private String writer;
	private String content;
	private Date regdate;
	private int hit;
	private int ref;
	private int lev;
	private int step;
	private String savename;
	
	public QnaBoardVo_kdy() {}
	
	public QnaBoardVo_kdy(int qnum, int mnum, String title, String writer, String content, Date regdate, int hit,
			int ref, int lev, int step, String savename) {
		super();
		this.qnum = qnum;
		this.mnum = mnum;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.regdate = regdate;
		this.hit = hit;
		this.ref = ref;
		this.lev = lev;
		this.step = step;
		this.savename = savename;
	}

	public QnaBoardVo_kdy(int qnum, int mnum, String title, String writer, String content, Date regdate, int hit,
			int ref, int lev, int step) {
		super();
		this.qnum = qnum;
		this.mnum = mnum;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.regdate = regdate;
		this.hit = hit;
		this.ref = ref;
		this.lev = lev;
		this.step = step;
	}

	public QnaBoardVo_kdy(int qnum, int mnum, String title, String content, Date regdate, int hit, int ref, int lev,
			int step) {
		super();
		this.qnum = qnum;
		this.mnum = mnum;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
		this.hit = hit;
		this.ref = ref;
		this.lev = lev;
		this.step = step;
	}
	
	public QnaBoardVo_kdy(int qnum, String title, String content) {
		super();
		this.qnum = qnum;
		this.title = title;
		this.content = content;
	}

	public String getSavename() {
		return savename;
	}

	public void setSavename(String savename) {
		this.savename = savename;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public int getQnum() {
		return qnum;
	}

	public void setQnum(int qnum) {
		this.qnum = qnum;
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
	
	
	
}
