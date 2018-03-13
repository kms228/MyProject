package admin.batch;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import admin.dao.ItemDao;
import admin.vo.ItemVo;

public class InsertBatch {
	public InsertBatch() {
		System.out.println("InsertBatch »£√‚");
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				System.out.println("InsertBatch");
				createInsertLog();
			}
		};
		Timer timer = new Timer(true);
		Calendar cal = Calendar.getInstance();
		cal.set(2018, 1, 25, 23, 59, 0);
		timer.scheduleAtFixedRate(task, new Date(cal.getTimeInMillis()), 1000 * 60 * 60 * 24);
	}

	private void createInsertLog() {
		String path = InsertBatch.class.getResource("/").getPath();
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
		if (d < 10) {
			date += "0";
		}
		date += d;
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(path + "/log/deletes_log_" + date + ".txt");
			ItemDao dao = ItemDao.getInstance();
			ArrayList<ItemVo> list = dao.logList(date);
			for (ItemVo vo : list) {
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
