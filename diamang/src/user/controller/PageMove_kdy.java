package user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/move.do")
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
		}else if(cmd.equals("main")){//메인으로 돌아올 때
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
			req.getRequestDispatcher("/user/layout_kdy.jsp").forward(req, resp);
		}else if(cmd.equals("review_detail")) { //리뷰 페이지의 상세 글보기 페이지로
			url = "page/review/review_detail_kdy.jsp";
			req.setAttribute("cmd", url);
			req.getRequestDispatcher("/user/layout_kdy.jsp").forward(req, resp);
		}else if(cmd.equals("login")) { //로그인 페이지로
			url = "login_hhj.jsp";
			req.setAttribute("cmd", url);
			req.getRequestDispatcher("/user/layout_kdy.jsp").forward(req, resp);
		}else if(cmd.equals("logout")) { //로그아웃 페이지로
			
		}else if(cmd.equals("join")) { //회원가입 페이지로
			url = "join_hhj.jsp";
			req.setAttribute("cmd", url);
			req.getRequestDispatcher("/user/layout_kdy.jsp").forward(req, resp);
		}else if(cmd.equals("review_update")) {
			req.getRequestDispatcher("/user/page/review/review_update_kdy.jsp").forward(req, resp);
		}else if(cmd.equals("itemList")) {	//각 상품 목록 페이지로
			url = "page/item/item_kdy.jsp";
			req.setAttribute("cmd", url);
			req.getRequestDispatcher("/user/layout_kdy.jsp").forward(req, resp);
		}else if(cmd.equals("itemDetail")) {
			url = "page/item/item_detail_kdy.jsp";
			req.setAttribute("cmd", url);
			req.getRequestDispatcher("/user/layout_kdy.jsp").forward(req, resp);
		}else if(cmd.equals("orderCheck")) {
			url = "page/order/order_kdy.jsp";
			req.setAttribute("cmd", url);
			req.getRequestDispatcher("/user/layout_kdy.jsp").forward(req, resp);
		}else if(cmd.equals("cartCheck")) { //카트에 상품 담김 확인 페이지
			url = "page/cart/cartOk_kdy.jsp";
			req.setAttribute("cmd", url);
			req.getRequestDispatcher("/user/layout_kdy.jsp").forward(req, resp);
		}else if(cmd.equals("cartList")) {
			url = "page/cart/cart_kdy.jsp";
			req.setAttribute("cmd", url);
			req.getRequestDispatcher("/user/layout_kdy.jsp").forward(req, resp);
		}else if(cmd.equals("orderResult")) { //주문완료 페이지
			url = "page/order/orderResult_kdy.jsp";
			req.setAttribute("cmd", url);
			req.getRequestDispatcher("/user/layout_kdy.jsp").forward(req, resp);
		}
	}
}
