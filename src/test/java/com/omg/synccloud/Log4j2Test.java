package com.omg.synccloud;

import lombok.extern.log4j.Log4j2;
import org.junit.Ignore;
import org.junit.Test;

@Log4j2(topic = "fileLogger")
public class Log4j2Test extends BaseJUnit {
    @Ignore
    @Test
    public void logForLoopTest() throws Exception {
        for (var i = 0; i < 100; i++) {
            log.fatal("fatal" + i);
            log.error("error" + i);
            Thread.sleep(200);
            log.warn("warn" + i);
            log.info("info" + i);
            log.debug("debug" + i);
            log.trace("trace" + i);
        }
    }

    @Test
    public void logTest() {
        log.fatal("fatal"); // fatal
        log.error("error"); // fatal error
        log.warn("warn");   // fatal error warn
        log.info("info");   // fatal error warn info 預設
        log.debug("debug"); // fatal error warn info debug
        log.trace("trace"); // fatal error warn info debug trace
    }

}
