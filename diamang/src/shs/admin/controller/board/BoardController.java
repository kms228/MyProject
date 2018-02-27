package shs.admin.controller.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shs.admin.dao.board.BoardReviewDao;
import shs.admin.vo.board.BoardReviewVo;

@WebServlet("/admin/board.do")
public class BoardController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
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
		} else if(cmd.equals("fillupReview")) {
			fillupReview(req,resp);
		}
	}		
	// review.jsp
	private void goReview(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		BoardReviewDao dao = new BoardReviewDao();
		ArrayList<BoardReviewVo> list1 = dao.getTop5Today();
		ArrayList<BoardReviewVo> list2 = dao.getTop5Sevendays();
		ArrayList<BoardReviewVo> list3 = dao.getTop5Reviewcnt();
		ArrayList<BoardReviewVo> list = dao.getReview();
		req.setAttribute("list1", list1);
		req.setAttribute("list2", list2);
		req.setAttribute("list3", list3);
		req.setAttribute("list", list);
		req.getRequestDispatcher("/admin/board/review.jsp").forward(req, resp);		
	}
	private void fillupReview(HttpServletRequest req, HttpServletResponse resp) {
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String ref = req.getParameter("ref");
		String rv_num = req.getParameter("rv_num");
		String lev = req.getParameter("lev");
		String step = req.getParameter("step");
		BoardReviewVo vo = new BoardReviewVo(Integer.parseInt(rv_num), title, null, content, 0, 5, null, null, null, 0, Integer.parseInt(ref),Integer.parseInt(lev),Integer.parseInt(step));
		BoardReviewDao dao = new BoardReviewDao();		
		dao.fillupReview(vo);
	}
	// qna.jsp
	private void goQna(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.sendRedirect("/diamang/admin/board/qna.jsp");
		
	}	
}
