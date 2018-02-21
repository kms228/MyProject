package shs.admin.junit;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import shs.admin.dao.board.BoardqnaDao;
import shs.admin.etc.PagingBot;
import shs.admin.vo.paging.PagingVo;

public class BoardqnaTest {
	private BoardqnaDao dao;
	
	@Before
	public void setUp() {				
		dao = new BoardqnaDao();
	}
	@Test
	public void test() {
		PagingBot pBot = new PagingBot(10, 10);
		assertEquals(0, dao.getCount());
		System.out.println(dao.getCount());
		PagingVo vo = pBot.calPaging(null, dao.getCount());		
		assertEquals(1, vo.getEndPage());
		assertEquals(0, vo.getPageNum());		
	}
}
