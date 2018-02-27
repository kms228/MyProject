package shs.admin.etc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

public class DefaultGrade implements ServletContextListener{

	public static String GNUM;
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("contextDestoryed()");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			String realPath = arg0.getServletContext().getRealPath("");			
			System.out.println("realPath : "+ realPath);
			File file = new File(realPath+"defaultGrade.txt");
			if(!file.exists()) {
				PrintWriter fos = new PrintWriter(realPath+"defaultGrade.txt");
				fos.println("1");
				fos.close();
			} 
			BufferedReader br = new BufferedReader(new FileReader(realPath+"defaultGrade.txt"));
			String readLine = br.readLine();
			GNUM = readLine;
			System.out.println("defaultgnum :"+ GNUM);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	static public int setDefaultGrade(String gnum, String realPath) {
		try {			
			PrintWriter fos = new PrintWriter(realPath + "defaultGrade.txt");
			fos.println(gnum);
			fos.close();
			GNUM = gnum;
			System.out.println("디폴트g넘 : "+GNUM);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return 0;
	}
//	static {
//	try {
//		System.out.println("서버 시작11111111");
//		File file = new File("c:/java/out.txt");
//		if(!file.exists()) {
//			PrintWriter fos = new PrintWriter("c:/java/out.txt");
//			fos.println("1");
//			fos.close();
//		}
//		BufferedReader br = new BufferedReader(new FileReader("c:/java/out.txt"));
//		String readLine = br.readLine();
//		DefaultGrade = readLine;
//	} catch (FileNotFoundException e) {
//		e.printStackTrace();
//	} catch (IOException e) {
//		e.printStackTrace();
//	}
//}
}
