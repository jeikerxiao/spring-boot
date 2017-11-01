package com.jeiker.demo.demo1;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

/**
 * @author : xiao
 * @date : 17/11/1 上午10:51
 * @description :
 */
@Service
public class Consumer {

    @JmsListener(destination = "test.queue")
    public void receiveMsg(String text) {
        System.out.println("<<<<<<============ 收到消息： " + text);
    }
}