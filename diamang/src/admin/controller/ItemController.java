package admin.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import admin.dao.ItemDao;
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
		}
	}
	
	private void itemMenu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		pw.close();
	}
	
	private int insertOk(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		MultipartRequest mr = null;
		int sizeLimit = 10 * 1024 * 1024;
		String uploadPath = request.getRealPath("/upload");
				
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			mr=new MultipartRequest(
					request,  //request객체
					uploadPath, //업로드할 파일 경로
					sizeLimit, //최대 업로드 크기(바이트 단위로 설정)
					"utf-8", //인코딩방식
					new DefaultFileRenamePolicy() //동일한 파일이름이 들어오면 파일명뒤에 1,2,..숫자 붙이기
				);
			
			String item_name = mr.getParameter("item_name");
			int price = Integer.parseInt(mr.getParameter("price"));
			int stock = Integer.parseInt(mr.getParameter("stock"));
			int fieldnum = Integer.parseInt(mr.getParameter("fieldnum"));
			//전송한 파일명 얻어오기
			String orgname=mr.getOriginalFileName("file1");
			String savename=mr.getFilesystemName("file1");
			//파일크기 구하기 (java.io.File)
			File f=new File(uploadPath + File.separator + savename);
			//long filesize=f.length();
			//전송된 정보 VO객체에 담기
			ItemVo vo=new ItemVo(0, item_name, price, null, stock, savename, orgname, fieldnum);
			//DB에 파일정보 저장하기
			ItemDao dao = ItemDao.getInstance();
			return dao.insert(vo);
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return -1;
			
		}finally {
			DbcpBean.closeConn(con, pstmt, null);
		}
	}	
}