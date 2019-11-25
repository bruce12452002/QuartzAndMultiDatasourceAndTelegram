package com.omg.synccloud.listener;

import org.quartz.listeners.JobListenerSupport;

public class QuartzJobListener extends JobListenerSupport {
    @Override
    public String getName() {
        return "myJobListener";
    }
}
