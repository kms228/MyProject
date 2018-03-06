package user.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.dao.QnaBoardDao_kdy;
import user.dao.RvBoardDao_kdy;
import user.vo.QnaBoardVo_kdy;
import user.vo.RvBoardVo_kdy;

@WebServlet("/qna_list.do")
public class QnaListController_kdy extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				//������ ��ȣ
				String spageNum=req.getParameter("pageNum");
				int pageNum=1;
				if(spageNum!=null) {
					pageNum=Integer.parseInt(spageNum);
				}
				//��Ͽ� 10���� �� ���̰� ����
				int startRow=(pageNum-1)*10+1;
				int endRow=startRow+9;
				QnaBoardDao_kdy dao = new QnaBoardDao_kdy();
				ArrayList<QnaBoardVo_kdy> list=dao.list(startRow,endRow);
				//10�������� ���̰� ����¡ ó��
				int pageCount = (int)Math.ceil(dao.getCount()/10.0);
				System.out.println("��������:"+pageCount);
				int startPage=((pageNum-1)/10*10)+1; 
				int endPage=startPage+9;
				if(pageCount<endPage) {
					endPage=pageCount;
				}
				req.setCharacterEncoding("utf-8");
				req.setAttribute("list", list);
				req.setAttribute("pageCount", pageCount);
				req.setAttribute("startPage", startPage);
				req.setAttribute("endPage", endPage);
				req.setAttribute("pageNum", pageNum);
				req.getRequestDispatcher("/move.do?cmd=qna_main").forward(req, resp);
	}
}
