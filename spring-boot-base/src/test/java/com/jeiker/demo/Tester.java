package com.jeiker.demo;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author : xiao
 * @Date : 17/7/31 下午3:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootBaseApplicationTests.class)
@Transactional
@Rollback
public abstract class Tester {

}
