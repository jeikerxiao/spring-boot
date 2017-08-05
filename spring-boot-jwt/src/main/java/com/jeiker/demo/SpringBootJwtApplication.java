package com.jeiker.demo;

import com.jeiker.demo.util.JsonUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class SpringBootJwtApplication extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootJwtApplication.class, args);
	}

	/**
	 * 跨域问题
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedMethods("DELETE", "POST", "GET", "PUT")
				.allowedOrigins("*");
	}

	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		return new MappingJackson2HttpMessageConverter(JsonUtil.getObjectMapper());
	}
}
