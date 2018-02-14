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
			System.out.println("�α����� ���� �Է��� ����Ȯ��");
		
		}else if(cmd.equals("loginForm")) {
			response.sendRedirect(request.getContextPath()+"/admin/page/login_kms.jsp");
			System.out.println("�α���â���� �̵�");
		
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
		
		if(n==1) {//ȸ���ΰ��
			HttpSession session = request.getSession();
			session.setAttribute("id",id);
			//layout �������� �̵��ϱ�
			response.sendRedirect(request.getContextPath()+"/admin/layout_kms.jsp?page=loginOk&id="+id);
		
		}else if(n==0) {//ȸ���̾ƴѰ��
			request.setAttribute("errMsg", "���̵� �Ǵ� ��й�ȣ�� ��ġ���� �ʾƿ�.");
			request.getRequestDispatcher("/admin/page/layout_kms.jsp").forward(request, response);
		
		}else {//������ �߻��� ���
			request.setAttribute("errMsg", "������ ���� �α��ο� �����߽��ϴ�..");
			request.getRequestDispatcher("/admin/page/layout_kms.jsp").forward(request, response);
		}
	}
}
