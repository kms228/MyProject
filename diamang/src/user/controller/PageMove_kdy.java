package user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/move.do")
public class PageMove_kdy extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String cmd = req.getParameter("cmd");
		String url = "page/main_kdy.jsp";
		if(cmd==null) { //ó�� ������ ��
			req.getRequestDispatcher("/main.do").forward(req, resp);
		}else if(cmd.equals("main")){//�������� ���ƿ� ��
			url = "page/main_kdy.jsp";
			req.setAttribute("cmd", url);
			req.getRequestDispatcher("/user/layout_kdy.jsp").forward(req, resp);
		}else if(cmd.equals("review_main")) { //���� ��������
			url = "page/review/review_main_kdy.jsp";
			req.setAttribute("cmd", url);
			req.getRequestDispatcher("/user/layout_kdy.jsp").forward(req, resp);
		}else if(cmd.equals("review_write")){ //���� �������� �۾��� ��������
			url = "page/review/review_write_kdy.jsp";
			req.setAttribute("cmd", url);
			req.getRequestDispatcher("/user/layout_kdy.jsp").forward(req, resp);
		}else if(cmd.equals("review_update")){
			url = "page/review/review_update_kdy.jsp";
			req.setAttribute("cmd", url);
			req.getRequestDispatcher("/user/layout_kdy.jsp").forward(req, resp);
		}else if(cmd.equals("result")) { //���� �������� ��� ��������
			url = "page/review/result_kdy.jsp";
			req.setAttribute("cmd", url);
			req.getRequestDispatcher("/user/layout_kdy.jsp").forward(req, resp);
		}else if(cmd.equals("review_detail")) { //���� �������� �� �ۺ��� ��������
			url = "page/review/review_detail_kdy.jsp";
			req.setAttribute("cmd", url);
			req.getRequestDispatcher("/user/layout_kdy.jsp").forward(req, resp);
		}else if(cmd.equals("login")) { //�α��� ��������
			url = "login_hhj.jsp";
			req.setAttribute("cmd", url);
			req.getRequestDispatcher("/user/layout_kdy.jsp").forward(req, resp);
		}else if(cmd.equals("logout")) { //�α׾ƿ� ��������
			
		}else if(cmd.equals("join")) { //ȸ������ ��������
			url = "join_hhj.jsp";
			req.setAttribute("cmd", url);
			req.getRequestDispatcher("/user/layout_kdy.jsp").forward(req, resp);
		}else if(cmd.equals("review_update")) {
			req.getRequestDispatcher("/user/page/review/review_update_kdy.jsp").forward(req, resp);
		}else if(cmd.equals("itemList")) {	//�� ��ǰ ��� ��������
			url = "page/item/item_kdy.jsp";
			req.setAttribute("cmd", url);
			req.getRequestDispatcher("/user/layout_kdy.jsp").forward(req, resp);
		}else if(cmd.equals("itemDetail")) {
			url = "page/item/item_detail_kdy.jsp";
			req.setAttribute("cmd", url);
			req.getRequestDispatcher("/user/layout_kdy.jsp").forward(req, resp);
		}else if(cmd.equals("orderCheck")) {
			url = "page/order/order_kdy.jsp";
			req.setAttribute("cmd", url);
			req.getRequestDispatcher("/user/layout_kdy.jsp").forward(req, resp);
		}else if(cmd.equals("cartCheck")) { //īƮ�� ��ǰ ��� Ȯ�� ������
			url = "page/cart/cartOk_kdy.jsp";
			req.setAttribute("cmd", url);
			req.getRequestDispatcher("/user/layout_kdy.jsp").forward(req, resp);
		}else if(cmd.equals("cartList")) {
			url = "page/cart/cart_kdy.jsp";
			req.setAttribute("cmd", url);
			req.getRequestDispatcher("/user/layout_kdy.jsp").forward(req, resp);
		}else if(cmd.equals("orderResult")) { //�ֹ��Ϸ� ������
			url = "page/order/orderResult_kdy.jsp";
			req.setAttribute("cmd", url);
			req.getRequestDispatcher("/user/layout_kdy.jsp").forward(req, resp);
		}else if(cmd.equals("orderList")) { //�ֹ� �����ȸ
			url = "page/order/orderList_kdy.jsp";
			req.setAttribute("cmd", url);
			req.getRequestDispatcher("/user/layout_kdy.jsp").forward(req, resp);
		}else if(cmd.equals("qna_main")) { //QnA ���� ��������
			url = "page/qna/qna_main_kdy.jsp";
			req.setAttribute("cmd", url);
			req.getRequestDispatcher("/user/layout_kdy.jsp").forward(req, resp);
		}else if(cmd.equals("qna_write")) {	//qna �۾��� ��������
			HttpSession session=req.getSession();
			String id = (String)session.getAttribute("id");
			if(id==null) {
				url = "login_hhj.jsp";
				req.setAttribute("cmd", url);
				req.getRequestDispatcher("/user/layout_kdy.jsp").forward(req, resp);
			}else{
				url = "page/qna/qna_write_kdy.jsp";
				req.setAttribute("cmd", url);
				req.getRequestDispatcher("/user/layout_kdy.jsp").forward(req, resp);
			}
		}else if(cmd.equals("qna_result")) { //qna �������� ��� ��������
			url = "page/qna/qna_result_kdy.jsp";
			req.setAttribute("cmd", url);
			req.getRequestDispatcher("/user/layout_kdy.jsp").forward(req, resp);
		}else if(cmd.equals("qna_detail")) { //qna �� ��������
			url = "page/qna/qna_detail_kdy.jsp";
			req.setAttribute("cmd", url);
			req.getRequestDispatcher("/user/layout_kdy.jsp").forward(req, resp);
		}else if(cmd.equals("qna_update")) { //qna ���� ��������
			HttpSession session=req.getSession();
			String id = (String)session.getAttribute("id");
			if(id==null) {
				url = "login_hhj.jsp";
				req.setAttribute("cmd", url);
				req.getRequestDispatcher("/user/layout_kdy.jsp").forward(req, resp);	
			}else {
				url = "page/qna/qna_update_kdy.jsp";
				req.setAttribute("cmd", url);
				req.getRequestDispatcher("/user/layout_kdy.jsp").forward(req, resp);
			}
		}
	}
}
