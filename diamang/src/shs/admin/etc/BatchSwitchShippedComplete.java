package shs.admin.etc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import shs.admin.dao.order.ShippedCompleteDao;
import shs.admin.vo.order.SwitchShippedLogVo;

public class BatchSwitchShippedComplete {
   public BatchSwitchShippedComplete() {
      System.out.println("batch 호출");
      
      TimerTask task = new TimerTask() {
         @Override
         public void run() {
            // 배치에서 실행될 메소드 호출
            createShippedLog();
            System.out.println("createShippedLog()");
         }
      };
      
      
      Timer timer = new Timer(true);
      Calendar cal = Calendar.getInstance();
      // 1일에 한번 실행. 현재는 시연을 위해 주석으로 막아놓음
//      cal.set(Calendar.HOUR_OF_DAY, 0);
//      cal.set(Calendar.MINUTE, 0);
//      cal.set(Calendar.SECOND, 0);
//      cal.add(Calendar.DATE, 1);	
      //scheduleAtFixedRate(수행할 작업, 최초시작시간, 반복주기)
     // timer.scheduleAtFixedRate(task, new Date(cal.getTimeInMillis()), /*1000 * 60 * 60 * 24*/1000*60);
   }

   // 배치에서 실행될 메소드 정의
   public void createShippedLog() {
      String path = BatchSwitchShippedComplete.class.getResource("/").getPath();
      path = path.replaceAll("/WEB-INF/classes/", "");
      File file = new File(path+"/log");
      if(file.isDirectory()==false) {
    	  file.mkdir();    	  
      }      
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
         pw = new PrintWriter(path + "/log/shipped_log" + date + ".txt");

         ShippedCompleteDao dao = new ShippedCompleteDao();
         ArrayList<SwitchShippedLogVo> list = dao.getShippedComplete();

         for (SwitchShippedLogVo vo : list) {
        	 System.out.println(vo.toString());
        	 pw.println(vo.toString());
         }
      } catch (FileNotFoundException e) {
         e.printStackTrace();
      } finally {
         pw.close();
      }
   }
}