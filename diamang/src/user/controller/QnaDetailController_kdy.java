package user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.dao.MembersDao_kdy;
import user.dao.QnaBoardDao_kdy;

import user.vo.MembersVo_kdy;
import user.vo.QnaBoardVo_kdy;


@WebServlet("/qna_detail.do")
public class QnaDetailController_kdy extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int qnum = Integer.parseInt(req.getParameter("qnum"));
		QnaBoardDao_kdy dao = new QnaBoardDao_kdy();
		dao.updateHit(qnum);//조회수 늘리기
		QnaBoardVo_kdy vo = dao.getInfo(qnum); //qna 테이블에 저장된 내용 가져오기
		MembersDao_kdy dao3 =new MembersDao_kdy();
		MembersVo_kdy vo3= dao3.MembersInfo(vo.getMnum()); //mnum으로 id 가져오기
		String id = vo3.getId();
			
		req.setAttribute("vo", vo);
		req.setAttribute("qnum", qnum);
		req.setAttribute("id", id);
		req.getRequestDispatcher("/move.do?cmd=qna_detail").forward(req, resp);
		
	}
}
