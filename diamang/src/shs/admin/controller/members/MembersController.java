package shs.admin.controller.members;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shs.admin.dao.members.MembersgradeDao;
import shs.admin.dao.members.MembersinfoDao;
import shs.admin.dao.members.MembersmainDao;
import shs.admin.etc.DefaultGrade;
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
		req.setCharacterEncoding("utf-8");
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
		} else if(cmd.equals("goupdate")) {
			goGradeupdate(req,resp);
		} else if(cmd.equals("updateGrade")) {
			updateGrade(req,resp);
		} else if(cmd.equals("addgrade")) {
			addGrade(req,resp);			
		} else if(cmd.equals("defaultGrade")) {
			String gnum = req.getParameter("gnum");
			String realPath = getServletContext().getRealPath("");
			DefaultGrade.setDefaultGrade(gnum, realPath);
			goGrade(req, resp);
		}
	}					
	//membersinfo.jsp
	private void goSearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MembersinfoDao dao = new MembersinfoDao();
		ArrayList<MembersgradeVo> grade = dao.getGrade();		
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
		PagingBot bot = new PagingBot(1,2);
		PagingVo pageVo =  bot.calPaging(pageNum, dao.getCount(vo));
		ArrayList<MembersVo> list = dao.search(vo, pageVo);		
		req.setAttribute("search", vo);
		req.setAttribute("list", list);
		req.setAttribute("pageVo", pageVo);
		System.out.println("endPage : "+pageVo.getEndPage());
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
		System.out.println("go grade()!!");
		MembersgradeDao dao = new MembersgradeDao();
		ArrayList<MembersgradeVo> list = dao.getGradeinfo();
		req.setAttribute("list", list);
		req.setAttribute("defaultGnum", DefaultGrade.GNUM);
		req.getRequestDispatcher("/admin/members/membersgrade.jsp").forward(req, resp);
	}
	private void goGradeupdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		String gnum = req.getParameter("gnum");
		MembersgradeDao dao = new MembersgradeDao();
		MembersgradeVo vo = dao.getGrade(gnum);	
		req.setAttribute("grade",vo);
		req.getRequestDispatcher("/admin/members/uptgrade.jsp").forward(req, resp);
	}
	private void updateGrade(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String gnum = req.getParameter("gnum");
		String grade = req.getParameter("grade");
		String drate = req.getParameter("drate");
		MembersgradeVo vo = new MembersgradeVo(Integer.parseInt(gnum),grade,Integer.parseInt(drate),0);
		MembersgradeDao dao = new MembersgradeDao();
		int result = dao.updateGrade(vo);
		String msg = "회원등급 수정이 실패했습니다.";
		if(result>0) {
			msg = "회원등급 수정 완료";
		}
		req.setAttribute("msg", msg);
		req.getRequestDispatcher("/admin/members/uptgrade.jsp").forward(req, resp);
	}
	private void addGrade(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String grade = req.getParameter("grade");
		String drate = req.getParameter("drate");
		MembersgradeVo vo = new MembersgradeVo(0,grade,Integer.parseInt(drate),0);
		MembersgradeDao dao = new MembersgradeDao();
		int result = dao.addGrade(vo);
		String msg = "회원등급 생성이 실패했습니다.";
		if(result>0) {
			msg = "회원등급 생성 완료";
		}
		req.setAttribute("msg", msg);
		req.getRequestDispatcher("/admin/members/addgrade.jsp").forward(req, resp);
	}
}
