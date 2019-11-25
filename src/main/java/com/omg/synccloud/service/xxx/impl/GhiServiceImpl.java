package com.omg.synccloud.service.xxx.impl;

import com.omg.synccloud.service.TelegramService;
import com.omg.synccloud.service.xxx.GhiService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Log4j2(topic = "fileLogger")
@Service
public class GhiServiceImpl implements GhiService {
    @Resource
    private TelegramService telegramServiceImpl;

    @Override
    public void doGhi(Date sDate, Date eDate) {

    }
}