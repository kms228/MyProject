package user.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.dao.ItemListDao;
import user.dao.ItemDao_kdy;
import user.vo.ItemListVo_kdy;

@WebServlet("/itemList.do")
public class ItemListController_kdy extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		//페이징
		String spageNum=req.getParameter("pageNum");
		int pageNum=1;
	
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);	
		}
		int startRow=(pageNum-1)*20+1;
		int endRow=startRow+19;
		
		req.setAttribute("pageNum", pageNum);
		req.setAttribute("startRow", startRow);
		req.setAttribute("endRow", endRow);
		
		String cmd = req.getParameter("cmd");
		if(cmd.equals("ring")) {
			req.setAttribute("item_num", 1);
			req.setAttribute("item", cmd);
			itemList(req, resp);
		}else if(cmd.equals("necklace")) {
			req.setAttribute("item_num", 2);
			req.setAttribute("item", cmd);
			itemList(req, resp);
		}else if(cmd.equals("earring")) {
			req.setAttribute("item_num", 3);
			req.setAttribute("item", cmd);
			itemList(req, resp);
		}else if(cmd.equals("couple")) {
			req.setAttribute("item_num", 4);
			req.setAttribute("item", cmd);
			itemList(req, resp);
		}else if(cmd.equals("new")) { //신상품순
			int item_num = Integer.parseInt(req.getParameter("item_num"));
			req.setAttribute("item_num", item_num);
			req.setAttribute("order", "regdate desc");
			req.setAttribute("item", cmd);
			if(!req.getParameter("fieldnum").equals("")) {
				int fieldnum = Integer.parseInt(req.getParameter("fieldnum"));
				req.setAttribute("fieldnum", fieldnum);
				itemOrder(req,resp);
			}
			itemAllOrder(req, resp);
		}else if(cmd.equals("name")) { //상품명순
			int item_num = Integer.parseInt(req.getParameter("item_num"));
			req.setAttribute("item_num", item_num);
			req.setAttribute("order", "item_name");
			req.setAttribute("item", cmd);
			if(!req.getParameter("fieldnum").equals("")) {
				int fieldnum = Integer.parseInt(req.getParameter("fieldnum"));
				req.setAttribute("fieldnum", fieldnum);
				itemOrder(req,resp);
			}
			itemAllOrder(req, resp);
		}else if(cmd.equals("low")) {
			int item_num = Integer.parseInt(req.getParameter("item_num"));
			req.setAttribute("item_num", item_num);
			req.setAttribute("order", "price");
			req.setAttribute("item", cmd);
			if(!req.getParameter("fieldnum").equals("")) {
				int fieldnum = Integer.parseInt(req.getParameter("fieldnum"));
				req.setAttribute("fieldnum", fieldnum);
				itemOrder(req,resp);
			}
			itemAllOrder(req, resp);
		}else if(cmd.equals("high")) {
			int item_num = Integer.parseInt(req.getParameter("item_num"));
			req.setAttribute("item_num", item_num);
			req.setAttribute("order", "price desc");
			req.setAttribute("item", cmd);
			if(!req.getParameter("fieldnum").equals("")) {
				int fieldnum = Integer.parseInt(req.getParameter("fieldnum"));
				req.setAttribute("fieldnum", fieldnum);
				itemOrder(req,resp);
			}
			itemAllOrder(req, resp);
		}else if(cmd.equals("14k/18k")) { //14k/18k
			int item_num = Integer.parseInt(req.getParameter("item_num"));
			req.setAttribute("item_num", item_num);
			req.setAttribute("item", cmd);
			if(item_num==1) {
				req.setAttribute("fieldnum", 11);
			}else if(item_num==2) {
				req.setAttribute("fieldnum", 21);
			}else if(item_num==3) {
				req.setAttribute("fieldnum", 31);
			}else if(item_num==4) {
				req.setAttribute("fieldnum", 41);
			}
			itemField(req, resp);
		}else if(cmd.equals("diamond")) {
			int item_num = Integer.parseInt(req.getParameter("item_num"));
			req.setAttribute("item_num", item_num);
			req.setAttribute("item", cmd);
			if(item_num==1) {
				req.setAttribute("fieldnum", 12);
			}else if(item_num==2) {
				req.setAttribute("fieldnum", 22);
			}else if(item_num==4) {
				req.setAttribute("fieldnum", 42);
			}
			itemField(req, resp);
		}else if(cmd.equals("birth")) {
			int item_num = Integer.parseInt(req.getParameter("item_num"));
			req.setAttribute("item_num", item_num);
			req.setAttribute("item", cmd);
			if(item_num==1) {
				req.setAttribute("fieldnum", 13);
			}else if(item_num==2) {
				req.setAttribute("fieldnum", 23);
			}else if(item_num==3) {
				req.setAttribute("fieldnum", 32);
			}
			itemField(req, resp);
		}else if(cmd.equals("silver")) {
			int item_num = Integer.parseInt(req.getParameter("item_num"));
			req.setAttribute("item_num", item_num);
			req.setAttribute("item", cmd);
			if(item_num==1) {
				req.setAttribute("fieldnum", 14);
			}else if(item_num==4) {
				req.setAttribute("fieldnum", 43);
			}
			itemField(req, resp);
		}else if(cmd.equals("all")) {
			int item_num = Integer.parseInt(req.getParameter("item_num"));
			req.setAttribute("item_num", item_num);
			req.setAttribute("item", cmd);
			itemList(req,resp);
		}
	}
	
	private void itemList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int item_num = (int) req.getAttribute("item_num");
		
		int pageNum = (int) req.getAttribute("pageNum");
		int startRow = (int) req.getAttribute("startRow");
		int endRow = (int) req.getAttribute("endRow");
		
		ItemDao_kdy dao = new ItemDao_kdy();
		ArrayList<ItemListVo_kdy> list = dao.itemList(item_num,startRow,endRow);
		int cnt = dao.itemCount(item_num);
		int pageCount = (int)Math.ceil((cnt)/20.0);
		int startPage=((pageNum-1)/10*10)+1; //시작페이지
		int endPage=startPage+9;
		if(pageCount<endPage) {
			endPage=pageCount;
		}
		
		req.setAttribute("cnt", cnt);
		req.setAttribute("list", list);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("startPage", startPage);
		req.setAttribute("endPage", endPage);
		req.setAttribute("pageNum", pageNum);
		req.getRequestDispatcher("/move.do?cmd=itemList").forward(req, resp);
	}
	
	private void itemOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int item_num = (int) req.getAttribute("item_num");
		int fieldnum = (int) req.getAttribute("fieldnum");
		String order = (String) req.getAttribute("order");
		
		int pageNum = (int) req.getAttribute("pageNum");
		int startRow = (int) req.getAttribute("startRow");
		int endRow = (int) req.getAttribute("endRow");
		
		ItemDao_kdy dao = new ItemDao_kdy();
		ArrayList<ItemListVo_kdy> list = dao.itemOrder(item_num,fieldnum, order);
		int cnt = dao.itemCount(item_num);
		int pageCount = (int)Math.ceil((cnt)/20.0);
		int startPage=((pageNum-1)/10*10)+1; //시작페이지
		int endPage=startPage+9;
		if(pageCount<endPage) {
			endPage=pageCount;
		}
		
		req.setAttribute("cnt", cnt);
		req.setAttribute("list", list);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("startPage", startPage);
		req.setAttribute("endPage", endPage);
		req.setAttribute("pageNum", pageNum);
		req.getRequestDispatcher("/move.do?cmd=itemList").forward(req, resp);
	}
	
	private void itemAllOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int item_num = (int) req.getAttribute("item_num");
		String order = (String) req.getAttribute("order");
		
		int pageNum = (int) req.getAttribute("pageNum");
		int startRow = (int) req.getAttribute("startRow");
		int endRow = (int) req.getAttribute("endRow");
		
		ItemDao_kdy dao = new ItemDao_kdy();
		ArrayList<ItemListVo_kdy> list = dao.itemAllOrder(item_num, order);
		int cnt = dao.itemCount(item_num);
		int pageCount = (int)Math.ceil((cnt)/20.0);
		int startPage=((pageNum-1)/10*10)+1; //시작페이지
		int endPage=startPage+9;
		if(pageCount<endPage) {
			endPage=pageCount;
		}
		
		req.setAttribute("cnt", cnt);
		req.setAttribute("list", list);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("startPage", startPage);
		req.setAttribute("endPage", endPage);
		req.setAttribute("pageNum", pageNum);
		req.getRequestDispatcher("/move.do?cmd=itemList").forward(req, resp);
	}
	
	private void itemField(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int item_num = (int) req.getAttribute("item_num");
		int fieldnum = (int) req.getAttribute("fieldnum");
		
		int pageNum = (int) req.getAttribute("pageNum");
		int startRow = (int) req.getAttribute("startRow");
		int endRow = (int) req.getAttribute("endRow");
		
		ItemDao_kdy dao = new ItemDao_kdy();
		ArrayList<ItemListVo_kdy> list = dao.itemField(item_num, fieldnum);
		int cnt = dao.itemFcount(item_num, fieldnum);
		int pageCount = (int)Math.ceil((cnt)/20.0);
		int startPage=((pageNum-1)/10*10)+1; //시작페이지
		int endPage=startPage+9;
		if(pageCount<endPage) {
			endPage=pageCount;
		}
		
		req.setAttribute("cnt", cnt);
		req.setAttribute("list", list);
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("startPage", startPage);
		req.setAttribute("endPage", endPage);
		req.setAttribute("pageNum", pageNum);
		req.getRequestDispatcher("/move.do?cmd=itemList").forward(req, resp);
	}
}
