package com.jeiker.demo.demo2;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

/**
 * @author : xiao
 * @date : 17/11/1 上午10:58
 * @description :
 */
@Service
public class Subscriber {

    @JmsListener(destination = "test.topic", containerFactory = "myJmsContainerFactory")
    public void subscribe(String text) {
        System.out.println("===========<<<<<<<<收到订阅的消息" + text);
    }
}