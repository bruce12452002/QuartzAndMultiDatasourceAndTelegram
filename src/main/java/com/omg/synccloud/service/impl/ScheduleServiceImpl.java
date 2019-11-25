package com.omg.synccloud.service.impl;

import com.omg.synccloud.util.ClazzUtil;
import com.omg.synccloud.service.ScheduleService;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONObject;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Log4j2(topic = "fileLogger")
@Service
public class ScheduleServiceImpl implements ScheduleService {
    private static final File JOB_CONF_PATH = new File("src\\main\\resources\\job_config\\job.json");

    @Resource
    private Scheduler scheduler;

    private JSONArray loadJobConf() {
        StringBuilder jobJson = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(JOB_CONF_PATH))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                jobJson.append(line);
            }
        } catch (IOException e) {
            log.error("loadJobConf={}", e.getMessage());
        }
        return new JSONArray(jobJson.toString());
    }

    public void loadJob() throws Exception {
        JobDetail jobDetail;
        SimpleTrigger trigger;

        JSONArray arr = loadJobConf();
        for (var i = 0; i < arr.length(); i++) {
            JSONObject jObj = arr.getJSONObject(i);
            if ("false".equals(jObj.getString("Enable"))) {
                continue;
            }
            var jobClassName = jObj.getString("JobClassName");
            var jobClass = ClazzUtil.getClass(jobClassName).getClass();

            if (Trigger.TriggerState.NONE.name().equals(getTriggerState(jobClassName))) {
                jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobClassName).build();
                trigger = TriggerBuilder.newTrigger().withIdentity(jobClassName)
                        .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever()).build();
                setParameter(jobDetail, jObj);
                scheduler.scheduleJob(jobDetail, trigger);
            } else {
                resumeJob(jobClassName);
            }
        }
        scheduler.start();
    }

    private void setParameter(JobDetail jobDetail, JSONObject jObj) {
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        jobDataMap.put("secCount", 0);
        jobDataMap.put("jobClassName", jObj.getString("JobClassName"));
        jobDataMap.put("side", jObj.getString("Side"));
        jobDataMap.put("freq", jObj.getString("Freq"));
        jobDataMap.put("period", jObj.getString("Period"));
        jobDataMap.put("now", System.currentTimeMillis());
    }

    @Override
    public void pauseJob() {
        JSONArray arr = loadJobConf();
        for (var i = 0; i < arr.length(); i++) {
            JSONObject jObj = arr.getJSONObject(i);
            var jobClassName = jObj.getString("JobClassName");

            if (!Trigger.TriggerState.NONE.name().equals(getTriggerState(jobClassName))) {
                try {
                    scheduler.pauseJob(JobKey.jobKey(jobClassName));
                } catch (SchedulerException e) {
                    log.error("pauseJob={}", e.getMessage());
                }
            }
        }
    }

    private void resumeJob(String jobClassName) {
        try {
            scheduler.resumeJob(JobKey.jobKey(jobClassName));
        } catch (SchedulerException e) {
            log.error("resumeJob={}", e.getMessage());
        }
    }

    private String getTriggerState(String jobClassName) {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobClassName);
        Trigger.TriggerState state = Trigger.TriggerState.NONE;
        try {
            state = scheduler.getTriggerState(triggerKey);
        } catch (SchedulerException e) {
            log.info("getTriggerState={}", e.getMessage());
        }
        return state.name();
    }
}
