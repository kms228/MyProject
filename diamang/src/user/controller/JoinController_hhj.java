package user.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.dao.MembersDao_hhj;
import user.vo.MemversVo;
@WebServlet("/JoinController.do")
public class JoinController_hhj extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd = request.getParameter("cmd");
		String context = request.getContextPath();//컨텍스트
		System.out.println("context:" + context);
		if (cmd.equals("insert")) {
			response.sendRedirect(context + "/user/join.jsp");
		}else if(cmd.equals("insertOk")) {
			insert(request,response);
		}
	}//service
	
	private void insert(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String birthday = request.getParameter("birthday");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		
		user.vo.MemversVo user = new MemversVo(0, id, pwd, name, birthday, email, address, phone,0, null);
		MembersDao_hhj dao = new MembersDao_hhj();
		int n = dao.insert(user);
		
		if(n>0) {
			request.setAttribute("result", "success");
		}else {
			request.setAttribute("result", "fall");
		}
		RequestDispatcher rd = request.getRequestDispatcher("/user/성공?");
		rd.forward(request,response);
		
	}
	
	
	
}//class
