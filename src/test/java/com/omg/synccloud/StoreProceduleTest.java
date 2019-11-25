package com.omg.synccloud;

import com.omg.synccloud.dao.telegram.TestMapper;
import com.omg.synccloud.dao.telegram.Ttt;
import org.junit.Ignore;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoreProceduleTest extends BaseJUnit {
    @Resource
    private TestMapper TestMapper;

    @Ignore
    @Test
    public void qooTests() {
        Map<String, Object> map = new HashMap<>();
        map.put("xxx", 55);
        List<Ttt> qoo = TestMapper.qoo(map);
        System.out.println(qoo.get(0));
    }

    @Test
    public void oooTests() {
        Map<String, Object> map = new HashMap<>();
        map.put("xxx", 55);
        List<Ttt> ooo = TestMapper.ooo(map);
        System.out.println(ooo);
    }
}
