package com.jeiker.demo.service;

/**
 * @Author : xiao
 * @Date : 17/9/20 下午3:52
 */
public interface MailService {

    /**
     * 发送简单的邮件
     * @param to 发送地址
     * @param subject 邮件主题
     * @param content 邮件内容
     */
    void sendSimpleMail(String to, String subject, String content);

    /**
     * 发送HTML格式的邮件
     * @param to 发送地址
     * @param subject 邮件主题
     * @param content 邮件内容
     */
    void sendHtmlMail(String to, String subject, String content);

    /**
     * 发送带附件的邮件
     * @param to 发送地址
     * @param subject 邮件主题
     * @param content 邮件内容
     */
    void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId);
}
