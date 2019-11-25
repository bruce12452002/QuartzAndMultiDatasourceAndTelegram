package com.omg.synccloud.util;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JobUtil {
    public static final int INSERT_BATCH = 35;

    public static Map<JobDateName, Date> preFomula(int period) {
        Map<JobUtil.JobDateName, Date> map = new HashMap<>();
        map.put(JobUtil.JobDateName.START_DATE, DateUtil.getChangeDate(Calendar.MINUTE, period * -1));
        map.put(JobUtil.JobDateName.END_DATE, new Date());
        return map;
    }

    public static Map<JobDateName, Date> preOutXxhistoryFomula(int period) {
        Map<JobUtil.JobDateName, Date> map = new HashMap<>();
        map.put(JobUtil.JobDateName.START_DATE, DateUtil.getChangeDate(Calendar.MINUTE, period * -1));
        map.put(JobUtil.JobDateName.END_DATE, DateUtil.getChangeDate(Calendar.MINUTE, 3));
        return map;
    }

    public static Map<JobUtil.JobDateName, Date> postFomula(long now, int times, int period) {
        Map<JobUtil.JobDateName, Date> map = new HashMap<>();
        map.put(JobUtil.JobDateName.START_DATE, DateUtil.getChangeDate(now, Calendar.MINUTE,
                period * (times + 1) * -1));
        map.put(JobUtil.JobDateName.END_DATE, DateUtil.getChangeDate(now, Calendar.MINUTE,
                period * times * -1));
        map.put(JobDateName.LOOP_START_TIME, DateUtil.getDate(now));
        map.put(JobDateName.LOOP_STOP_TIME, DateUtil.getChangeDate(now, Calendar.DATE, -7));
        return map;
    }

    public static Map<JobUtil.JobDateName, Date> postOg2Fomula(long now, int times, int period) {
        Map<JobUtil.JobDateName, Date> map = new HashMap<>();
        map.put(JobUtil.JobDateName.START_DATE, DateUtil.getChangeDate(now,
                Calendar.MINUTE, -20 * times - period));
        map.put(JobUtil.JobDateName.END_DATE, DateUtil.getChangeDate(now, Calendar.MINUTE, -20 * times));
        map.put(JobDateName.LOOP_START_TIME, DateUtil.getDate(now));
        map.put(JobDateName.LOOP_STOP_TIME, DateUtil.getChangeDate(now, Calendar.DATE, -7));
        return map;
    }

    public static Map<JobUtil.JobDateName, Date> postOg2MemberFomula(long now, int times, int period) {
        Map<JobUtil.JobDateName, Date> map = new HashMap<>();
        map.put(JobUtil.JobDateName.START_DATE, DateUtil.getChangeDate(now,
                Calendar.MINUTE, -360 * times - period));
        map.put(JobUtil.JobDateName.END_DATE, DateUtil.getChangeDate(now, Calendar.MINUTE, -360 * times));
        map.put(JobDateName.LOOP_START_TIME, DateUtil.getDate(now));
        try {
            map.put(JobDateName.LOOP_STOP_TIME, DateUtil.getDate(
                    2010, Calendar.JANUARY, 1, 0, 0, 0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public static Map<JobUtil.JobDateName, Date> postOg2GameresultoutFomula(long now, int times, int period) {
        Map<JobUtil.JobDateName, Date> map = new HashMap<>();
        map.put(JobUtil.JobDateName.START_DATE, DateUtil.getChangeDate(now,
                Calendar.MINUTE, -10 * times - period));
        map.put(JobUtil.JobDateName.END_DATE, DateUtil.getChangeDate(now, Calendar.MINUTE, -10 * times));
        map.put(JobDateName.LOOP_START_TIME, DateUtil.getDate(now));
        try {
            map.put(JobDateName.LOOP_STOP_TIME, DateUtil.getDate(
                    2015, Calendar.JANUARY, 1, 0, 0, 0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public enum JobDateName {
        LOOP_START_TIME,
        LOOP_STOP_TIME,
        START_DATE,
        END_DATE
    }
}