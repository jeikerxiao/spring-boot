package com.jeiker.demo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRedisApplicationTests {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Test
	public void contextLoads() {

	}

	@Test
	public void testRedis() throws Exception {
		// 保存字符串
		stringRedisTemplate.opsForValue().set("name", "xiao");
		// 查出字符串
		Assert.assertEquals("xiao", stringRedisTemplate.opsForValue().get("name"));
	}

}
