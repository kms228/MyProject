package user.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.dao.MainPageDao_kdy;
import user.vo.ItemListVo_kdy;

@WebServlet("/main.do")
public class MainPageController_kdy extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MainPageDao_kdy dao = new MainPageDao_kdy();
		ArrayList<ItemListVo_kdy> list = dao.NewItem();
		ArrayList<ItemListVo_kdy> list1 = dao.earItem();	
		ArrayList<ItemListVo_kdy> list3 = dao.neckItem();
		ArrayList<ItemListVo_kdy> list4 = dao.ringItem();
		ArrayList<ItemListVo_kdy> list5 = dao.coupleItem();
		
		req.setAttribute("list", list);
		req.setAttribute("list1", list1);
		req.setAttribute("list3", list3);
		req.setAttribute("list4", list4);
		req.setAttribute("list5", list5);
		req.getRequestDispatcher("/move.do?cmd=main").forward(req, resp);
		
	}
}
