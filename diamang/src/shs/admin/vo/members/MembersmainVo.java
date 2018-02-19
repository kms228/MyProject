package shs.admin.vo.members;

public class MembersmainVo {
	private String recent;
	private String allmembers;
	//constructor
	public MembersmainVo(){};
	public MembersmainVo(String recent, String allmembers) {
		this.recent = recent;
		this.allmembers = allmembers;
	}
	//getter, setter
	public String getRecent() {
		return recent;
	}
	public void setRecent(String recent) {
		this.recent = recent;
	}
	public String getAllmembers() {
		return allmembers;
	}
	public void setAllmembers(String allmembers) {
		this.allmembers = allmembers;
	}		
}
