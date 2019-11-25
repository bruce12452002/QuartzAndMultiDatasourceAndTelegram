package com.omg.synccloud.listener;

import org.quartz.listeners.TriggerListenerSupport;

public class QuartzTriggerListener extends TriggerListenerSupport {
    @Override
    public String getName() {
        return "myTriggerListener";
    }
}
