package user.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import user.dao.RvCommDao_kdy;
import user.vo.RvCommVo_kdy;

@WebServlet("/comm.do")
public class RvCommController_kdy extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String cmd = req.getParameter("cmd");
		if(cmd.equals("insert")) {
			insert(req,resp);
		}else if(cmd.equals("list")) {
			list(req,resp);
		}
	}
	private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int rv_num = Integer.parseInt(req.getParameter("rv_num"));
		RvCommDao_kdy dao = new RvCommDao_kdy();
		ArrayList<RvCommVo_kdy> list = dao.list(rv_num); 
		JSONArray arr = new JSONArray();
		for(RvCommVo_kdy vo : list) {
			JSONObject json = new JSONObject();
			System.out.println(vo.getId());
			json.put("num", vo.getNum());
			json.put("commId", vo.getId());
			json.put("comments", vo.getComments());
			json.put("regdate", vo.getRegdate());
			arr.put(json);
		}
		JSONObject result =new JSONObject();
		result.put("list", arr);
		
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw = resp.getWriter();
		pw.println(result);
		pw.close();
	}
	
	private void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int rv_num = Integer.parseInt(req.getParameter("rv_num"));
		String id = req.getParameter("commId");
		String pwd = req.getParameter("commPwd");
		if(pwd.equals("")) {
			pwd=null;
		}
		String comments = req.getParameter("comments");
		RvCommVo_kdy vo = new RvCommVo_kdy(0,id,pwd,comments,null,rv_num);
		RvCommDao_kdy dao = new RvCommDao_kdy();
		int n = dao.insert(vo);
		
		String result = "fail";
		JSONObject json=new JSONObject();
		
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		if(n>0) {
			result = "success";
		}
		json.put("result", result);
		pw.println(json.toString());
		pw.close();
	}
}
