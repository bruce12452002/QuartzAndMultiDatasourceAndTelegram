package com.omg.synccloud.dao.telegram;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TestMapper extends BaseMapper<Ttt> {
//    @Select("call og_quartz.proc_user_data()")
//    @Options(statementType = StatementType.CALLABLE)
//    List xxx();
//
    @Select("call og_quartz.proc_user_data1(#{xxx})")
    @Options(statementType = StatementType.CALLABLE)
    List<Ttt> ooo(Map<String, Object> map);

    @Select("select * from og_cashplatformhistoryout.user_data")
    @Results(
            id = "ttt",
            value = {
                    @Result(column = "userid", property = "userid"),
                    @Result(column = "age", property = "age"),
                    @Result(column = "birthday", property = "birthday")
            }
    )
    Ttt findByUserid(int id);

    @Select("call og_quartz.proc_user_data2(#{xxx}, #{rtn, mode=OUT, jdbcType=CURSOR, resultMap=ttt})")
    @Options(statementType = StatementType.CALLABLE)
    List<Ttt> qoo(Map<String, Object> map);
}
