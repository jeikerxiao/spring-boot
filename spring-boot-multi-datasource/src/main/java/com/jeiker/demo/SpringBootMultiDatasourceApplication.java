package com.jeiker.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = {
		DataSourceAutoConfiguration.class
})
// 设置事务执行顺序(需要在切换数据源之后，否则只走主库)
@EnableTransactionManagement(order = 2)
@MapperScan(basePackages = "com.jeiker.demo.dao")
public class SpringBootMultiDatasourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMultiDatasourceApplication.class, args);
	}
}
