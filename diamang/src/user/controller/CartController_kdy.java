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
			if(id==null) { //로그인 안되어있으면 로그인 페이지로
				req.getRequestDispatcher("/move.do?cmd=login").forward(req, resp);
			}else { //로그인 되어있으면 세션에서 구한 id를 req에 넣고 insertCart 메소드로 반환
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
		
		//쿠키 확인(이미 장바구니에 추가된 상품인지 확인하고 수량 바꿔주는 작업)
		Cookie[] cooks = req.getCookies();
		if(cooks.length==1) {
			System.out.println("빈 장바구니 상태");
			String item_name = "item"+pnum2;
			System.out.println("쿠키가 없을 때(빈장바구니에서 시작):"+item_name);
			String value=pnum2+"a"+amount; //얻어온 수량 붙이는 작업
			Cookie cook=new Cookie(item_name,value);
			System.out.println("쿠키추가, value값:"+value);
			cook.setMaxAge(5*60);//하루
			resp.addCookie(cook);
		}else {
			System.out.println("쿠키가 이미 있음(장바구니에 물건 잇음)");
			boolean ex=false;
			for(Cookie ck:cooks) {	
				String name = ck.getName();
				String name2="item"+pnum2;		
				if(name.equals(name2)) {//장바구니에 넣을 상품이 이미 쿠키에 저장되어 있을 때
					System.out.println("장바구니에 넣은 상품이 이미 쿠키에 저장돼있음"+name);
					ex=true;
					String value = ck.getValue();
					String[] split = value.split("a");
					amount += Integer.parseInt(split[1]);
					System.out.println("저장할 수량:"+amount);
					String finalValue = split[0]+"a"+amount;
					System.out.println("finalValue:"+finalValue);
					Cookie cook=new Cookie(name,finalValue);
					cook.setMaxAge(5*60);
					resp.addCookie(cook);
					}
			}
			if(ex==false) {
				System.out.println("선택한 상품이 장바구니에 없음");
				String item_name = "item"+pnum2;
				pnum2=pnum2+"a"+amount; //얻어온 수량 붙이는 작업
				Cookie cook=new Cookie(item_name,pnum2);
				cook.setMaxAge(5*60);//하루
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
		
		//쿠키 다시 확인
		Cookie[] cooks2 = req.getCookies();
		for(Cookie ck2:cooks2) {
			String name = ck2.getName();
			if(name.startsWith("item")) {
				String value=ck2.getValue();
				System.out.println("가져온값:"+value);
				String []pp = value.split("a");
				amount = Integer.parseInt(pp[1]);
				System.out.println("수량:"+amount);
				pnum = Integer.parseInt(pp[0]);
			
			//쿠키에 있는 pnum으로 상품 정보 조회
			ItemDao_kdy dao = new ItemDao_kdy();
			ItemListVo_kdy vo = dao.itemDetail(pnum);
			
			//세션에 저장되어 있는 id로 회원 정보 중에 등급 가져오기.
			MembersDao_kdy dao2=new MembersDao_kdy();
			int mnum = dao2.searchMnum(id);
			MembersVo_kdy vo2=dao2.MembersInfo(mnum);
			GradeVo_kdy vo3 = dao2.gradeInfo(vo2.getGnum());
			
			//등급에 따른 할인율과 할인 후 가격 구하기
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
