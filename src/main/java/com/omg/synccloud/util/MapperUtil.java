package com.omg.synccloud.util;

import com.omg.synccloud.entity.BaseEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MapperUtil {
    <T extends BaseEntity> boolean insertBatch(@Param("list") List<T> list);
}