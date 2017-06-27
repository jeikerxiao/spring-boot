package com.jeiker.demo;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 消息: 生产者
 * @Author : xiao
 * @Date : 17/6/27 下午3:58
 */
@Component
public class Producer {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String context = "hello " + new Date();
        System.out.println("Producer : " + context);
        this.rabbitTemplate.convertAndSend("hello", context);
    }
}
