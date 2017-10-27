package com.jeiker.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author : xiao
 * @date : 17/10/26 下午5:22
 * @description : 消息接收者
 */
@Component
public class Receiver {

    private static final Logger logger = LoggerFactory.getLogger(Receiver.class);

    private Gson gson = new GsonBuilder().create();

    @KafkaListener(topics = "test_topic")
    public void processMessage(String content) {
        Message message = gson.fromJson(content, Message.class);
        logger.info("====> get message: {}", message.getMsg());
    }
}
