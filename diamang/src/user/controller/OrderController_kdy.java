package user.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.dao.MembersDao_kdy;
import user.dao.OrderDao_kdy;
import user.vo.BuyBoardVo_kdy;
import user.vo.GradeVo_kdy;
import user.vo.MembersVo_kdy;
import user.vo.OrderVo_kdy;

@WebServlet("/order.do")
public class OrderController_kdy extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String cmd = req.getParameter("cmd");
		
		if(cmd.equals("orderCheck")) {
			HttpSession session=req.getSession();
			String id = (String)session.getAttribute("id");
			if(id==null) {
				req.getRequestDispatcher("/move.do?cmd=login").forward(req, resp);
			}else {
				req.setAttribute("id", id);
				orderCheck(req,resp);
			}
		}else if(cmd.equals("orderOk")) {
			orderOk(req,resp);
		}
	}
	
	private void orderOk(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int mnum = Integer.parseInt(req.getParameter("mnum"));
		String addr = req.getParameter("address");
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		int accprice = Integer.parseInt(req.getParameter("payPrice"));
		int drate = Integer.parseInt(req.getParameter("drate"));
		BuyBoardVo_kdy vo = new BuyBoardVo_kdy(0,mnum,null,null,name,addr,phone,accprice,drate);
		OrderDao_kdy dao=new OrderDao_kdy();
		int n = dao.OrderOk(vo);
		if(n>0) {
			
			req.getRequestDispatcher("/move.do?cmd=orderResult").forward(req, resp);
		}
		
		
	}
	
	
	private void orderCheck(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = (String)req.getAttribute("id");
		
		int pnum = Integer.parseInt(req.getParameter("pnum"));
		String item_name = req.getParameter("item_name");
		String savename = req.getParameter("savename");
		int price = Integer.parseInt(req.getParameter("price"));
		int amount = Integer.parseInt(req.getParameter("amount"));
		
		MembersDao_kdy dao=new MembersDao_kdy();
		int mnum = dao.searchMnum(id);
		MembersVo_kdy vo=dao.MembersInfo(mnum);
		GradeVo_kdy vo2 = dao.gradeInfo(vo.getGnum());
		double drate = price*(vo2.getDrate()/100.0);
		System.out.println(vo2.getDrate());
		int total = (int) (price-drate)+3000;
		
		ArrayList<OrderVo_kdy> list=new ArrayList<>();
		OrderVo_kdy vo3 = new OrderVo_kdy(pnum,item_name, savename, price, vo2.getGrade(), amount, total);
		list.add(vo3);
		req.setAttribute("mem", vo);
		req.setAttribute("list", list);
		req.setAttribute("grade", vo2);
		req.getRequestDispatcher("/move.do?cmd=orderCheck").forward(req, resp);
	}

}
