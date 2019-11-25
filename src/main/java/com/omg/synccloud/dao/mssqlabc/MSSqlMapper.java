package com.omg.synccloud.dao.mssqlabc;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MSSqlMapper {
    List<?> getMemberByDate(@Param("sDate") String sDate, @Param("eDate") String eDate);
}