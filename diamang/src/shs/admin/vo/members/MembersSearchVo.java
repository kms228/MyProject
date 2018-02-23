package shs.admin.vo.members;

public class MembersSearchVo {
	private String optName;
	private String optValue;
	private String gnum;
	private String pnum;
	
	public MembersSearchVo(String optName, String optValue, String gnum, String pnum) {
		this.optName = optName;
		this.optValue = optValue;
		this.gnum = gnum;
		this.pnum = pnum;
	}
	
	public String getOptName() {
		return optName;
	}
	public void setOptName(String optName) {
		this.optName = optName;
	}
	public String getOptValue() {
		return optValue;
	}
	public void setOptValue(String optValue) {
		this.optValue = optValue;
	}
	public String getGnum() {
		return gnum;
	}
	public void setGnum(String gnum) {
		this.gnum = gnum;
	}
	public String getPnum() {
		return pnum;
	}
	public void setPnum(String pnum) {
		this.pnum = pnum;
	}							
}
