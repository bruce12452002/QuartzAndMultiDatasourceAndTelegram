package com.omg.synccloud.entity.mysql.ogtelegram;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Accessors(chain = true)
public class TelegramEntity implements BaseTelegram {
    private Long id;
    private String username;
    private String dbName;
}
