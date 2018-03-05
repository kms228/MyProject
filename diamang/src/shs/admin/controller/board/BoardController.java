package shs.admin.controller.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shs.admin.dao.board.BoardReviewDao;
import shs.admin.dao.board.BoardqnaDao;
import shs.admin.etc.PagingBot;
import shs.admin.vo.board.BoardReviewVo;
import shs.admin.vo.board.BoardqnaVo;
import shs.admin.vo.paging.PagingVo;

@WebServlet("/admin/board.do")
public class BoardController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("id");
		if(id==null) {
			req.setAttribute("errMsg", "로그인 후 이용가능합니다.");
			req.getRequestDispatcher("/admin/layout_kms.jsp?page=page/home_kms.jsp").forward(req, resp);
		
		}else {
		
			String cmd = req.getParameter("cmd");
			if(cmd.equals("boardnotice")) {
				
			} else if(cmd.equals("boardqna")) {
				goQna(req,resp);
				
			} else if(cmd.equals("boardreview")) {
				goReview(req,resp);
				
			} else if(cmd.equals("fillUp")) {
				String ref = req.getParameter("ref");
				String rv_num = req.getParameter("rv_num");
				String lev = req.getParameter("lev");
				String step = req.getParameter("step");
				resp.sendRedirect(req.getContextPath()+"/admin/board/fillupReview.jsp?ref="+ ref+"&rv_num="+rv_num+"&lev="+lev+"&step="+step);			
			
			} else if(cmd.equals("fillUp1")) {
				String refer = req.getParameter("refer");
				String qnum = req.getParameter("qnum");
				String lev = req.getParameter("lev");
				String step = req.getParameter("step");
				resp.sendRedirect(req.getContextPath()+"/admin/board/fillupQna.jsp?refer="+ refer+"&qnum="+qnum+"&lev="+lev+"&step="+step);			
			
			} else if(cmd.equals("fillupReview")) {
				fillupReview(req,resp);
			
			} else if(cmd.equals("fillupQna")) {
				fillupQna(req,resp);
			}
		}
	}		
	
	// review.jsp
	private void goReview(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		BoardReviewDao dao = new BoardReviewDao();
		//페이징 봇. new PagingBot(페이지 내의 글 숫자, 맨 밑에 페이지들(예)1~10)
		String pageNum = req.getParameter("pageNum");
		PagingBot bot = new PagingBot(2, 10);
		PagingVo pageVo = bot.calPaging(pageNum, dao.getCount());
		ArrayList<BoardReviewVo> list1 = dao.getTop5Today();
		ArrayList<BoardReviewVo> list2 = dao.getTop5Sevendays();
		ArrayList<BoardReviewVo> list3 = dao.getTop5Reviewcnt();
		ArrayList<BoardReviewVo> list = dao.getReview(pageVo);
		
		req.setAttribute("list1", list1);
		req.setAttribute("list2", list2);
		req.setAttribute("list3", list3);
		req.setAttribute("list", list);
		req.setAttribute("pageVo", pageVo);
		
		req.getRequestDispatcher("/admin/layout_kms.jsp?page=board/review.jsp").forward(req, resp);
	}
	
	private void fillupReview(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String ref = req.getParameter("ref");
		String rv_num = req.getParameter("rv_num");
		String lev = req.getParameter("lev");
		String step = req.getParameter("step");	
		String pwd = req.getParameter("pwd");
		BoardReviewVo vo = new BoardReviewVo(Integer.parseInt(rv_num), title, null, content, 0, 5, null, null, null, 0, Integer.parseInt(ref),Integer.parseInt(lev),Integer.parseInt(step),pwd);		
		BoardReviewDao dao = new BoardReviewDao();		
		int result = dao.fillupReview(vo);
		String msg = "답글 생성 중 에러 발생. 다시 시도해주세요.";
		if(result>0) {
			msg = "답글을 올렸습니다.";
		}
		req.setAttribute("msg", msg);
		req.getRequestDispatcher("/admin/board/fillupReview.jsp").forward(req, resp);
	}
	
	// qna.jsp
	private void goQna(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//		resp.sendRedirect("/diamang/admin/board/qna.jsp");
		BoardqnaDao dao = new BoardqnaDao();
		String pageNum = req.getParameter("pageNum");
		PagingBot bot = new PagingBot(2, 10);
		PagingVo pageVo = bot.calPaging(pageNum, dao.getCount());
		ArrayList<BoardqnaVo> list = dao.getList(pageVo);
		req.setAttribute("list", list);		
		req.setAttribute("pageVo", pageVo);
				
		req.getRequestDispatcher("/admin/layout_kms.jsp?page=board/qna.jsp").forward(req, resp);
		
	}
	
	private void fillupQna(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String refer = req.getParameter("refer");
		String qnum = req.getParameter("qnum");
		String lev = req.getParameter("lev");
		String step = req.getParameter("step");	
		String pwd = req.getParameter("pwd");

		BoardqnaVo vo = new BoardqnaVo(10000, title, content, Integer.parseInt(refer), Integer.parseInt(lev), Integer.parseInt(step));		
		BoardqnaDao dao = new BoardqnaDao();		
		int result = dao.fillupQna(vo);
		String msg = "답글 생성 중 에러 발생. 다시 시도해주세요.";
		
		if(result>0) {
			msg = "답글을 올렸습니다.";
		}
		
		req.setAttribute("msg", msg);
		req.getRequestDispatcher("/admin/board/fillupQna.jsp").forward(req, resp);
	}
}
