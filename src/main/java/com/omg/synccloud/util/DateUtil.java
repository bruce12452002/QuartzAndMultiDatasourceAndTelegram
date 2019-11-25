package com.omg.synccloud.util;

import lombok.extern.log4j.Log4j2;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@Log4j2(topic = "fileLogger")
public class DateUtil {
    public static Date getChangeDate(int addUnit, int addNum) {
        return getChangeDate(System.currentTimeMillis(), addUnit, addNum);
    }

    public static Date getChangeDate(long dateTime, int addUnit, int addNum) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(dateTime);
        cal.add(addUnit, addNum);
        return cal.getTime();
    }

    public static Date getChangeDate(Date dateTime, int addUnit, int addNum) {
        return getChangeDate(dateTime.getTime(), addUnit, addNum);
    }

    public static Date getChangeDate(Map<Integer, Integer> map) {
        return getChangeDate(System.currentTimeMillis(), map);
    }

    public static Date getChangeDate(long dateTime, Map<Integer, Integer> map) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(dateTime);
        map.forEach((k, v) -> cal.add(k, v));
        return cal.getTime();
    }

    public static Date getChangeDate(Date dateTime, Map<Integer, Integer> map) {
        return getChangeDate(dateTime.getTime(), map);
    }

    public static Date getFormatDate(Date date, DateFormatter dfm) {
        Date resultDate = null;
        try {
            DateFormat df = new SimpleDateFormat(dfm.formatter);
            resultDate = df.parse(df.format(date));
        } catch (ParseException e) {
            log.error(e.getMessage());
        }
        return resultDate;
    }

    public static Date getDate(long dateTime) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(dateTime);
        return cal.getTime();
    }

    public static Date getDate(int year, int month, int date, int hourOfDay, int minute, int second) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, date, hourOfDay, minute, second);
        return cal.getTime();
    }

    public static String getDateString(DateFormatter dfm, Date date) {
        return new SimpleDateFormat(dfm.formatter).format(date);
    }

    public static String getDateString(Date date) {
        return new SimpleDateFormat(DateFormatter.YMDHMS.formatter).format(date);
    }

    public enum DateFormatter {
        YMD("yyyy-MM-dd"),
        YMDHMS("yyyy-MM-dd HH:mm:ss"),
        YMDHM0("yyyy-MM-dd HH:mm:00"),
        YMDHM59("yyyy-MM-dd HH:mm:59"),
        YMDHMS_0_0("yyyy-MM-dd HH:mm:00.000"),
        YMDHMS_0("yyyy-MM-dd HH:mm:ss.000"),
        YMDHMS_9("yyyy-MM-dd HH:mm:ss.999"),
        YMDHM_59_9("yyyy-MM-dd HH:mm:59.999");

        public String formatter;

        DateFormatter(String formatter) {
            this.formatter = formatter;
        }
    }
}
