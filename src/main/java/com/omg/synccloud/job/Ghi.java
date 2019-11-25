package com.omg.synccloud.job;

import com.omg.synccloud.service.xxx.GhiService;
import org.quartz.PersistJobDataAfterExecution;

import javax.annotation.Resource;
import java.util.Date;

@PersistJobDataAfterExecution
public class Ghi implements BaseJob {
    @Resource
    private GhiService cashplatformBetOutCVideobettingServiceImpl;

    @Override
    public void pre(Date sDate, Date eDate) {
        cashplatformBetOutCVideobettingServiceImpl.doGhi(sDate, eDate);
    }
}
