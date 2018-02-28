package shs.admin.junit;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import shs.admin.dao.board.BoardqnaDao;
import shs.admin.etc.PagingBot;
import shs.admin.vo.paging.PagingVo;

public class BoardqnaTest {
	public BoardqnaTest() {}	
	private BoardqnaDao dao;
	
//	@BeforeClass
//	public static void setUpClass() {
//		try {
//			System.setProperty(Context.INITIAL_CONTEXT_FACTORY, 
//					"org.apache.naming.java.JavaURLContextFactory");
//			System.setProperty(Context.URL_PKG_PREFIXES, 
//					"org.apache.naming");
//			InitialContext ic = new InitialContext();
//			
//			ic.createSubcontext("java:");
//			ic.createSubcontext("java:/comp");
//			ic.createSubcontext("java:/comp/env");
//			ic.createSubcontext("java:/comp/env/jdbc");
//			
//			ic.bind("java:/comp/env/jdbc/myoracle",DbcpBean.ds);						
//		} catch (NamingException ex) {
//			ex.getMessage();
//		}		
//	}
	@Before
	public void setDao() {
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
