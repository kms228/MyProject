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
			System.out.println("loginController:�α����� ���� �Է��� ����Ȯ��");
		
		}else if(cmd.equals("logout")) {
			System.out.println("loginController:���:�α׾ƿ�");
			HttpSession session = request.getSession();
			session.invalidate();
			System.out.println("loginController:���:�α׾ƿ� �� �⺻�������� �̵�");
			response.sendRedirect(request.getContextPath()+"/admin/layout_kms.jsp?page=page/home_kms.jsp");
		}
	}
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		System.out.println(id);
		System.out.println(pwd);
		AdminDao dao = new AdminDao();
		int n = dao.login(id, pwd);
		
		if(n>0) {//ȸ���ΰ��
			HttpSession session = request.getSession();
			session.setAttribute("id",id);
			//layout �������� �̵��ϱ�
			System.out.println("loginController:�α��� �� ������ �̵� ��");
			response.sendRedirect(request.getContextPath()+"/admin/layout_kms.jsp?page=page/loginOk.jsp");
			System.out.println("loginController:�α��� �� ������ �̵� ��");
		}else if(n==0) {//ȸ���̾ƴѰ��
			request.setAttribute("errMsg", "���̵� �Ǵ� ��й�ȣ�� ��ġ���� �ʾƿ�.");
			request.getRequestDispatcher("/admin/page/layout_kms.jsp").forward(request, response);
		
		}else {//������ �߻��� ���
			request.setAttribute("errMsg", "������ ���� �α��ο� �����߽��ϴ�..");
			request.getRequestDispatcher("/admin/page/layout_kms.jsp").forward(request, response);
		}
	}
}
