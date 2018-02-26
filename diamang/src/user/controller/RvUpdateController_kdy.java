package user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.dao.RvBoardDao_kdy;
import user.dao.RvItemDao_kdy;
import user.vo.RvBoardVo_kdy;
import user.vo.RvItemVo_kdy;

@WebServlet("/rv_update.do")
public class RvUpdateController_kdy extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cmd = req.getParameter("cmd");
		if(cmd.equals("update")) {
			int rv_num = Integer.parseInt(req.getParameter("rv_num"));
			int pnum = Integer.parseInt(req.getParameter("pnum"));
			RvBoardDao_kdy dao=new RvBoardDao_kdy();
			RvBoardVo_kdy vo = dao.getInfo(rv_num);
			
			RvItemDao_kdy dao2=new RvItemDao_kdy();  //상품 상세정보 가져오기
			RvItemVo_kdy vo2 = dao2.itemInfo2(pnum);
			
			req.setAttribute("vo", vo);
			req.setAttribute("vo2", vo2);
			req.setAttribute("rv_num", rv_num);
			req.getRequestDispatcher("/move.do?cmd=review_update").forward(req, resp);	
		}else {
			updateOk(req,resp);
		}
		
	}
	
	private void updateOk(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int rv_num = Integer.parseInt(req.getParameter("rv_num"));
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String sstar = req.getParameter("star");
		System.out.println("title:"+title);
		
		int star = 0;
		if(sstar!=null) {
			star = Integer.parseInt(sstar);
		}
		
		RvBoardVo_kdy vo=new RvBoardVo_kdy(rv_num,title,content,star);
		RvBoardDao_kdy dao=new RvBoardDao_kdy();
		int n = dao.update(vo);
		String result2="fail";
		if(n>0) {
			result2="success";
		}
		req.setAttribute("result2", result2);
		req.getRequestDispatcher("move.do?cmd=result").forward(req, resp);
	}
}
