package com.jeiker.demo.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @Author : xiao
 * @Date : 17/9/30 上午11:29
 */
public class StartupEvent implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger = LoggerFactory.getLogger(StartupEvent.class);

    private static ApplicationContext context;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        try {
            context = contextRefreshedEvent.getApplicationContext();
            SysConfig sysConfig = context.getBean(SysConfig.class);
            // 接收UDP消息并保存至redis中
            UdpServer udpServer = (UdpServer) StartupEvent.getBean(UdpServer.class);
            // 获取属性列表中的端口号，运行 UDP 服务器。
            udpServer.run(sysConfig.getUdpReceivePort());

            // 这里可以开启多个线程去执行不同的任务


        } catch (Exception e) {
            logger.error("Exception", e);
        }
    }

    public static Object getBean(Class beanName) {
        return context != null ? context.getBean(beanName) : null;
    }
}
