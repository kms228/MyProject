package admin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.dao.AdminDao;
@WebServlet("/login.do")
public class LoginController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		AdminDao dao = new AdminDao();
		int n = dao.login(id, pwd);
		if(n>0) {
		//	request.setAttribute();
			
		//	RequestDispatcher rd = request.getRequestDispatcher();
		//	rd.forward(request, response);
		}else {
			
		
		
		}
	}
}
