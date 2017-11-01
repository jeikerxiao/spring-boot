package com.jeiker.demo.demo2;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;

/**
 * @author : xiao
 * @date : 17/11/1 上午11:12
 * @description :
 */
@Service
public class Publisher {

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void publish(String destinationName, String message) {
        Destination destination = new ActiveMQTopic(destinationName);
        System.out.println("============>>>>> 发布topic消息 " + message);
        jmsMessagingTemplate.convertAndSend(destination, message);
    }
}