package shs.admin.vo.paging;

public class PagingVo {
	private int pageNum;
	private int startRow;
	private int endRow;
	private int pageCount;
	private int startPage;
	private int endPage;
	
	public PagingVo() {};
	public PagingVo(int pageNum, int startRow, int endRow, int pageCount, int startPage, int endPage) {
		this.pageNum = pageNum;
		this.startRow = startRow;
		this.endRow = endRow;
		this.pageCount = pageCount;
		this.startPage = startPage;
		this.endPage = endPage;
	}
	
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getEndRow() {
		return endRow;
	}
	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}			
}
