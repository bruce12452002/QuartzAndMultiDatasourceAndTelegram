package com.omg.synccloud.job;

import org.quartz.PersistJobDataAfterExecution;

import java.util.Date;

@PersistJobDataAfterExecution
public class Abc implements BaseJob {

    @Override
    public void pre(Date sDate, Date eDate) {

    }

    @Override
    public void post(Date sDate, Date eDate) {

    }
}
