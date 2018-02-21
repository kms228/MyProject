package user.vo;

import java.sql.Date;

public class RvCommVo_kdy {
	private int num;
	private String id;
	private String pwd;
	private String comments;
	private String regdate;
	private int rv_num;
	
	public RvCommVo_kdy() {}

	public RvCommVo_kdy(int num, String id, String pwd, String comments, String regdate, int rv_num) {
		super();
		this.num = num;
		this.id = id;
		this.pwd = pwd;
		this.comments = comments;
		this.regdate=regdate;
		this.rv_num=rv_num;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public String getRegdate() {
		return regdate;
	}
	
	public void setRegdate(String regdate) {
		this.regdate=regdate;
	}
	
	public int getRv_num() {
		return rv_num;
	}
	
	public void setRv_num(int rv_num) {
		this.rv_num=rv_num;
	}
}
