package admin.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import admin.dao.IteamImageDao;
import admin.dao.ItemDao;
import admin.vo.ItemImageVo;
import admin.vo.ItemVo;
import diamang.dbcp.DbcpBean;


@WebServlet("/item")
public class ItemController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = (String)request.getParameter("cmd");
		if(cmd.equals("insert")) {
			response.sendRedirect(request.getContextPath()+"/admin/layout_kms.jsp?page=item/insertItem_kms.jsp");
		}else if(cmd.equals("itemMenu")) {
			System.out.println("itemController:itemMenu");
			itemMenu(request, response);
		}else if(cmd.equals("itemInsert")) {
			System.out.println("itemController:itemInsertOk");
			itemInsert(request, response);
		}
	}
	
	private void itemMenu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		pw.close();
	}
	//상품추가
	private void itemInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String uploadPath = request.getServletContext().getRealPath("/upload");
		MultipartRequest mr=new MultipartRequest(
	            request, //request객체
	            uploadPath,   //업로드할 파일 경로
	            1024*1024*20,   //최대 업로드 크기(바이트 단위로 설정)
	            "utf-8",   //인코딩방식
	            new DefaultFileRenamePolicy());
		
		String item_name = mr.getParameter("item_name");
		int price = Integer.parseInt(request.getParameter("price"));
		int stock = Integer.parseInt(request.getParameter("stock"));
		int fieldnum = Integer.parseInt(request.getParameter("fieldnum"));
		
		ItemVo vo = new ItemVo(0, item_name, price, null, stock, fieldnum);
		ItemDao dao = ItemDao.getInstance();
		//방금 insert한 상품의 pnum구하기
		int pnum = dao.itemInsert(vo);
		
		IteamImageDao imgDao = IteamImageDao.getInstance();
		
		String savefilename1 = mr.getFilesystemName("file1");
		ItemImageVo imgvo1 = new ItemImageVo(0, pnum, savefilename1);
		int i = imgDao.itemImageInsert(imgvo1);
		
		String savefilename2 = mr.getFilesystemName("file2");
		ItemImageVo imgvo2 = new ItemImageVo(0, pnum, savefilename2);
		int j = imgDao.itemImageInsert(imgvo2);
		
		if(i>0 && j>0) {
			 response.sendRedirect(request.getContextPath()+"/item?cmd=insert");
		}else {
			System.out.println("파일추가실패");
		}
	}		
}