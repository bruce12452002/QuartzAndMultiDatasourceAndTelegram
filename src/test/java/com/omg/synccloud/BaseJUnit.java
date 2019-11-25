package com.omg.synccloud;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SynccloudApplication.class)
public abstract class BaseJUnit {
    @BeforeClass
    public static void beforeClass() {
    }

    @Before
    public void before() {
    }

    @After
    public void after() {
    }

    @AfterClass
    public static void afterClass() {
    }
}
