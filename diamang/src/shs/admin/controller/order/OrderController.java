package shs.admin.controller.order;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shs.admin.dao.order.OrderCancelDao;
import shs.admin.dao.order.PrepareproductDao;
import shs.admin.dao.order.ShippedCompleteDao;
import shs.admin.dao.order.ShippedEndDao;
import shs.admin.etc.PagingBot;
import shs.admin.vo.order.PrepProdVo;
import shs.admin.vo.order.OrderSearchVo;
import shs.admin.vo.paging.PagingVo;

@WebServlet("/admin/order.do")
public class OrderController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String cmd = req.getParameter("cmd");
		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("id");
		if(id==null) {
			req.setAttribute("errMsg", "로그인 후 이용가능합니다.");
			req.getRequestDispatcher("/admin/layout_kms.jsp?page=page/home_kms.jsp").forward(req, resp);
		
		}else {
			if(cmd.equals("prepareproduct")) {			
				resp.sendRedirect(req.getContextPath()+"/admin/layout_kms.jsp?page=order/prepareproduct.jsp");			
			} else if(cmd.equals("shippedend")) {
				resp.sendRedirect(req.getContextPath()+"/admin/layout_kms.jsp?page=order/shippedend.jsp");
			
			} else if(cmd.equals("shippedcomplete")) {
				resp.sendRedirect(req.getContextPath()+"/admin/layout_kms.jsp?page=order/shippedcomplete.jsp");
			
			} else if(cmd.equals("ordercancel")) {
				resp.sendRedirect(req.getContextPath()+"/admin/layout_kms.jsp?page=order/ordercancel.jsp");
			
			} else if(cmd.equals("prepsearch")) {
				prepSearch(req,resp);
			
			} else if(cmd.equals("uptShippedend")) {
				uptShippedend(req,resp);
			
			} else if(cmd.equals("uptShippedCAN")) {
				uptShippedCAN(req,resp);
			
			} else if(cmd.equals("shippedendSearch")) {
				shippedendSearch(req,resp);
			
			} else if(cmd.equals("shippedCompleteSearch")) {
				shippedCompleteSearch(req,resp);
			
			} else if(cmd.equals("orderCANSearch")) {
				orderCANSearch(req,resp);
			}
		}
	}		

	private void uptShippedCAN(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String buy_num = req.getParameter("buy_num");
		PrepareproductDao dao = new PrepareproductDao();
		int result = dao.uptShippedCAN(buy_num);
		if(result>0) {
			req.setAttribute("msg", "배송취소 처리가 성공했습니다.");
		} else {
			req.setAttribute("msg", "처리 실패. 다시 시도해보세요.");
		}
		prepSearch(req,resp);
	}

	private void uptShippedend(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String buy_num = req.getParameter("buy_num");
//		System.out.println(buy_num);
		PrepareproductDao dao = new PrepareproductDao();
		int result = dao.uptShippedend(buy_num);
		if(result>0) {
			req.setAttribute("msg", "배송중 처리가 성공했습니다.");
		} else {
			req.setAttribute("msg", "처리 실패. 다시 시도해보세요.");
		}
		prepSearch(req,resp);
	}

	private void prepSearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String optName = req.getParameter("optName");
		String optValue = req.getParameter("optValue");
		String buy_date = req.getParameter("buy_date");
		String item_name = req.getParameter("item_name");	
		String pageNum = req.getParameter("pageNum");
		OrderSearchVo vo = new OrderSearchVo(optName, optValue, buy_date, item_name);		
		PrepareproductDao dao = new PrepareproductDao();
		PagingBot bot = new PagingBot(20,10);
		PagingVo pageVo =  bot.calPaging(pageNum, dao.getCount(vo));
		ArrayList<PrepProdVo> list = dao.search(vo, pageVo);		
		req.setAttribute("search", vo);		
		if(list.size()>0) {
			req.setAttribute("list", list);
			req.setAttribute("pageVo", pageVo);
		}
		req.getRequestDispatcher("/admin/order/prepareproduct.jsp").forward(req, resp);
	}
	private void shippedendSearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String optName = req.getParameter("optName");
		String optValue = req.getParameter("optValue");
		String buy_date = req.getParameter("buy_date");
		String item_name = req.getParameter("item_name");	
		String pageNum = req.getParameter("pageNum");
		OrderSearchVo vo = new OrderSearchVo(optName, optValue, buy_date, item_name);		
		ShippedEndDao dao = new ShippedEndDao();
		PagingBot bot = new PagingBot(20,10);
		PagingVo pageVo =  bot.calPaging(pageNum, dao.getCount(vo));
		ArrayList<PrepProdVo> list = dao.search(vo, pageVo);				
		req.setAttribute("search", vo);
		if(list.size()>0) {
			req.setAttribute("list", list);
			req.setAttribute("pageVo", pageVo);
		}		
		req.getRequestDispatcher("/admin/order/shippedend.jsp").forward(req, resp);
	}
	private void shippedCompleteSearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String optName = req.getParameter("optName");
		String optValue = req.getParameter("optValue");
		String buy_date = req.getParameter("buy_date");
		String item_name = req.getParameter("item_name");	
		String pageNum = req.getParameter("pageNum");
		OrderSearchVo vo = new OrderSearchVo(optName, optValue, buy_date, item_name);		
		ShippedCompleteDao dao = new ShippedCompleteDao();
		PagingBot bot = new PagingBot(20,10);
		PagingVo pageVo =  bot.calPaging(pageNum, dao.getCount(vo));
		ArrayList<PrepProdVo> list = dao.search(vo, pageVo);				
		req.setAttribute("search", vo);
		if(list.size()>0) {
			req.setAttribute("list", list);
			req.setAttribute("pageVo", pageVo);
		}		
		req.getRequestDispatcher("/admin/order/shippedcomplete.jsp").forward(req, resp);
	}
	
	private void orderCANSearch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		OrderSearchVo vo = new OrderSearchVo(req.getParameter("optName"), req.getParameter("optValue"), req.getParameter("buy_date"), req.getParameter("item_name"));		
		OrderCancelDao dao = new OrderCancelDao();
		PagingBot bot = new PagingBot(20,10);
		PagingVo pageVo =  bot.calPaging(req.getParameter("pageNum"), dao.getCount(vo));
		ArrayList<PrepProdVo> list = dao.search(vo, pageVo);
		req.setAttribute("search", vo);
		if(list.size()>0) {
			req.setAttribute("list", list);
			req.setAttribute("pageVo", pageVo);
		}
		req.getRequestDispatcher("/admin/order/ordercancel.jsp").forward(req, resp);
	}
}
