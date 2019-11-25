package com.omg.synccloud;

import com.omg.synccloud.entity.mysql.ogtelegram.TelegramEntity;
import com.omg.synccloud.service.TelegramService;
import org.junit.Ignore;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class TelegramTest extends BaseJUnit {
    @Resource
    private TelegramService telegramServiceImpl;

    @Ignore
    @Test
    public void sendTelegramTest() {
        telegramServiceImpl.send("主旨", "內容");
    }

    @Test
    public void insertTelegramTest() {
        List<TelegramEntity> list = new ArrayList<>();
        for (var i = 1; i <= 5; i++) {
            TelegramEntity tcf = new TelegramEntity()
                    .setId(Long.valueOf(i))
                    .setDbName("db1");
            list.add(tcf);
        }

//        telegramServiceImpl.xxx(list);
    }
}