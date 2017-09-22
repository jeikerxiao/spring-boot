package com.jeiker.demo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * @Author : xiao
 * @Date : 17/9/20 下午3:57
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {

    @Autowired
    private MailService mailService;
    @Autowired
    private TemplateEngine templateEngine;

    /**
     * 测试 发送简单邮件
     * @throws Exception
     */
    @Test
    public void sendSimpleMail() throws Exception {
        mailService.sendSimpleMail("jeiker@126.com", "test mail", "this a test e-mail from spring boot.");
    }

    /**
     * 测试 发送HTML的邮件
     * @throws Exception
     */
    @Test
    public void sendHtmlMail() throws Exception {
        String content = "<html>\n" +
                "<body>\n" +
                "    <h3>this a test e-mail from spring boot.</h3>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendHtmlMail("jeiker@126.com", "test mail", content);
    }

    /**
     * 测试 发送带附件的邮件
     * @throws Exception
     */
    @Test
    public void sendInlineResourceMail() throws Exception {
        String rscId = "head007";
        String content = "<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
        String imgPath = "/Users/xiao/Downloads/test/head.jpg";
        mailService.sendInlineResourceMail("jeiker@126.com", "test mail", content, imgPath, rscId);
    }

    /**
     * 测试 使用模板发送邮件
     */
    @Test
    public void sendTemplateMail() {
        //创建邮件正文
        Context context = new Context();
        context.setVariable("id", "77451733");
        String emailContent = templateEngine.process("emailTemplate", context);

        mailService.sendHtmlMail("jeiker@126.com", "test mail", emailContent);
    }
}