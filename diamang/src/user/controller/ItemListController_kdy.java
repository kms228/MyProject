package user.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.dao.ItemListDao;
import user.dao.ItemListDao_kdy;
import user.vo.ItemListVo_kdy;

@WebServlet("/itemList.do")
public class ItemListController_kdy extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String cmd = req.getParameter("cmd");
		if(cmd.equals("earing")) {
			earingList(req, resp);
		}
	}
	
	private void earingList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ItemListDao_kdy dao = new ItemListDao_kdy();
		ArrayList<ItemListVo_kdy> list = dao.earingList();
		req.setAttribute("list", list);
		req.getRequestDispatcher("/move.do?cmd=earing").forward(req, resp);
		
	}
}
