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
import user.dao.QnaBoardDao_kdy;
import user.vo.QnaBoardVo_kdy;
import user.vo.QnaImgVo_kdy;


@WebServlet("/qnaInsert.do")
public class QnaInsertController_kdy extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/move.do?cmd=qna_write").forward(req, resp);
	}
	
	//글 업로드
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		System.out.println("qna 컨트롤러 접속");
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
		String title = mr.getParameter("title");
		String content = mr.getParameter("content");
		String snum = mr.getParameter("qnum");
		String writer="";
		//세션 검사
		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("id");
		
		//id로 회원 번호 찾는 메소드 불러오기
		MembersDao_kdy dao=new MembersDao_kdy();
		int mnum = dao.searchMnum(id);
		if(mnum<0) {
			System.out.println("id 검색 오류");
		}
		
		int qnum = 0;
		int ref = 0;
		int lev = 0;
		int step = 0;
		
		if(snum!=null && !snum.equals("")) { //답글인 경우
			qnum = Integer.parseInt(snum);
			ref = Integer.parseInt(mr.getParameter("ref"));
			lev = Integer.parseInt(mr.getParameter("lev"));
			step = Integer.parseInt(mr.getParameter("step"));
		}
		QnaBoardVo_kdy vo = new QnaBoardVo_kdy(qnum, mnum, title, content, null, 0, ref, lev, step);
		QnaBoardDao_kdy dao2 = new QnaBoardDao_kdy();
		int n = dao2.insert(vo);
		int rn = dao2.getMaxNum();
		QnaImgVo_kdy imgvo = new QnaImgVo_kdy(0,rn,savename);
		dao2.imgUpload(imgvo);
		
		String result = "fail";
		if(n>0) {
			result = "success";
		}
		req.setAttribute("result", result);
		req.getRequestDispatcher("move.do?cmd=qna_result").forward(req, resp);
	}
}
	
