package user.controller;

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
		String url = "page/main_kdy.jsp";
		if(cmd==null) { //처음 접속할 때
			System.out.println(2);
			req.setAttribute("cmd", url);
			req.getRequestDispatcher("/user/layout_kdy.jsp").forward(req, resp);
		}else if(cmd.equals("review_main")) { //리뷰 페이지로
			url = "page/review/review_main_kdy.jsp";
			req.setAttribute("cmd", url);
			req.getRequestDispatcher("/user/layout_kdy.jsp").forward(req, resp);
		}else if(cmd.equals("review_write")){ //리뷰 페이지의 글쓰기 페이지로
			url = "page/review/review_write_kdy.jsp";
			req.setAttribute("cmd", url);
			req.getRequestDispatcher("/user/layout_kdy.jsp").forward(req, resp);
		}else if(cmd.equals("result")) { //리뷰 페이지의 결과 페이지로
			url = "page/review/result_kdy.jsp";
			req.setAttribute("cmd", url);
			req.getRequestDispatcher("user/layout_kdy.jsp").forward(req, resp);
		}else if(cmd.equals("rv_detail")) { //리뷰 페이지의 상세 글보기 페이지로
			url = "page/review/review_detail.jsp";
			req.setAttribute("cmd", url);
			req.getRequestDispatcher("user/layout_kdy.jsp").forward(req, resp);
		}
	}
}
