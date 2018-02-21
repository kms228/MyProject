package shs.admin.controller.order;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shs.admin.dao.order.PrepareproductDao;
import shs.admin.vo.order.PrepProdVo;
import shs.admin.vo.order.SearchOptionVo;

@WebServlet("/admin/prepareProduct.do")
public class PrepareOrderController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String cmd = req.getParameter("cmd");
		if(cmd==null) {
			resp.sendRedirect(req.getContextPath()+"/admin/order/prepareproduct.jsp");			
		}else if(cmd.equals("search")) {			
			search(req,resp);
		}
	}

	private void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String optName = req.getParameter("optName");
		String optValue = req.getParameter("optValue");
		String buy_date = req.getParameter("buy_date");
		String item_name = req.getParameter("item_name");
		
		SearchOptionVo vo = new SearchOptionVo(optName, optValue, buy_date, item_name);		
		PrepareproductDao dao = new PrepareproductDao();
		ArrayList<PrepProdVo> list = dao.search(vo);
		req.setAttribute("search", vo);
		req.setAttribute("list", list);
		req.getRequestDispatcher("/admin/order/prepareproduct.jsp").forward(req, resp);
	}
}
