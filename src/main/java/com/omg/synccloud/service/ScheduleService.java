package com.omg.synccloud.service;

public interface ScheduleService {
    /**
     * read setting file
     */
    void loadJob() throws Exception;

    /**
     * pause schedule
     */
    void pauseJob();
}
