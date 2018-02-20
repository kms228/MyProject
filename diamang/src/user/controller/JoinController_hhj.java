package user.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		}else if(cmd.equals("login")) {
			login(request,response);
		}else if (cmd.equals("logout")) {
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect(request.getContextPath()+"/user/login_hhj.jsp");
		}else if (cmd.equals("update")) {
			update(request,response);
		}else if(cmd.equals("updateOk")) {
			updateOk(request,response);
		}
	
	}//service
	
	private void updateOk(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		int mnum=Integer.parseInt(request.getParameter("mnum"));
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");
		String name=request.getParameter("name");
		String birthday=request.getParameter("birthday");
		String email=request.getParameter("email");
		String address=request.getParameter("address");
		String phone=request.getParameter("phone");
		String gnum=request.getParameter("gnum");
		String joindate=request.getParameter("joindate");
		user.vo.MemversVo update = new MemversVo(mnum, id, pwd, name, birthday, email, address, phone, gnum, joindate);
		MembersDao_hhj dao = new MembersDao_hhj();
		int n = dao.update(update);
		
		if(n>0) {
			response.sendRedirect(request.getContextPath()+"/user/login_hhj.jsp");
		}else {
			request.setAttribute("fail", "수정실패");
			request.getRequestDispatcher("/users/result.jsp").forward(request, response);
		}
	}

	
	private void update(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		MembersDao_hhj dao = new MembersDao_hhj();
		user.vo.MemversVo user = dao.getinfo(id);
		//
		request.setAttribute("user", user);
		//
		RequestDispatcher rd = request.getRequestDispatcher("/user/updateMembers_hhj.jsp");
		rd.forward(request, response);
	}

	
	
	private void login(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String id=request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		//map에 아이디 비번 넣기
		HashMap<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("pwd",pwd);
		//dao통해서 검사후 세션에 넣기
		MembersDao_hhj dao= new MembersDao_hhj();
		int n = dao.login(map);
		
		if(n==1) {	//회원O
			HttpSession session = request.getSession();  
			session.setAttribute("id", id);
			//이동할곳 향후수정
			response.sendRedirect("user/login_hhj.jsp");  
		}else if(n==0) {	//회원X
			request.setAttribute("errmsg", "아이디 또는 비밀번호가 존재하지 않습니다.");
			request.getRequestDispatcher("회원X오류 페이지이동").forward(request,response);
		}else { //오류데쓰네
			request.setAttribute("errMsg","오류로 인해 로그인에 실패하였습니다.");
			request.getRequestDispatcher("회원X오류 페이지이동").forward(request, response);
		}
	}
	
	private void insert(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String birthday = request.getParameter("birthday");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		
		user.vo.MemversVo user = new MemversVo(0, id, pwd, name, birthday, email, address, phone,null, null);
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
