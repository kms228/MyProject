package admin.vo;

public class ItemImg1Vo {
	private int num;
	private int pnum;
	private String savefilename;
	
	public ItemImg1Vo() {}

	public ItemImg1Vo(int num, int pnum, String savefilename) {
		super();
		this.num = num;
		this.pnum = pnum;
		this.savefilename = savefilename;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getPnum() {
		return pnum;
	}

	public void setPnum(int pnum) {
		this.pnum = pnum;
	}

	public String getSavefilename() {
		return savefilename;
	}

	public void setSavefilename(String savefilename) {
		this.savefilename = savefilename;
	}

	@Override
	public String toString() {
		return "ItemImageVo [num=" + num + ", pnum=" + pnum + ", savefilename=" + savefilename + "]";
	}
	
	
}
