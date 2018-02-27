package user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.dao.MembersDao_kdy;
import user.dao.RvBoardDao_kdy;
import user.dao.RvItemDao_kdy;
import user.vo.MembersVo_kdy;
import user.vo.RvBoardVo_kdy;
import user.vo.RvItemVo_kdy;

@WebServlet("/rv_detail.do")
public class RvDetailController_kdy extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int rv_num = Integer.parseInt(req.getParameter("rv_num"));
		int pnum = Integer.parseInt(req.getParameter("pnum"));
		RvBoardDao_kdy dao = new RvBoardDao_kdy();
		dao.updateHit(rv_num);//조회수 늘리기
		RvBoardVo_kdy vo = dao.getInfo(rv_num); //review 테이블에 저장된 내용 가져오기
		MembersDao_kdy dao3 =new MembersDao_kdy();
		MembersVo_kdy vo3= dao3.MembersInfo(vo.getMnum()); //mnum으로 id 가져오기
		String id = vo3.getId();
		
		RvItemDao_kdy dao2=new RvItemDao_kdy();
		RvItemVo_kdy vo2 = dao2.itemInfo2(pnum); //상품 상세정보 가져오기
			
		req.setAttribute("vo", vo);
		req.setAttribute("vo2", vo2);
		req.setAttribute("rv_num", rv_num);
		req.setAttribute("id", id);
		req.getRequestDispatcher("/move.do?cmd=review_detail").forward(req, resp);
		
	}
}
