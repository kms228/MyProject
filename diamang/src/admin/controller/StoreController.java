package admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.dao.BasicInfoDao;
import admin.vo.BasicInfoVo;
@WebServlet("/store")
public class StoreController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		if(id==null) {
			request.setAttribute("errMsg", "로그인 후 이용가능합니다.");
			request.getRequestDispatcher("/admin/layout_kms.jsp?page=page/home_kms.jsp").forward(request, response);
		
		}else {
			String cmd = (String)request.getParameter("cmd");
			if(cmd.equals("storeMenu")) {
				System.out.println("itemController:itemMenu");
				storeMenu(request, response);
			
			}else if(cmd.equals("basicInfo")) {
				//request.getRequestDispatcher("/admin/layout_kms.jsp?page=store/basicInfo.jsp").forward(request, response);
				getlist(request,response);
				
			}else if(cmd.equals("getlist")) {
				getlist(request,response);
				
			}else if(cmd.equals("goChange")) {
				goChange(request,response);
			
			}else if(cmd.equals("insert")) {
				insert(request,response);
			
			}else if(cmd.equals("del")) {
				del(request,response);
			}else if(cmd.equals("getlist2")) {
				getlist2(request,response);
				
			}
		}
	}
	
	private void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		BasicInfoDao dao = BasicInfoDao.getInstance();
		int n = dao.delete(num);
		if(n>0) {
			System.out.println("삭제성공");
			getlist(request,response);
		}else {
			System.out.println("삭제실패");
		}
	}
	
	private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String info = request.getParameter("info1");
		BasicInfoDao dao = BasicInfoDao.getInstance();
		BasicInfoVo vo = new BasicInfoVo(info, 0);
		int n = dao.insert(vo);
		if(n>0) {
			System.out.println("추가성공");
			getlist(request,response);
		}else {
			System.out.println("추가실패");
		}
	}
	private void goChange(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int num = Integer.parseInt(request.getParameter("num"));
		String info = request.getParameter("info");
		System.out.println(info);
		
		BasicInfoVo vo = new BasicInfoVo(info, num);
		BasicInfoDao dao = BasicInfoDao.getInstance();
		int n = dao.update(vo);
		if(n>0) {
			System.out.println("수정성공");
			getlist(request,response);
		}else {
			System.out.println("수정실패");
		}
	}
	private void getlist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<BasicInfoVo> infoList = new ArrayList<>();
		BasicInfoDao dao = BasicInfoDao.getInstance();
		infoList = dao.getInfo();
		request.setAttribute("list", infoList);
		request.getRequestDispatcher("/admin/layout_kms.jsp?page=store/basicInfo.jsp").forward(request, response);
	}
	
	private void getlist2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<BasicInfoVo> infoList2 = new ArrayList<>();
		BasicInfoDao dao = BasicInfoDao.getInstance();
		infoList2 = dao.getInfo();
		request.setAttribute("list2", infoList2);
		request.getRequestDispatcher("/user/page/footer_kdy.jsp").forward(request, response);
	}
	
	private void storeMenu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		pw.close();
	}
}
