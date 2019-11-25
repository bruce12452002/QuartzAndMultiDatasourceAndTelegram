package com.omg.synccloud.controller;

import com.omg.synccloud.service.ScheduleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("schedule")
public class ScheduleController {
    @Resource
    private ScheduleService scheduleService;

    @GetMapping(value = "/loadJob")
    public void loadJob() throws Exception {
        scheduleService.loadJob();
    }

    @GetMapping(value = "/pauseJob")
    public void pauseJob() {
        scheduleService.pauseJob();
    }
}
