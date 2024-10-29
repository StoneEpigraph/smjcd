package cn.sm.demo.date;

import sun.java2d.pipe.SpanShapeRenderer;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName DateTest
 * @Description TODO
 * @Author StoneEpigraph
 * @Date 4/25/22 10:28 AM
 * @Version 1.0
 **/
public class DateTest {

    static int commitTime = 120;
    public static void main(String[] args) {
//        Calendar cal = Calendar.getInstance();
//        cal.set(2022, 6, 1);
//        Date time = cal.getTime();
//        System.out.println(time);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(sdf.format(time));
//
//        long second = LocalDateTime.now().minusSeconds(commitTime).atZone(ZoneId.systemDefault()).toEpochSecond();
//        long millisecond = LocalDateTime.now().minusSeconds(commitTime).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
//        System.out.println(second);
//        System.out.println(millisecond);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        Date currDate = new Date();
        cal.setTime(currDate);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
//        cal.set(Calendar.DAY_OF_MONTH, 1);
        String endTime = sdf.format(cal.getTime());
        cal.add(Calendar.MONTH, -1);
        String startTime = sdf.format(cal.getTime());
        System.out.println(startTime);
        System.out.println(endTime);
    }
}
