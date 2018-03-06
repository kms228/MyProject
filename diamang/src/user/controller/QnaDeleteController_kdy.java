package user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.dao.MembersDao_kdy;
import user.dao.QnaBoardDao_kdy;
import user.dao.RvBoardDao_kdy;
import user.vo.BuyBoardVo_kdy;
import user.vo.QnaBoardVo_kdy;
import user.vo.RvBoardVo_kdy;

@WebServlet("/qna_delete.do")
public class QnaDeleteController_kdy extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int qnum = Integer.parseInt(req.getParameter("qnum"));
		QnaBoardDao_kdy dao = new QnaBoardDao_kdy();
		
		dao.delete(qnum);
		dao.commDelete(qnum);
		
		req.getRequestDispatcher("qna_list.do").forward(req, resp);
		
	}
}