package com.dzy.test.ordiry;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author dengzhiyuan
 * @version 1.0
 * @date 2017/6/23
 * @since 1.0
 */
public class TimeTest {

    public Long GenerateTimestamp(Date time) {
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化时间
        Date startTime = null;
        try {
            startTime = d.parse("1970-01-01 00:00:00");//根据时区来的,parse方法有猫腻
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (time.compareTo(startTime) < 0) {
            time = startTime;
        }
        return (Long) ((time.getTime() - startTime.getTime())/1000/3600 );
    }

    @Test
    public void testTime(){
        Long i = GenerateTimestamp(new Date());
        System.out.println(i==System.currentTimeMillis()/1000/3600);

    }
}
