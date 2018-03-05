package user.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.dao.ItemDao_kdy;
import user.dao.MembersDao_kdy;
import user.vo.GradeVo_kdy;
import user.vo.ItemListVo_kdy;
import user.vo.MembersVo_kdy;
import user.vo.OrderVo_kdy;

@WebServlet("/cart.do")
public class CartController_kdy extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String cmd = req.getParameter("cmd");
		
		if(cmd.equals("cartCheck")) {
			HttpSession session=req.getSession();
			String id = (String)session.getAttribute("id");
			if(id==null) { //�α��� �ȵǾ������� �α��� ��������
				req.getRequestDispatcher("/move.do?cmd=login").forward(req, resp);
			}else { //�α��� �Ǿ������� ���ǿ��� ���� id�� req�� �ְ� insertCart �޼ҵ�� ��ȯ
				req.setAttribute("id", id);			
				
//				Cookie[] cooks = req.getCookies();
//				for(Cookie ck : cooks) {
//					String name = ck.getName();
//					Cookie cook=new Cookie(name,"");
//					cook.setMaxAge(0);
//					resp.addCookie(cook);
//				}
				insertCart(req,resp);
			}
		}else if(cmd.equals("cartList")) {
			listCart(req, resp);
		}else if(cmd.equals("cartCancel")) {
			cancelCart(req, resp);
		}
	}
	
	private void cancelCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String[] pp = req.getParameterValues("check");
		for(String pp2:pp) {
			int pnum = Integer.parseInt(pp2);
			String item="item"+pnum;
			Cookie ck = new Cookie(item,"");
			ck.setMaxAge(0);
			resp.addCookie(ck);
			listCart(req, resp);
		}
	}
	
	private void insertCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pnum2 = req.getParameter("pnum");
		
		int amount = Integer.parseInt(req.getParameter("amount"));
		int pnum = Integer.parseInt(pnum2);
		
		//��Ű Ȯ��(�̹� ��ٱ��Ͽ� �߰��� ��ǰ���� Ȯ���ϰ� ���� �ٲ��ִ� �۾�)
		Cookie[] cooks = req.getCookies();
		if(cooks.length==1) {
			System.out.println("�� ��ٱ��� ����");
			String item_name = "item"+pnum2;
			System.out.println("��Ű�� ���� ��(����ٱ��Ͽ��� ����):"+item_name);
			String value=pnum2+"a"+amount; //���� ���� ���̴� �۾�
			Cookie cook=new Cookie(item_name,value);
			System.out.println("��Ű�߰�, value��:"+value);
			cook.setMaxAge(5*60);//�Ϸ�
			resp.addCookie(cook);
		}else {
			System.out.println("��Ű�� �̹� ����(��ٱ��Ͽ� ���� ����)");
			boolean ex=false;
			for(Cookie ck:cooks) {	
				String name = ck.getName();
				String name2="item"+pnum2;		
				if(name.equals(name2)) {//��ٱ��Ͽ� ���� ��ǰ�� �̹� ��Ű�� ����Ǿ� ���� ��
					System.out.println("��ٱ��Ͽ� ���� ��ǰ�� �̹� ��Ű�� ���������"+name);
					ex=true;
					String value = ck.getValue();
					String[] split = value.split("a");
					amount += Integer.parseInt(split[1]);
					System.out.println("������ ����:"+amount);
					String finalValue = split[0]+"a"+amount;
					System.out.println("finalValue:"+finalValue);
					Cookie cook=new Cookie(name,finalValue);
					cook.setMaxAge(5*60);
					resp.addCookie(cook);
					}
			}
			if(ex==false) {
				System.out.println("������ ��ǰ�� ��ٱ��Ͽ� ����");
				String item_name = "item"+pnum2;
				pnum2=pnum2+"a"+amount; //���� ���� ���̴� �۾�
				Cookie cook=new Cookie(item_name,pnum2);
				cook.setMaxAge(5*60);//�Ϸ�
				resp.addCookie(cook);
			}
		}
		req.getRequestDispatcher("/move.do?cmd=cartCheck").forward(req, resp);
	}

	
	private void listCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();
		String id = (String)session.getAttribute("id");
		
		ArrayList<OrderVo_kdy> list=new ArrayList<>();
		int amount = 0;
		int pnum = 0;
		int total=0;
		
		//��Ű �ٽ� Ȯ��
		Cookie[] cooks2 = req.getCookies();
		for(Cookie ck2:cooks2) {
			String name = ck2.getName();
			if(name.startsWith("item")) {
				String value=ck2.getValue();
				System.out.println("�����°�:"+value);
				String []pp = value.split("a");
				amount = Integer.parseInt(pp[1]);
				System.out.println("����:"+amount);
				pnum = Integer.parseInt(pp[0]);
			
			//��Ű�� �ִ� pnum���� ��ǰ ���� ��ȸ
			ItemDao_kdy dao = new ItemDao_kdy();
			ItemListVo_kdy vo = dao.itemDetail(pnum);
			
			//���ǿ� ����Ǿ� �ִ� id�� ȸ�� ���� �߿� ��� ��������.
			MembersDao_kdy dao2=new MembersDao_kdy();
			int mnum = dao2.searchMnum(id);
			MembersVo_kdy vo2=dao2.MembersInfo(mnum);
			GradeVo_kdy vo3 = dao2.gradeInfo(vo2.getGnum());
			
			//��޿� ���� �������� ���� �� ���� ���ϱ�
			double drate = vo.getPrice()*(vo3.getDrate()/100.0);
			int price = (int) (vo.getPrice()-drate)*amount;
			
			total += price;
			OrderVo_kdy vo4 = new OrderVo_kdy(pnum,vo.getItem_name(), vo.getSavename(), vo.getPrice(), vo3.getGrade(), amount, price);
			list.add(vo4);
			
			req.setAttribute("grade", vo3);
			}
		}
		
		req.setAttribute("total", total);
		req.setAttribute("list", list);
		req.getRequestDispatcher("/move.do?cmd=cartList").forward(req, resp);
	}
}
