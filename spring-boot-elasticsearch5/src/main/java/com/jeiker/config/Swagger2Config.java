package com.jeiker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author : xiao
 * @date : 17/12/7 下午5:01
 * @description :
 */
@Configuration
@EnableSwagger2
@ComponentScan("com.jeiker.controller")
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.jeiker.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("Spring Boot 整合 swagger ui")
                .license("MIT")
                .licenseUrl("http://opensource.org/licenses/MIT")
                .contact(new Contact("jeiker","jeiker.cn","jeiker@126.com"))
                .version("1.0")
                .build();
        return apiInfo;
    }
}
