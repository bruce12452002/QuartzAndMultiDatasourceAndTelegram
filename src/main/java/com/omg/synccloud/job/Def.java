package com.omg.synccloud.job;

import com.omg.synccloud.util.DateUtil;
import lombok.extern.log4j.Log4j2;
import org.quartz.PersistJobDataAfterExecution;

import java.util.Date;

@Log4j2(topic = "fileLogger")
@PersistJobDataAfterExecution
public class Def implements BaseJob {

    @Override
    public void pre(Date sDate, Date eDate) {
        final String sDateStr = DateUtil.getDateString(DateUtil.DateFormatter.YMDHMS_0_0, sDate);
        final String eDateStr = DateUtil.getDateString(DateUtil.DateFormatter.YMDHM_59_9, eDate);

    }
}
