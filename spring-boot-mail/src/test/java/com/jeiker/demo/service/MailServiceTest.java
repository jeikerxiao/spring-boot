package com.jeiker.demo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @Author : xiao
 * @Date : 17/9/20 下午3:57
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Test
    public void sendSimpleMail() throws Exception {
        mailService.sendSimpleMail("jeiker@126.com", "test mail", "this a test e-mail from spring boot.");
    }

}