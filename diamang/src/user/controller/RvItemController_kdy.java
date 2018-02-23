package user.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.dao.MembersDao_kdy;
import user.dao.RvItemDao_kdy;
import user.vo.RvItemVo_kdy;

@WebServlet("/itemInfo.do")
public class RvItemController_kdy extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("id");
		MembersDao_kdy dao=new MembersDao_kdy();
		int mnum = dao.searchMnum(id);
		RvItemDao_kdy dao2=new RvItemDao_kdy();
		ArrayList<RvItemVo_kdy> list = new ArrayList<>();
		list = dao2.itemInfo(mnum);
		System.out.println("¿Ï·á");
		req.setAttribute("list", list);
		req.getRequestDispatcher("/user/page/review/itemInfo.jsp").forward(req, resp);
	}
}
