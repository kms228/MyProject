package shs.admin.controller.members;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shs.admin.dao.members.MembersgradeDao;
import shs.admin.dao.members.MembersmainDao;
import shs.admin.vo.members.MembersVo;
import shs.admin.vo.members.MembersgradeVo;
import shs.admin.vo.members.MembersmainVo;

@WebServlet("/admin/members.do")
public class MembersController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cmd = req.getParameter("cmd");
		if(cmd.equals("membersmain")) {			
			goMain(req,resp);
		} else if(cmd.equals("membersgrade")) {
			goGrade(req,resp);
		} else if(cmd.equals("memberssearch")) {
			goSearch(req,resp);
		}
	}	
	//memberssearch.jsp
	private void goSearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MembersgradeDao dao = new MembersgradeDao();
		ArrayList<MembersgradeVo> list = dao.getGradeinfo();
		req.setAttribute("list", list);
		
		req.getRequestDispatcher("/admin/members/memberssearch.jsp").forward(req, resp);
	}
	//membersmain.jsp
	private void goMain(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MembersmainDao dao = new MembersmainDao();
		MembersmainVo vo1 = dao.getUserval();
		ArrayList<MembersVo> list = dao.getUserinfo();
		req.setAttribute("vo1", vo1);
		req.setAttribute("list", list);
		
		req.getRequestDispatcher("/admin/members/membersmain.jsp").forward(req, resp);
	}
	//membersgrade.jsp
	private void goGrade(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MembersgradeDao dao = new MembersgradeDao();
		ArrayList<MembersgradeVo> list = dao.getGradeinfo();
		req.setAttribute("list", list);
		
		req.getRequestDispatcher("/admin/members/membersgrade.jsp").forward(req, resp);
	}
}
