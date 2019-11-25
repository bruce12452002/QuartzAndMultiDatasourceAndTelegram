package com.omg.synccloud.util;

import lombok.extern.log4j.Log4j2;

import java.util.Date;

@Log4j2(topic = "fileLogger")
public class Log4j2Util {
    public static void printLog(Date sDate, Date eDate, String jobClassName) {
        log.info("{}{}{}{}{}", jobClassName, StringUtil.getNewLine(),
                DateUtil.getDateString(sDate), StringUtil.getNewLine(),
                DateUtil.getDateString(eDate));
    }

    public static void printErrorLog(String jobClassName, String msg) {
        log.error("{}{}{}", jobClassName, StringUtil.getNewLine(), msg);
    }
}
