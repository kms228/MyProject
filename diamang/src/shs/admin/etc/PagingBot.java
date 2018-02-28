package shs.admin.etc;

import shs.admin.vo.paging.PagingVo;

public class PagingBot {
	//해당 페이지에서 보여줄 글의 개수(1페이지에서 글 20개를 출력하고 싶다. 20)
	private int pageRowVolume;
	//1,2,3,4,5...10 이라면 currentPageVolume은 10 
	private int currentPageVolume;
	//costructor
	public PagingBot(int pageRowVolume, int currentPageVolume) {
		this.pageRowVolume = pageRowVolume;
		this.currentPageVolume = currentPageVolume;
	}	
	//페이징
	public PagingVo calPaging(String pageNum, int boardCount) {//pageNum: 해당 페이지. boardCount: 게시판의 모든 글 개수.
		//파라미터로 pageNum을 받았다면 대입함.
		int integerPageNum = 1;
		if(pageNum != null) {
			integerPageNum = Integer.parseInt(pageNum);
		}
		int startRow = (integerPageNum - 1) * pageRowVolume + 1;// 시작행 번호
		int endRow = startRow + (pageRowVolume-1);// 끝행 번호
		int pageCount = boardCount/pageRowVolume;
		if(boardCount%pageRowVolume!=0) {
			pageCount=pageCount +1;
		}		
		int startPage = ((integerPageNum - 1) / currentPageVolume * currentPageVolume) + 1;// 시작페이지
		int endPage = startPage + (currentPageVolume-1); // 끝페이지
		if (pageCount < endPage) {
			endPage = pageCount;
		}
		PagingVo vo = new PagingVo(pageRowVolume,currentPageVolume,integerPageNum, startRow, endRow, pageCount, startPage, endPage);
		return vo;
	}
}