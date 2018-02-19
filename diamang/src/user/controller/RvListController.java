package user.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.dao.RvBoardDao_kdy;
import user.vo.RvBoardVo_kdy;

@WebServlet("/review_list.do")
public class RvListController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				//페이지 번호
				String spageNum=req.getParameter("pageNum");
				int pageNum=1;
				if(spageNum!=null) {
					pageNum=Integer.parseInt(spageNum);
				}
				//목록에 10개의 글 보이게 설정
				int startRow=(pageNum-1)*10+1;
				int endRow=startRow+9;
				RvBoardDao_kdy dao = new RvBoardDao_kdy();
				ArrayList<RvBoardVo_kdy> list=dao.list(startRow,endRow);
				//10페이지씩 보이게 페이징 처리
				int pageCount = (int)Math.ceil(dao.getCount()/10.0);
				System.out.println("페이지수:"+pageCount);
				int startPage=((pageNum-1)/10*10)+1; 
				int endPage=startPage+9;
				if(pageCount<endPage) {
					endPage=pageCount;
				}
				
				req.setAttribute("list", list);
				req.setAttribute("pageCount", pageCount);
				req.setAttribute("startPage", startPage);
				req.setAttribute("endPage", endPage);
				req.setAttribute("pageNum", pageNum);
				req.getRequestDispatcher("move.do?cmd=review_main").forward(req, resp);
	}
}
