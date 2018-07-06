package com.jeiker.demo.service;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
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

    /**
     * 方式1.代理类工厂的方式,需要拿到对方的接口
     */
    @Test
    public void sayHello() {
        // 接口地址
        String address = "http://localhost:8080/demo/hello?wsdl";
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
        String result = cs.sayHi(userName);
        System.out.println("返回结果:" + result);
        assertEquals(result, "xiao hello");
    }

    /**
     * 动态调用
     */
    @Test
    public void sayHello2() {
// 创建动态客户端
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient("http://localhost:8080/demo/hello?wsdl");
        // 需要密码的情况需要加上用户名和密码
        // client.getOutInterceptors().add(new ClientLoginInterceptor(USER_NAME,
        // PASS_WORD));
        Object[] objects = new Object[0];
        try {
            // invoke("方法名",参数1,参数2,参数3....);
            objects = client.invoke("sayHello", "xiao");
            System.out.println("返回数据:" + objects[0]);
        } catch (java.lang.Exception e) {
            e.printStackTrace();
        }

    }
}