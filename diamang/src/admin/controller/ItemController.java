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
			insert(request,response);
		}else if(cmd.equals("itemMenu")) {
			itemMenu(request, response);
		}
	}
	protected void itemMenu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ItemDao dao = ItemDao.getInstance();
		int num = Integer.parseInt(request.getParameter("num"));
		int n = dao.delete(num);
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		pw.println("<result>");
		if(n>0) {
			pw.println("<code>success</code>");
		}else {
			pw.println("<code>fail</code>");
		}		
		pw.println("</result>");
		pw.close();
	}
	
	private int insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		MultipartRequest mr = null;
		int sizeLimit = 10 * 1024 * 1024;
		String uploadPath = request.getRealPath("/upload");
				
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			mr=new MultipartRequest(
					request,  //request��ü
					uploadPath, //���ε��� ���� ���
					sizeLimit, //�ִ� ���ε� ũ��(����Ʈ ������ ����)
					"utf-8", //���ڵ����
					new DefaultFileRenamePolicy() //������ �����̸��� ������ ���ϸ�ڿ� 1,2,..���� ���̱�
				);
			
			String item_name = mr.getParameter("item_name");
			int price = Integer.parseInt(mr.getParameter("price"));
			int stock = Integer.parseInt(mr.getParameter("stock"));
			int fieldnum = Integer.parseInt(mr.getParameter("fieldnum"));
			//������ ���ϸ� ������
			String orgname=mr.getOriginalFileName("file1");
			String savename=mr.getFilesystemName("file1");
			//����ũ�� ���ϱ� (java.io.File)
			File f=new File(uploadPath + File.separator + savename);
			//long filesize=f.length();
			//���۵� ���� VO��ü�� ���
			ItemVo vo=new ItemVo(0, item_name, price, null, stock, savename, orgname, fieldnum);
			//DB�� �������� �����ϱ�
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