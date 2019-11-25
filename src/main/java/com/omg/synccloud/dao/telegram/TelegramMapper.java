package com.omg.synccloud.dao.telegram;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.omg.synccloud.entity.mysql.ogtelegram.TelegramEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TelegramMapper extends BaseMapper<TelegramEntity> {
    boolean insertTelegram(@Param("telegram") List<TelegramEntity> telegram);
}
