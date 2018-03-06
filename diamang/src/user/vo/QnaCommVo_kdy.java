package user.vo;

public class QnaCommVo_kdy {
	private int num;
	private String id;
	private String pwd;
	private String comments;
	private String regdate;
	private int qnum;
	
	public QnaCommVo_kdy() {}
	
	public QnaCommVo_kdy(int num, String id, String pwd, String comments, String regdate, int qnum) {
		super();
		this.num = num;
		this.id = id;
		this.pwd = pwd;
		this.comments = comments;
		this.regdate = regdate;
		this.qnum = qnum;
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
		this.regdate = regdate;
	}
	public int getQnum() {
		return qnum;
	}
	public void setQnum(int qnum) {
		this.qnum = qnum;
	}
	
	
}
