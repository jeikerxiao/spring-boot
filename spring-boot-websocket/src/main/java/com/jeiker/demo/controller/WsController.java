package com.jeiker.demo.controller;

import com.jeiker.demo.model.RequestMessage;
import com.jeiker.demo.model.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * @author : xiao
 * @date : 17/11/2 上午11:01
 * @description :
 */
@Controller
public class WsController {

    private static final Logger logger = LoggerFactory.getLogger(WsController.class);

    @MessageMapping("/welcome")
    @SendTo("/topic/getResponse")
    public ResponseMessage say(RequestMessage message) {
        logger.info(message.getName());
        return new ResponseMessage("welcome," + message.getName() + " !");
    }
}
