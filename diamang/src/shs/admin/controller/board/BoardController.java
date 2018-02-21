package shs.admin.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/board.do")
public class BoardController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String cmd = req.getParameter("cmd");
		if(cmd.equals("boardnotice")) {
			
		} else if(cmd.equals("boardqna")) {
			goQna(req,resp);
		} else if(cmd.equals("boardreview")) {
			
		}
	}

	private void goQna(HttpServletRequest req, HttpServletResponse resp) {
		
	}	
}
