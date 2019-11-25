package com.omg.synccloud.dao.telegram;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class Ttt {
    @TableId
    private Integer userid;
    private Integer age;
    private Date birthday;
}
