package com.jeiker.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootElkApplicationTests {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void contextLoads() {

    }

    @Test
    public void testLog() {
        for (int i = 0; i < 100; i++) {
            logger.info("===> 输出info日志。");
            logger.debug("===> 输出debug日志。");
            logger.error("===> 输出error日志。");
        }
    }
}
