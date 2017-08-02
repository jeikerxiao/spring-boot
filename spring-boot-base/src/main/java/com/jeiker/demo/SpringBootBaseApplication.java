package com.jeiker.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.jeiker.demo.dao")
public class SpringBootBaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBaseApplication.class, args);
	}
}
