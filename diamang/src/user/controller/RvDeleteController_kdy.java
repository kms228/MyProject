package user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.dao.MembersDao_kdy;
import user.dao.RvBoardDao_kdy;
import user.vo.BuyBoardVo_kdy;
import user.vo.RvBoardVo_kdy;

@WebServlet("/rv_delete.do")
public class RvDeleteController_kdy extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int rv_num = Integer.parseInt(req.getParameter("rv_num"));
		int pnum = Integer.parseInt(req.getParameter("pnum"));
		RvBoardDao_kdy dao = new RvBoardDao_kdy();
		RvBoardVo_kdy vo = dao.getInfo(rv_num);
		int mnum=vo.getMnum();
		
		MembersDao_kdy dao2=new MembersDao_kdy();
		BuyBoardVo_kdy vo2 = dao2.buyBoardInfo(mnum);
		int buy_num=vo2.getBuy_num();
		
		dao.delete(rv_num);
		dao.deleteOk(buy_num, pnum);
		dao.commDelete(rv_num);
		
		req.getRequestDispatcher("review_list.do").forward(req, resp);
		
	}
}
