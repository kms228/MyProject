package user.diamang.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PageMove_kdy extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		System.out.println(1);
		String cmd = req.getParameter("cmd");
		if(cmd==null) {
			System.out.println(2);
			String url = "page/main_kdy.jsp";
			req.setAttribute("cmd", url);
			req.getRequestDispatcher("/user/layout_kdy.jsp").forward(req, resp);
		}
	}
}
