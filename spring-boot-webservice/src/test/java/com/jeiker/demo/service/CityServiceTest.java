package com.jeiker.demo.service;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * Description: webservice 测试webservice 服务
 * Created by jeikerxiao on 2018/7/6 下午1:35
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CityServiceTest {

    @Test
    public void sayHello() {
        // 接口地址
        String address = "http://localhost:8080/demo/api?wsdl";
        // 代理工厂
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        // 设置代理地址
        jaxWsProxyFactoryBean.setAddress(address);
        // 设置接口类型
        jaxWsProxyFactoryBean.setServiceClass(CityService.class);
        // 创建一个代理接口实现
        CityService cs = (CityService) jaxWsProxyFactoryBean.create();
        // 数据准备
        String userName = "xiao";
        // 调用代理接口的方法调用并返回结果
        String result = cs.sayHello(userName);
        System.out.println("返回结果:" + result);
        assertEquals(result, "xiao hello");
    }
}