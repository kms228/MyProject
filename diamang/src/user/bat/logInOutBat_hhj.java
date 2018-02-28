package user.bat;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import shs.admin.vo.members.MembersVo;
import sun.font.CreatedFontTracker;
import sun.security.timestamp.Timestamper;
import user.dao.MembersDao_hhj;

public class logInOutBat_hhj {
	public logInOutBat_hhj() {
		System.out.println("logInOut불러내기");
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				// 배치시 실행할 메소드 호출
				createlogInOutBatLog();
				System.out.println("logInOut불러내기");
			}
		}; // TimerTask
		Timer timer = new Timer(true);
		Calendar cal = Calendar.getInstance();
		cal.set(2018, 1, 25, 23, 59, 0);
		timer.scheduleAtFixedRate(task, new Date(cal.getTimeInMillis()), 1000 * 60 * 60 * 24);

	}

	public void createlogInOutBatLog() {
		String path = logInOutBat_hhj.class.getResource("/").getPath();
		path = path.replaceAll("/WEB-INF/classes/", "");

		Calendar cal = Calendar.getInstance();
		int y = cal.get(Calendar.YEAR);
		int m = cal.get(Calendar.MONTH) + 1;
		int d = cal.get(Calendar.DATE);

		String date = "";

		date += y;
		if (m < 10) {
			date += "0";
		}
		date += m;
		date += d;

		PrintWriter pw = null;

		try {
			pw = new PrintWriter(path + "/log/loginout_log" + date + ".txt");

			MembersDao_hhj dao = new MembersDao_hhj();
			ArrayList<MembersVo> login = dao.date(date);

			for (MembersVo vo : login) {
				pw.println(vo);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			pw.close();
		}

	}

}
