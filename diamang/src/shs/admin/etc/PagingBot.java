package shs.admin.etc;

import shs.admin.vo.paging.PagingVo;

public class PagingBot {
	//�ش� ���������� ������ ���� ����(1���������� �� 20���� ����ϰ� �ʹ�. 20)
	private int pageRowVolume;
	//1,2,3,4,5...10 �̶�� currentPageVolume�� 10 
	private int currentPageVolume;
	//costructor
	public PagingBot(int pageRowVolume, int currentPageVolume) {
		this.pageRowVolume = pageRowVolume;
		this.currentPageVolume = currentPageVolume;
	}	
	//����¡
	public PagingVo calPaging(String pageNum, int boardCount) {//pageNum: �ش� ������. boardCount: �Խ����� ��� �� ����.
		//�Ķ���ͷ� pageNum�� �޾Ҵٸ� ������.
		int integerPageNum = 1;
		if(pageNum != null) {
			integerPageNum = Integer.parseInt(pageNum);
		}
		int startRow = (integerPageNum - 1) * pageRowVolume + 1;// ������ ��ȣ
		int endRow = startRow + (pageRowVolume-1);// ���� ��ȣ
		int pageCount = boardCount/pageRowVolume;
		if(boardCount%pageRowVolume!=0) {
			pageCount=pageCount +1;
		}		
		int startPage = ((integerPageNum - 1) / currentPageVolume * currentPageVolume) + 1;// ����������
		int endPage = startPage + (currentPageVolume-1); // ��������
		if (pageCount < endPage) {
			endPage = pageCount;
		}
		PagingVo vo = new PagingVo(pageRowVolume,currentPageVolume,integerPageNum, startRow, endRow, pageCount, startPage, endPage);
		return vo;
	}
}