package com.sk.cloudmvc;

import java.util.Calendar;
import java.util.Date;

/**
 * @author qiaochunxiang
 * @date 2020/4/27 14:30
 */
public class DateTest {
    public static void main(String[] args) {
        Date startDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(Calendar.YEAR, 1);
        Date endTime = cal.getTime();
        System.out.println(endTime);
    }
}
