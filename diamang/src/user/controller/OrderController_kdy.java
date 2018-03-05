package user.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import admin.dao.ItemDao;
import user.dao.ItemDao_kdy;
import user.dao.MembersDao_kdy;
import user.dao.OrderDao_kdy;
import user.vo.BuyBoardVo_kdy;
import user.vo.GradeVo_kdy;
import user.vo.ItemListVo_kdy;
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
		String[] ppnum = req.getParameterValues("pnum");
		String[] aamount = req.getParameterValues("amount");
		String[] pprice = req.getParameterValues("price");
		
		int mnum = Integer.parseInt(req.getParameter("mnum"));
		String addr = req.getParameter("address");
		String name = req.getParameter("name");
		String phone = req.getParameter("phone");
		int accprice = Integer.parseInt(req.getParameter("payPrice"));
		int drate = Integer.parseInt(req.getParameter("drate"));
		BuyBoardVo_kdy vo = new BuyBoardVo_kdy(0,mnum,null,null,name,addr,phone,accprice,drate);
		OrderDao_kdy dao=new OrderDao_kdy();
		int n = dao.orderOk(vo);//buyboard에 먼저 insert
		
		String result="fail";
		if(n>0) {
			for(int i=0;i<ppnum.length;i++) {
				//orderlist에 insert하기 위한 과정
				int pnum=Integer.parseInt(ppnum[i]);
				int amount =Integer.parseInt(aamount[i]);
				int price = Integer.parseInt(pprice[i]);
				
				int buy_num = dao.getBuyNum();
				if(buy_num>0) {
					int n2= dao.orderList(pnum, buy_num, amount,price);
				}
				result="success";
			}
		}
		
		req.setAttribute("result", result);
		req.getRequestDispatcher("/move.do?cmd=orderResult").forward(req, resp);
		
	}
	
	
	private void orderCheck(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = (String)req.getAttribute("id");
		
		String[] ppnum = req.getParameterValues("pnum");
		String[] iitem_name = req.getParameterValues("item_name");
		String[] ssavename = req.getParameterValues("savename");
		String[] aamount = req.getParameterValues("amount");
		String[] pprice = req.getParameterValues("price");
		ArrayList<OrderVo_kdy> list=new ArrayList<>();
		
		for(int i=0;i<ppnum.length;i++) {
			int pnum = Integer.parseInt(ppnum[i]);
			String item_name = iitem_name[i];
			String savename = ssavename[i];
			int price = Integer.parseInt(pprice[i]);
			int amount = Integer.parseInt(aamount[i]);
			
			MembersDao_kdy dao=new MembersDao_kdy();
			int mnum = dao.searchMnum(id);
			MembersVo_kdy vo=dao.MembersInfo(mnum);
			GradeVo_kdy vo2 = dao.gradeInfo(vo.getGnum());
			double drate = price*(vo2.getDrate()/100.0);
			System.out.println(vo2.getDrate());
			int total = (int) (price-drate)+3000;
			
			
			OrderVo_kdy vo3 = new OrderVo_kdy(pnum,item_name, savename, price, vo2.getGrade(), amount, total);
			list.add(vo3);
			req.setAttribute("mem", vo);
			req.setAttribute("grade", vo2);
		}
		req.setAttribute("list", list);
		
		req.getRequestDispatcher("/move.do?cmd=orderCheck").forward(req, resp);
	}

}
