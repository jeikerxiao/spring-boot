package com.jeiker.demo.service;

/**
 * @Author : xiao
 * @Date : 17/9/20 下午3:52
 */
public interface MailService {

    void sendSimpleMail(String to, String subject, String content);
}
