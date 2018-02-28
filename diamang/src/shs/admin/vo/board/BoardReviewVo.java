package shs.admin.vo.board;

public class BoardReviewVo {
	private int rv_num;
	private int mnum;
	private String title;
	private String content;
	private String regdate;
	private int hit;
	private int ref;
	private int lev;
	private int step;
	private int star;
	private int pnum;
	private String savename;
	private String id;
	private String item_name;
	private int rvcnt;
	private String pwd;
	
	//constructor
	public BoardReviewVo() {}
	public BoardReviewVo(int rv_num, int mnum, String title, String content, String regdate, int hit, int ref, int lev,
			int step, int star, int pnum, String savename, String id, String item_name, int rvcnt) {
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
		this.pnum = pnum;
		this.savename = savename;
		this.id = id;
		this.item_name =item_name;
		this.rvcnt = rvcnt;
	}			
	public BoardReviewVo(int rv_num, String title, String content, String regdate, int hit, int star, String savename,String id, String item_name, int rvcnt, int ref, int lev, int step) {
		this.rv_num = rv_num;
		this.title = title;
		this.content = content;
		this.regdate = regdate;
		this.hit = hit;
		this.star = star;
		this.savename = savename;
		this.id = id;
		this.item_name =item_name;
		this.rvcnt = rvcnt;
		this.ref = ref;
		this.lev = lev;
		this.step = step;
	}	
	public BoardReviewVo(int rv_num, String title, String regdate, String content, int hit, int star, String savename,String id, String item_name, int rvcnt, int ref, int lev, int step, String pwd) {
		this.rv_num = rv_num;
		this.title = title;
		this.regdate = regdate;
		this.content = content;
		this.hit = hit;
		this.star = star;
		this.savename = savename;
		this.id = id;
		this.item_name =item_name;
		this.rvcnt = rvcnt;
		this.ref = ref;
		this.lev = lev;
		this.step = step;
		this.pwd = pwd;
	}
	public BoardReviewVo(int rv_num, String title, int hit) {
		this.rv_num = rv_num;
		this.title = title;		
		this.hit = hit;		
	}		
	public BoardReviewVo(String id, int rvcnt) {
		this.id = id;
		this.rvcnt = rvcnt;
	}
	//getter, setter
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
	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	public String getSavename() {
		return savename;
	}
	public void setSavename(String savename) {
		this.savename = savename;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}

	public int getRvcnt() {
		return rvcnt;
	}

	public void setRvcnt(int rvcnt) {
		this.rvcnt = rvcnt;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}	
	
}
