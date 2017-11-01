package com.jeiker.demo;

import com.jeiker.demo.demo1.Producer;
import com.jeiker.demo.demo2.Publisher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootActivemqApplicationTests {

	@Resource
	private Producer producer;
	@Resource
	private Publisher publisher;

	@Test
	public void testQueue() {
		for (int i = 0; i < 10; i++) {
			producer.sendMsg("test.queue", "Queue Message " + i);
		}
	}

	@Test
	public void testTopic() {
		for (int i = 0; i < 10; i++) {
			publisher.publish("test.topic", "Topic Message " + i);
		}
	}
}
