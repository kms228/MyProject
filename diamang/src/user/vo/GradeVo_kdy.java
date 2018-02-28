package user.vo;

public class GradeVo_kdy {
	private int gnum;
	private String grade;
	private int drate;
	
	public GradeVo_kdy() {}
	
	public GradeVo_kdy(int gnum, String grade, int drate) {
		super();
		this.gnum = gnum;
		this.grade = grade;
		this.drate = drate;
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
	
	
}
