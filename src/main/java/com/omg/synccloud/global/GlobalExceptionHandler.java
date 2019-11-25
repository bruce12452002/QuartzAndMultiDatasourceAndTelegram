package com.omg.synccloud.global;

import lombok.extern.log4j.Log4j2;
import org.quartz.SchedulerException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Log4j2(topic = "fileLogger")
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    String handleException(Exception e) {
        rollback(TransactionAspectSupport.currentTransactionStatus(), e.getMessage());
        return "globalException:" + e.getMessage();
    }

    @ExceptionHandler(ClassNotFoundException.class)
    @ResponseBody
    String handleClassNotFoundException(ClassNotFoundException e) {
        rollback(TransactionAspectSupport.currentTransactionStatus(), e.getMessage());
        return "globalClassNotFoundException:" + e.getMessage();
    }

    @ExceptionHandler(SchedulerException.class)
    @ResponseBody
    String handleSchedulerException(SchedulerException e) {
        rollback(TransactionAspectSupport.currentTransactionStatus(), e.getMessage());
        return "globalSchedulerException:" + e.getMessage();
    }

    private void rollback(TransactionStatus status, String msg) {
        log.error(msg);
        if (!status.isRollbackOnly()) {
            status.setRollbackOnly();
        }
    }
}
