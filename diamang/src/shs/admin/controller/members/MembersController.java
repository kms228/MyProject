package shs.admin.controller.members;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shs.admin.dao.members.MembersgradeDao;
import shs.admin.dao.members.MembersinfoDao;
import shs.admin.dao.members.MembersmainDao;
import shs.admin.etc.PagingBot;
import shs.admin.vo.members.MembersSearchVo;
import shs.admin.vo.members.MembersVo;
import shs.admin.vo.members.MembersgradeVo;
import shs.admin.vo.members.MembersmainVo;
import shs.admin.vo.paging.PagingVo;

@WebServlet("/admin/members.do")
public class MembersController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cmd = req.getParameter("cmd");
		System.out.println("cmd :"+cmd);
		if(cmd.equals("membersmain")) {			
			goMain(req,resp);
		} else if(cmd.equals("membersgrade")) {
			goGrade(req,resp);
		} else if(cmd.equals("membersinfo")) {
			goSearch(req,resp);
		} else if(cmd.equals("infosearch")) {
			infoSearch(req,resp);
		}
	}		
	//membersinfo.jsp
	private void goSearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MembersgradeDao dao = new MembersgradeDao();
		ArrayList<MembersgradeVo> grade = dao.getGradeinfo();
		req.setAttribute("grade", grade);		
		req.getRequestDispatcher("/admin/members/membersinfo.jsp").forward(req, resp);
	}
	private void infoSearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String optName = req.getParameter("optName");
		String optValue = req.getParameter("optValue");
		String gnum = req.getParameter("grade");
//		String pnum = req.getParameter("good");	
		String pageNum = req.getParameter("pageNum");
		MembersSearchVo vo = new MembersSearchVo(optName, optValue, gnum, null);		
		MembersinfoDao dao = new MembersinfoDao();
		PagingBot bot = new PagingBot(20,10);
		PagingVo pageVo =  bot.calPaging(pageNum, dao.getCount(vo));
		ArrayList<MembersVo> list = dao.search(vo, pageVo);		
		req.setAttribute("search", vo);
		req.setAttribute("list", list);
		req.setAttribute("pageVo", pageVo);
		goSearch(req,resp);
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
