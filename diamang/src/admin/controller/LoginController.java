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
			System.out.println("loginController:로그인을 위한 입력한 정보확인");
				}else if(cmd.equals("logout")) {
			System.out.println("loginController:로그아웃");
			HttpSession session = request.getSession();
			session.invalidate();
			System.out.println("loginController:로그아웃 후 기본페이지로 이동");
			response.sendRedirect(request.getContextPath()+"/admin/layout_kms.jsp");
		}
	}
	
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		System.out.println("loginController.id : "+id);
		System.out.println("loginController.pwd : "+pwd);
		AdminDao dao = new AdminDao();
		int n = dao.login(id, pwd);
		
		if(n>0) {//회원인경우
			HttpSession session = request.getSession();
			session.setAttribute("id",id);
			//layout 페이지로 이동하기
			System.out.println("loginController:로그인 완료 후 페이지이동");
			response.sendRedirect(request.getContextPath()+"/admin/layout_kms.jsp?page=page/loginOk.jsp");
		
		}else if(n==0) {//회원이아닌경우
			System.out.println("loginController:아이디 또는 비밀번호가 불일치");
			System.out.println("errMsg 적용");
			request.setAttribute("errMsg", "아이디 또는 비밀번호가 일치하지 않아요.");
			
			request.getRequestDispatcher("/admin/layout_kms.jsp?page=page/home_kms.jsp").forward(request, response);
			System.out.println("errMsg 적용 후 페이지 이동완료");
		}else {//오류가 발생된 경우
			System.out.println("loginController:로그인오류");
			request.setAttribute("errMsg", "오류로 인해 로그인에 실패했습니다..");
			request.getRequestDispatcher("/admin/layout_kms.jsp").forward(request, response);
		}
	}
}
