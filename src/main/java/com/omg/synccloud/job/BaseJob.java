package com.omg.synccloud.job;

import com.omg.synccloud.util.DateUtil;
import com.omg.synccloud.util.JobUtil;
import com.omg.synccloud.util.Log4j2Util;
import com.omg.synccloud.entity.BaseEntity;
import com.omg.synccloud.entity.mysql.ogtelegram.BaseTelegram;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.BeanUtils;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public interface BaseJob extends Job {
    default void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        int secCount = jobDataMap.getInt("secCount");
        String jobClassName = jobDataMap.getString("jobClassName");
        int freq = jobDataMap.getInt("freq");
        long now = jobDataMap.getLong("now");

        if (secCount % freq == 0) {
            int times = secCount / freq;
            String side = jobDataMap.getString("side");
            int period = jobDataMap.getInt("period");

            Map<JobUtil.JobDateName, Date> map;
            if ("Pre".equals(side)) {
                if (!"CashplatformHistoryOutXxhistory".equals(jobClassName)) {
                    commonPre(JobUtil.preFomula(period), jobClassName);
                } else {
                    commonPre(JobUtil.preOutXxhistoryFomula(period), jobClassName);
                }
            } else if ("Post".equals(side)) {
                map = JobUtil.postFomula(now, times, period);
                Date loopStartTime = map.get(JobUtil.JobDateName.LOOP_START_TIME);
                Date loopStopTime = map.get(JobUtil.JobDateName.LOOP_STOP_TIME);

                Date minBefore = DateUtil.getChangeDate(loopStartTime, Calendar.MINUTE, period * times * -1);
                final String SPECIAL_PROGRAMMER = "CashPlatformHistoryOutXXHistory30Days";
                if (minBefore.after(loopStopTime) && !SPECIAL_PROGRAMMER.equals(jobClassName)) {
                    commonPost(map, jobClassName);
                } else {
                    if (SPECIAL_PROGRAMMER.equals(jobClassName) &&
                            minBefore.after(DateUtil.getChangeDate(loopStartTime, Calendar.DATE, -5))) {
                        commonPost(map, jobClassName);
                    }
                }
            } else if ("PostOg2".equals(side)) {
                final String MEMBER = "CashplatformMember";
                final String GAME_RESULT_OUT = "CashplatformCVideoGameResultOut";

                if (!Arrays.asList(MEMBER, GAME_RESULT_OUT).contains(jobClassName)) {
                    map = JobUtil.postOg2Fomula(now, times, period);
                    Date loopStartTime = map.get(JobUtil.JobDateName.LOOP_START_TIME);
                    Date loopStopTime = map.get(JobUtil.JobDateName.LOOP_STOP_TIME);

                    Date minBefore = DateUtil.getChangeDate(loopStartTime, Calendar.MINUTE, -20 * times);
                    if (minBefore.after(loopStopTime)) {
                        commonPost(map, jobClassName);
                    }
                } else if (MEMBER.equals(jobClassName)) {
                    map = JobUtil.postOg2MemberFomula(now, times, period);
                    Date loopStartTime = map.get(JobUtil.JobDateName.LOOP_START_TIME);
                    Date loopStopTime = map.get(JobUtil.JobDateName.LOOP_STOP_TIME);
                    Date minBefore = DateUtil.getChangeDate(loopStartTime, Calendar.MINUTE, -360 * times);

                    if (minBefore.after(loopStopTime)) {
                        commonPost(map, jobClassName);
                    }
                } else if (GAME_RESULT_OUT.equals(jobClassName)) {
                    map = JobUtil.postOg2GameresultoutFomula(now, times, period);
                    Date loopStartTime = map.get(JobUtil.JobDateName.LOOP_START_TIME);
                    Date loopStopTime = map.get(JobUtil.JobDateName.LOOP_STOP_TIME);
                    Date minBefore = DateUtil.getChangeDate(loopStartTime, Calendar.MINUTE, -10 * times);

                    if (minBefore.after(loopStopTime)) {
                        commonPost(map, jobClassName);
                    }
                }
            }
        }
        jobDataMap.put("secCount", ++secCount);
    }

    default void pre(Date sDate, Date eDate) {
    }

    default void post(Date sDate, Date eDate) {
    }

    static <T extends BaseTelegram, E extends BaseEntity> T setTelegram(E baseEntity, T baseTelegram) {
        BeanUtils.copyProperties(baseEntity, baseTelegram);
        return baseTelegram;
    }

    default void commonPre(Map<JobUtil.JobDateName, Date> map, String jobClassName) {
        Date sDate = map.get(JobUtil.JobDateName.START_DATE);
        Date eDate = map.get(JobUtil.JobDateName.END_DATE);
        Log4j2Util.printLog(sDate, eDate, jobClassName);
        try {
            pre(sDate, eDate);
        } catch (Exception e) {
            Log4j2Util.printErrorLog(jobClassName, e.getMessage());
        }
    }

    default void commonPost(Map<JobUtil.JobDateName, Date> map, String jobClassName) {
        Date sDate = map.get(JobUtil.JobDateName.START_DATE);
        Date eDate = map.get(JobUtil.JobDateName.END_DATE);
        Log4j2Util.printLog(sDate, eDate, jobClassName);
        try {
            post(sDate, eDate);
        } catch (Exception e) {
            Log4j2Util.printErrorLog(jobClassName, e.getMessage());
        }
    }
}
