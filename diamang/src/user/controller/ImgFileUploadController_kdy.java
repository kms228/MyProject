package user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/imgUpload.do")
public class ImgFileUploadController_kdy extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/user/page/review/review_write_kdy.jsp");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		System.out.println("imgFileUploadController Á¢¼Ó");
		if(req.getParameter("file1")!=null) {
			String uploadPath=req.getServletContext().getRealPath("user/upload");
			MultipartRequest mr=new MultipartRequest(
					req,
					uploadPath,
					1024*1024*50,
					"utf-8",
					new DefaultFileRenamePolicy()
					);
		}
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String snum = req.getParameter("rv_num");
		int star = Integer.parseInt(req.getParameter("star"));
		System.out.println(star);
		
		int num = 0;
		int ref = 0;
		int lev = 0;
		int step = 0;
		
		if(snum!=null && !snum.equals("")) {
			num = Integer.parseInt(snum);
			ref = Integer.parseInt(req.getParameter("ref"));
			lev = Integer.parseInt(req.getParameter("lev"));
			step = Integer.parseInt(req.getParameter("step"));
		}
		
		
		
		
	}
}
	
