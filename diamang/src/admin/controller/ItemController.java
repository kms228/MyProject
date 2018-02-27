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
			response.sendRedirect(request.getContextPath()+"/admin/layout_kms.jsp?page=item/insertItem_kms.jsp");
			
		}else if(cmd.equals("itemMenu")) {
			System.out.println("itemController:itemMenu");
			itemMenu(request, response);
			
		}else if(cmd.equals("itemInsert")) {
			System.out.println("itemController:itemInsert");
			itemInsert(request, response);
		
		}else if(cmd.equals("list")) {
			response.sendRedirect(request.getContextPath()+"/admin/layout_kms.jsp?page=item/itemList.jsp");
		
		}else if(cmd.equals("del")) {
			System.out.println("���췯��");
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
		System.out.println("���췯��2");
		int pnum = Integer.parseInt(request.getParameter("pnum"));
		
		String uploadPath = request.getServletContext().getRealPath("admin/upload");
		//�������̹������� ������
		//��ǥ�̹��� ����
		ItemImg1Dao img1dao = ItemImg1Dao.getInstance();
		ItemImg1Vo delImg1 = img1dao.getinfo(pnum);
		String delImgName1 = delImg1.getSavefilename();
		File myFile1 = new File(uploadPath+"/"+delImgName1);
		boolean del1 = myFile1.delete();
		//��ǥ�̹��� db�� ����
		int i = img1dao.delete(pnum);
		//���̹��� ����
		ItemImg2Dao img2dao = ItemImg2Dao.getInstance();
		ItemImg2Vo delImg2 = img2dao.getinfo(pnum);
		String delImgName2 = delImg2.getSavefilename();
		File myFile2 = new File(uploadPath+"/"+delImgName2);
		boolean del2 = myFile2.delete();
		//���̹��� db�� ����
		int j =	img2dao.delete(pnum);
		if(del1 && del2) {
			System.out.println("delete controller : �̹��� ���� ��������");
		}else {
			System.out.println("delete controller : �̹��� ���� ��������");
		}
		if(i>0 && j>0) {
			System.out.println("delete controller : db�� �̹��� ���� ��������");
		}
		ItemDao dao = ItemDao.getInstance();
		int n = dao.delete(pnum);
		if(n>0) {
			System.out.println("delete controller : db���� ���� ����");
		}
	}
	
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("itemController : itemInsert");
		request.setCharacterEncoding("UTF-8");
		String uploadPath = request.getServletContext().getRealPath("admin/upload");
		//�̹������ε�
		MultipartRequest mr=new MultipartRequest(
	            request, //request��ü
	            uploadPath,   //���ε��� ���� ���
	            1024*1024*20,   //�ִ� ���ε� ũ��(����Ʈ ������ ����)
	            "utf-8",   //���ڵ����
	            new DefaultFileRenamePolicy()
	            );
		//���ε��� db�������
		int pnum = Integer.parseInt(mr.getParameter("pnum"));
		String item_name = mr.getParameter("item_name");
		int price = Integer.parseInt(mr.getParameter("price"));
		int stock = Integer.parseInt(mr.getParameter("stock"));
		int fieldnum = Integer.parseInt(mr.getParameter("fieldnum"));
		ItemVo vo = new ItemVo(pnum, item_name, price, null, stock, fieldnum);
		//db���� ������Ʈ����
		ItemDao dao = ItemDao.getInstance();
		int n = dao.update(vo);
		//��ǥ�̹��� db ������Ʈ����
		String savefilename1 = mr.getFilesystemName("file1");
		ItemImg1Vo imgvo1 = new ItemImg1Vo(0, pnum, savefilename1);
		ItemImg1Dao img1dao = ItemImg1Dao.getInstance();
		//�ű��̹��� ���ε�
		
		//�̹���1db����
		int i = img1dao.update(imgvo1);
		
		//���̹��� db ������Ʈ����
		String savefilename2 = mr.getFilesystemName("file2");
		ItemImg2Vo imgvo2 = new ItemImg2Vo(0, pnum, savefilename2);
		ItemImg2Dao img2dao = ItemImg2Dao.getInstance();
		//�ű��̹��� ���ε�
		
		//�̹���2db����
		int j = img2dao.update(imgvo2);
		
		//�������̹������� ������
		String delFile1 = mr.getParameter("delFile1");
		String delFile2 = mr.getParameter("delFile2");
		System.out.println(delFile1);
		System.out.println(delFile2);
		//��ǥ�̹��� ����
		File myFile1 = new File(uploadPath+"/"+delFile1);
		boolean del1 = myFile1.delete();
		//���̹��� ����
		File myFile2 = new File(uploadPath+"/"+delFile2);
		boolean del2 = myFile2.delete();
		
		if(n>0) {
			System.out.println("db���������Ϸ�");
		}	
		if(i>0) {
			System.out.println("���ο� ��ǥ�̹��� db����");
		}
		if(j>0) {
			System.out.println("���ο� ���̹��� db����");
		}
		if(del1) {
			System.out.println("���� ��ǥ�̹��� ����");
		}
		if(del2) {
			System.out.println("���� ���̹��� ����");
		}
			
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
		//��������ȣ ������
		String spageNum = request.getParameter("pageNum");
		int pageNum=1;
		if(spageNum!=null) {
			pageNum=Integer.parseInt(spageNum);
		}
		int startRow=(pageNum-1)*10+1;//�������ȣ
		int endRow=startRow+9;//�����ȣ
		ItemListDao dao = new ItemListDao();
		
		System.out.println("�ʵ�� : "+request.getParameter("fieldnum"));
		int fieldnum = Integer.parseInt(request.getParameter("fieldnum"));
		
		ArrayList<ItemVo> list = dao.list(startRow, endRow ,fieldnum);
		
		//��ü ������ ����
		int pageCount=(int)Math.ceil(dao.getCount(fieldnum)/10.0);//ceil : �ø�
		int startPage=((pageNum-1)/10*10)+1;//����������
		int endPage=startPage+9;//��������
		if(pageCount<endPage) {
			endPage=pageCount;
		}
		
		request.setAttribute("list", list);
		request.setAttribute("fieldnum", fieldnum);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("pageNum", pageNum);
		request.getRequestDispatcher("/admin/layout_kms.jsp?page=item/itemList.jsp").forward(request, response);
		
	}

	private void itemMenu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
		pw.close();
	}
	//��ǰ�߰�
	private void itemInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("itemController : itemInsert");
		request.setCharacterEncoding("UTF-8");
		String uploadPath = request.getServletContext().getRealPath("admin/upload");
		MultipartRequest mr=new MultipartRequest(
	            request, //request��ü
	            uploadPath,   //���ε��� ���� ���
	            1024*1024*20,   //�ִ� ���ε� ũ��(����Ʈ ������ ����)
	            "utf-8",   //���ڵ����
	            new DefaultFileRenamePolicy());
		
		String item_name = mr.getParameter("item_name");
		int price = Integer.parseInt(mr.getParameter("price"));
		int stock = Integer.parseInt(mr.getParameter("stock"));
		int fieldnum = Integer.parseInt(mr.getParameter("fieldnum"));
		ItemVo vo = new ItemVo(0, item_name, price, null, stock, fieldnum);
		ItemDao dao = ItemDao.getInstance();

		//��� insert�� ��ǰ�� pnum���ϱ�
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
			System.out.println("�����߰�����");
			
		}else {
			System.out.println("�����߰�����");
		}
	}		
}