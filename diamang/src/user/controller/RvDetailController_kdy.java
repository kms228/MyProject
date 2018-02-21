package user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.dao.RvBoardDao_kdy;
import user.vo.RvBoardVo_kdy;

@WebServlet("/rv_detail.do")
public class RvDetailController_kdy extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int rv_num = Integer.parseInt(req.getParameter("rv_num"));
		RvBoardDao_kdy dao = new RvBoardDao_kdy();
		dao.updateHit(rv_num);
		RvBoardVo_kdy vo = dao.getInfo(rv_num);
		req.setAttribute("vo", vo);
		req.setAttribute("rv_num", rv_num);
		req.getRequestDispatcher("/move.do?cmd=review_detail").forward(req, resp);
		
	}
}
