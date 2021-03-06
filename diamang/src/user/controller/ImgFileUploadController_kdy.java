package user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import user.dao.MembersDao_kdy;
import user.dao.RvBoardDao_kdy;
import user.vo.RvBoardVo_kdy;
import user.vo.RvImageVo_kdy;
import user.vo.RvItemVo_kdy;

@WebServlet("/imgUpload.do")
public class ImgFileUploadController_kdy extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//req.getRequestDispatcher("/user/page/review/review_write_kdy.jsp").forward(req, resp);
		req.getRequestDispatcher("/move.do?cmd=review_write").forward(req, resp);
	}
	
	//글 업로드
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		System.out.println("imgFileUploadController 접속");
		//첨부파일 업로드
		String uploadPath=req.getServletContext().getRealPath("user/upload");
		MultipartRequest mr=new MultipartRequest(
				req,
				uploadPath,
				1024*1024*50,
				"utf-8",
				new DefaultFileRenamePolicy()
				);
		//파일명 가져오기
		String savename = mr.getFilesystemName("file1");
		
		//글 내용 가져오기
		int pnum = Integer.parseInt(mr.getParameter("hidPnum"));
		String title = mr.getParameter("title");
		String content = mr.getParameter("content");
		String snum = mr.getParameter("rv_num");
		String sstar = mr.getParameter("star");
		String writer="";
		String pwd="";
		int star = 0;
		if(sstar!=null) {
			star = Integer.parseInt(sstar);
		}
		//세션 검사
		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("id");
		if(id==null) { //비회원
			writer = mr.getParameter("writer");
			pwd= mr.getParameter("pwd");
		}
		
		//id로 회원 번호 찾는 메소드 불러오기
		MembersDao_kdy dao=new MembersDao_kdy();
		int mnum = dao.searchMnum(id);
		if(mnum<0) {
			System.out.println("id 검색 오류");
		}
		
		int rv_num = 0;
		int ref = 0;
		int lev = 0;
		int step = 0;
		
		if(snum!=null && !snum.equals("")) { //답글인 경우
			rv_num = Integer.parseInt(snum);
			ref = Integer.parseInt(mr.getParameter("ref"));
			lev = Integer.parseInt(mr.getParameter("lev"));
			step = Integer.parseInt(mr.getParameter("step"));
		}
		RvBoardVo_kdy vo = new RvBoardVo_kdy(rv_num, mnum, title, content, null, 0, ref, lev, step, star,pwd,pnum);
		RvBoardDao_kdy dao2=new RvBoardDao_kdy();
		int n = dao2.insert(vo);
		int rn = dao2.getMaxNum();
		RvImageVo_kdy imgvo = new RvImageVo_kdy(0,rn,savename);
		dao2.imgUpload(imgvo);
		dao2.reviewOk(pnum);
		
		String result = "fail";
		if(n>0) {
			result = "success";
		}
		req.setAttribute("result", result);
		req.getRequestDispatcher("move.do?cmd=result").forward(req, resp);
	}
}
	
