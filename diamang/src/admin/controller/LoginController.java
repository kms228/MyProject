package admin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.dao.AdminDao;
@WebServlet("/login.do")
public class LoginController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cmd = request.getParameter("cmd");
		
		if(cmd.equals("login")) {
			login(request,response);
			System.out.println("로그인을 위한 입력한 정보확인");
		
		}else if(cmd.equals("loginForm")) {
			response.sendRedirect(request.getContextPath()+"/admin/page/login_kms.jsp");
			System.out.println("로그인창으로 이동");
		
		}else if(cmd.equals("logout")) {
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect(request.getContextPath()+"/admin/layout_kms.jsp");
		}
	}
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		System.out.println(id);
		System.out.println(pwd);
		AdminDao dao = new AdminDao();
		int n = dao.login(id, pwd);
		
		if(n==1) {//회원인경우
			HttpSession session = request.getSession();
			session.setAttribute("id",id);
			//layout 페이지로 이동하기
			response.sendRedirect(request.getContextPath()+"/admin/layout_kms.jsp?page=loginOk&id="+id);
		
		}else if(n==0) {//회원이아닌경우
			request.setAttribute("errMsg", "아이디 또는 비밀번호가 일치하지 않아요.");
			request.getRequestDispatcher("/admin/page/layout_kms.jsp").forward(request, response);
		
		}else {//오류가 발생된 경우
			request.setAttribute("errMsg", "오류로 인해 로그인에 실패했습니다..");
			request.getRequestDispatcher("/admin/page/layout_kms.jsp").forward(request, response);
		}
	}
}
