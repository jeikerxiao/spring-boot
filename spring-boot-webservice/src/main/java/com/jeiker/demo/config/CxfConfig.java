package com.jeiker.demo.config;

import com.jeiker.demo.service.CityService;
import com.jeiker.demo.service.HelloService;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;
/**
 * Description: spring-boot-webservice
 * Created by jeikerxiao on 2018/7/6 上午11:48
 */
@Configuration
public class CxfConfig {

    @Autowired
    private Bus bus;
    @Autowired
    private HelloService helloService;
    @Autowired
    private CityService cityService;

    @Bean
    public ServletRegistrationBean dispatcherServlet() {
        return new ServletRegistrationBean(new CXFServlet(),"/demo/*");
    }

    //    http://localhost:8080/demo/hello?wsdl
    @Bean
    public Endpoint endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, helloService);
        endpoint.publish("/hello");
        return endpoint;
    }
}