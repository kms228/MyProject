package shs.admin.vo.members;

public class MembersgradeVo {
	private int gnum;
	private String grade;
	private int drate;
	private int gnumcnt;
	
	public MembersgradeVo() {};
	public MembersgradeVo(int gnum, String grade, int drate, int gnumcnt) {
		this.gnum = gnum;
		this.grade = grade;
		this.drate = drate;
		this.gnumcnt = gnumcnt;
	}
	
	public int getGnum() {
		return gnum;
	}
	public void setGnum(int gnum) {
		this.gnum = gnum;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getDrate() {
		return drate;
	}
	public void setDrate(int drate) {
		this.drate = drate;
	}
	public int getGnumcnt() {
		return gnumcnt;
	}
	public void setGnumcnt(int Gnumcnt) {
		this.gnumcnt = Gnumcnt;
	}		
}
