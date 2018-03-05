package admin.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import admin.dao.ItemDao;
import admin.dao.ItemImg1Dao;
import admin.dao.ItemImg2Dao;
import admin.dao.ItemListDao;
import admin.vo.ItemImg1Vo;
import admin.vo.ItemImg2Vo;
import admin.vo.ItemVo;


@WebServlet("/item")
public class ItemController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = (String)request.getParameter("cmd");
		if(cmd.equals("insert")) {
			HttpSession session = request.getSession();
			String id = (String)session.getAttribute("id");
			if(id==null) {
				request.setAttribute("errMsg", "로그인 후 이용가능합니다.");
				request.getRequestDispatcher("/admin/layout_kms.jsp?page=page/home_kms.jsp").forward(request, response);
			}else {
				response.sendRedirect(request.getContextPath()+"/admin/layout_kms.jsp?page=item/insertItem_kms.jsp");
			}
			
		}else if(cmd.equals("itemMenu")) {
			System.out.println("itemController:itemMenu");
			itemMenu(request, response);
			
		}else if(cmd.equals("itemInsert")) {
			System.out.println("itemController:itemInsert");
			itemInsert(request, response);
		
		}else if(cmd.equals("list")) {
			HttpSession session = request.getSession();
			String id = (String)session.getAttribute("id");
			if(id==null) {
				request.setAttribute("errMsg", "로그인 후 이용가능합니다.");
				request.getRequestDispatcher("/admin/layout_kms.jsp?page=page/home_kms.jsp").forward(request, response);
			}else {
				response.sendRedirect(request.getContextPath()+"/admin/layout_kms.jsp?page=item/itemList.jsp");
			}
		}else if(cmd.equals("del")) {
			System.out.println("지우러옴");
			del(request,response);
		
		}else if(cmd.equals("listOk")) {
			itemList(request,response);
		
		}else if(cmd.equals("detail")) {
			detail(request,response);
		
		}else if(cmd.equals("update")) {
			update(request,response);
		}
	}
	
	private void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("지우러옴2");
		int pnum = Integer.parseInt(request.getParameter("pnum"));
		int fieldnum = Integer.parseInt(request.getParameter("fieldnum"));
		String uploadPath = request.getServletContext().getRealPath("admin/upload");
		//삭제할이미지정보 얻어오기
		//대표이미지 삭제
		ItemImg1Dao img1dao = ItemImg1Dao.getInstance();
		ItemImg1Vo delImg1 = img1dao.getinfo(pnum);
		String delImgName1 = delImg1.getSavefilename();
		File myFile1 = new File(uploadPath+"/"+delImgName1);
		boolean del1 = myFile1.delete();
		//대표이미지 db상 삭제
		int i = img1dao.delete(pnum);
		//상세이미지 삭제
		ItemImg2Dao img2dao = ItemImg2Dao.getInstance();
		ItemImg2Vo delImg2 = img2dao.getinfo(pnum);
		String delImgName2 = delImg2.getSavefilename();
		File myFile2 = new File(uploadPath+"/"+delImgName2);
		boolean del2 = myFile2.delete();
		//상세이미지 db상 삭제
		int j =	img2dao.delete(pnum);
		if(del1 && del2) {
			System.out.println("delete controller : 이미지 파일 삭제성공");
		}else {
			System.out.println("delete controller : 이미지 파일 삭제실패");
		}
		if(i>0 && j>0) {
			System.out.println("delete controller : db상 이미지 파일 삭제성공");
		}
		ItemDao dao = ItemDao.getInstance();
		int n = dao.delete(pnum);
		if(n>0) {
			System.out.println("delete controller : db정보 삭제 성공");
		}
		request.setAttribute("fieldnum", fieldnum);
		//request.getRequestDispatcher("/admin/layout_kms.jsp?page=item/itemList.jsp?page=selectItemList&?fieldnum="+fieldnum).forward(request, response);
		itemList(request,response);
	}
	
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("itemController : itemInsert");
		request.setCharacterEncoding("UTF-8");
		String uploadPath = request.getServletContext().getRealPath("admin/upload");
		//이미지업로드
		MultipartRequest mr=new MultipartRequest(
	            request, //request객체
	            uploadPath,   //업로드할 파일 경로
	            1024*1024*20,   //최대 업로드 크기(바이트 단위로 설정)
	            "utf-8",   //인코딩방식
	            new DefaultFileRenamePolicy()
	            );
		//업로드할 db정보얻기
		int pnum = Integer.parseInt(mr.getParameter("pnum"));
		String item_name = mr.getParameter("item_name");
		int price = Integer.parseInt(mr.getParameter("price"));
		int stock = Integer.parseInt(mr.getParameter("stock"));
		int fieldnum = Integer.parseInt(mr.getParameter("fieldnum"));
		ItemVo vo = new ItemVo(pnum, item_name, price, null, stock, fieldnum);
		//db정보 업데이트저장
		ItemDao dao = ItemDao.getInstance();
		int n = dao.update(vo);
		int i=0;
		int j=0;
		boolean del1=false;
		boolean del2=false;
		//대표이미지 db 업데이트저장
		String savefilename1 = mr.getFilesystemName("file1");
		if(savefilename1!=null) {
			ItemImg1Vo imgvo1 = new ItemImg1Vo(0, pnum, savefilename1);
			ItemImg1Dao img1dao = ItemImg1Dao.getInstance();
			//신규이미지 업로드
			
			//이미지1db업뎃
			i = img1dao.update(imgvo1);
			String delFile1 = mr.getParameter("delFile1");
			//기존이미지삭제
			File myFile1 = new File(uploadPath+"/"+delFile1);
			del1 = myFile1.delete();
		}
		//상세이미지 db 업데이트저장
		String savefilename2 = mr.getFilesystemName("file2");
		if(savefilename2!=null) {
			ItemImg2Vo imgvo2 = new ItemImg2Vo(0, pnum, savefilename2);
			ItemImg2Dao img2dao = ItemImg2Dao.getInstance();
			//신규이미지 업로드
			
			//이미지2db업뎃
			j = img2dao.update(imgvo2);
			//기존이미지삭제
			String delFile2 = mr.getParameter("delFile2");
			File myFile2 = new File(uploadPath+"/"+delFile2);
			del2 = myFile2.delete();
		}
		//삭제할이미지정보 얻어오기
		
		if(n>0) {
			System.out.println("db정보수정완료");
		}	
		if(i>0) {
			System.out.println("새로운 대표이미지 db저장");
		}
		if(j>0) {
			System.out.println("새로운 상세이미지 db저장");
		}
		if(del1) {
			System.out.println("기존 대표이미지 삭제");
		}
		if(del2) {
			System.out.println("기존 상세이미지 삭제");
		}
		//request.getRequestDispatcher("/admin/layout_kms.jsp?page=item/itemList.jsp").forward(request, response);
		request.getRequestDispatcher("/admin/item/result.jsp").forward(request, response); 
	}
	private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("pnum"));
		
		ItemDao dao = ItemDao.getInstance();
		ItemImg1Dao img1dao = ItemImg1Dao.getInstance();
		ItemImg2Dao img2dao = ItemImg2Dao.getInstance();
		
		ItemVo vo = dao.getinfo(num);
		ItemImg1Vo img1vo = img1dao.getinfo(num);
		ItemImg2Vo img2vo = img2dao.getinfo(num);
		
		request.setAttribute("vo", vo);
		request.setAttribute("img1vo", img1vo);
		request.setAttribute("img2vo", img2vo);
		request.getRequestDispatcher("/admin/item/detail.jsp").forward(request, response);
		
	}
	private void itemList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//페이지번호 얻어오기
		String spageNum = request.getParameter("pageNum");
		int pageNum=1;
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		int startRow=(pageNum-1)*10+1;//시작행번호
		int endRow=startRow+9;//끝행번호
		ItemListDao dao = new ItemListDao();
		
		System.out.println("필드넘 : "+request.getParameter("fieldnum"));
		int fieldnum = Integer.parseInt(request.getParameter("fieldnum"));
		
		ArrayList<ItemVo> list = dao.list(startRow, endRow ,fieldnum);
		
		//전체 페이지 갯수
		int pageCount=(int)Math.ceil(dao.getCount(fieldnum)/10.0);//ceil : 올림
		int startPage=((pageNum-1)/10*10)+1;//시작페이지
		int endPage=startPage+9;//끝페이지
		if(pageCount<endPage) {
			endPage=pageCount;
		}
		
		request.setAttribute("list", list);
		request.setAttribute("fieldnum", fieldnum);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("page", "selectItemList.jsp?fieldnum="+fieldnum);
		request.getRequestDispatcher("/admin/layout_kms.jsp?page=item/itemList.jsp?page=selectItemList").forward(request, response);
	}

	private void itemMenu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		pw.close();
	}
	//상품추가
	private void itemInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("itemController : itemInsert");
		request.setCharacterEncoding("UTF-8");
		String uploadPath = request.getServletContext().getRealPath("admin/upload");
		MultipartRequest mr=new MultipartRequest(
	            request, //request객체
	            uploadPath,   //업로드할 파일 경로
	            1024*1024*20,   //최대 업로드 크기(바이트 단위로 설정)
	            "utf-8",   //인코딩방식
	            new DefaultFileRenamePolicy());
		
		String item_name = mr.getParameter("item_name");
		int price = Integer.parseInt(mr.getParameter("price"));
		int stock = Integer.parseInt(mr.getParameter("stock"));
		int fieldnum = Integer.parseInt(mr.getParameter("fieldnum"));
		
		ItemVo vo = new ItemVo(0, item_name, price, null, stock, fieldnum);
		ItemDao dao = ItemDao.getInstance();

		//방금 insert한 상품의 pnum구하기
		int pnum = dao.itemInsert(vo);
		ItemImg1Dao imgDao = ItemImg1Dao.getInstance();
		String savefilename1 = mr.getFilesystemName("file1");
		ItemImg1Vo imgvo1 = new ItemImg1Vo(0, pnum, savefilename1);
		int i = imgDao.itemImg1Insert(imgvo1);
		
		ItemImg2Dao imgDao2 = ItemImg2Dao.getInstance();
		String savefilename2 = mr.getFilesystemName("file2");
		ItemImg2Vo imgvo2 = new ItemImg2Vo(0, pnum, savefilename2);
		int j = imgDao2.itemImg2Insert(imgvo2);
		
		if(i>0 && j>0) {
			response.sendRedirect(request.getContextPath()+"/item?cmd=insert");
			System.out.println("파일추가성공");
			
		}else {
			System.out.println("파일추가실패");
		}
	}		
}