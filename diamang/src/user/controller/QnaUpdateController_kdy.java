package user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.dao.QnaBoardDao_kdy;
import user.vo.QnaBoardVo_kdy;

@WebServlet("/qna_update.do")
public class QnaUpdateController_kdy extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cmd = req.getParameter("cmd");
		if(cmd.equals("update")) {
			int qnum = Integer.parseInt(req.getParameter("qnum"));
			QnaBoardDao_kdy dao = new QnaBoardDao_kdy();
			QnaBoardVo_kdy vo = dao.getInfo(qnum);
			
			req.setAttribute("vo", vo);
			req.setAttribute("qnum", qnum);
			req.getRequestDispatcher("/move.do?cmd=qna_update").forward(req, resp);	
		}else {
			updateOk(req,resp);
		}
		
	}
	
	private void updateOk(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		int qnum = Integer.parseInt(req.getParameter("qnum"));
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		System.out.println("¸»:"+content);
		System.out.println("title:"+title);
		
		QnaBoardVo_kdy vo = new QnaBoardVo_kdy(qnum,title,content);
		QnaBoardDao_kdy dao = new QnaBoardDao_kdy();
		int n = dao.update(vo);
		String result2="fail";
		if(n>0) {
			result2="success";
		}
		req.setAttribute("result2", result2);
		req.getRequestDispatcher("move.do?cmd=qna_result").forward(req, resp);
	}
}
