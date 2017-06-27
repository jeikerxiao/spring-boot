package com.jeiker.demo;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消息: 消费者
 * @Author : xiao
 * @Date : 17/6/27 下午3:59
 */
@Component
@RabbitListener(queues = "hello")
public class Consumer {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Consumer : " + hello);
    }
}
