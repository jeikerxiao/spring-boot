package com.jeiker.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRamqApplicationTests {

	@Autowired
	private Producer producer;

	@Test
	public void contextLoads() {
	}

	@Test
	public void hello() throws Exception {
		// 生产者: 生产消息
		producer.send();
	}

}
