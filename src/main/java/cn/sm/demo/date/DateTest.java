package cn.sm.demo.date;

import sun.java2d.pipe.SpanShapeRenderer;

import java.text.SimpleDateFormat;
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
    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        cal.set(2022, 6, 1);
        Date time = cal.getTime();
        System.out.println(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(time));
    }
}
