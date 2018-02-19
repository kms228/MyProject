package user.vo;

import java.sql.Date;

public class MembersVo_kdy {
	private int mnum;
	private String id;
	private String pwd;
	private String name;
	private Date birthday;
	private String email;
	private String address;
	private String phone;
	private int gnum;
	
	
	@Override
	public String toString() {
		return "MembersVo_kdy [mnum=" + mnum + ", id=" + id + ", pwd=" + pwd + ", name=" + name + ", birthday=" + birthday
				+ ", email=" + email + ", address=" + address + ", phone=" + phone + ", gradeNum=" + gnum
				+ ", accAmt=" + "]";
	}
	public MembersVo_kdy(int mnum, String id, String pwd, String name, Date birthday, String email, String address,
			String phone, int gnum) {
		super();
		this.mnum = mnum;
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.birthday = birthday;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.gnum = gnum;
	}
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
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
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
	public int getGnum() {
		return gnum;
	}
	public void setgNum(int gnum) {
		this.gnum = gnum;
	}
	
}
