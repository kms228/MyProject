package shs.admin.vo.members;

public class MembersVo {
	private int mnum;
	private String id;
	private String pwd;
	private String name;
	private String birthday;
	private String email;
	private String address;
	private String phone;
	private String gnum;
	private String joindate;
	private String grade;
	//constructor
	public MembersVo() {};
	public MembersVo(int mnum, String id, String pwd, String name, String birthday, String email, String address,
			String phone, String gnum, String joindate, String grade) {
		this.mnum = mnum;
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.birthday = birthday;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.gnum = gnum;
		this.joindate = joindate;
		this.grade = grade;
	}
	//getter, setter
	public int getMnum() {
		return mnum;
	}
	public void setMnum(int mnum) {
		this.mnum = mnum;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getGnum() {
		return gnum;
	}
	public void setGnum(String gnum) {
		this.gnum = gnum;
	}
	public String getJoindate() {
		return joindate;
	}
	public void setJoindate(String joindate) {
		this.joindate = joindate;
	}			
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
}
