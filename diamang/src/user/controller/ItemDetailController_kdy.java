package user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.dao.ItemDao_kdy;
import user.vo.ItemListVo_kdy;

@WebServlet("/itemDetail.do")
public class ItemDetailController_kdy extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int pnum = Integer.parseInt(req.getParameter("pnum"));
		
		ItemDao_kdy dao=new ItemDao_kdy();
		ItemListVo_kdy vo = dao.itemDetail(pnum);
		req.setAttribute("vo", vo);
		req.getRequestDispatcher("/move.do?cmd=itemDetail").forward(req, resp);
	}
}
