# spring-boot-mail

使用 spring-boot 发送邮件。

## 依赖

pom.xml

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-mail</artifactId>
</dependency>

<!-- 发送模板邮件时使用 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```

## 配置

application.properties

```
# 邮箱服务器地址
spring.mail.host=smtp.126.com
# 邮箱账号，密码
spring.mail.username=jeiker@126.com
spring.mail.password=******
spring.mail.default-encoding=UTF-8

# 发送人
mail.fromMail.addr=jeiker@126.com
```

## 邮件服务类

MailService.class 接口

```java
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

```


MailServiceImpl.class 实现

```java
@Service
public class MailServiceImpl implements MailService {

    private final Logger log = LoggerFactory.getLogger(MailServiceImpl.class);

    @Autowired
    private JavaMailSender mailSender;

    @Value("${mail.fromMail.addr}")
    private String from;

    /**
     * 发送简单邮件
     * @param to 发送地址
     * @param subject 邮件主题
     * @param content 邮件内容
     */
    @Override
    public void sendSimpleMail(String to, String subject, String content) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);

        try {
            mailSender.send(message);
            log.info("邮件发送成功！");
        } catch (Exception e) {
            log.error("邮件发送失败！");
            log.error("失败原因：{}", e);
        }
    }

    @Override
    public void sendHtmlMail(String to, String subject, String content) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);  // html 格式的内容

            mailSender.send(message);
            log.info("邮件发送成功！");
        } catch (MessagingException e) {
            log.error("邮件发送失败！");
            log.error("失败原因：{}", e);
        }
    }

    @Override
    public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId){
        MimeMessage message = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource res = new FileSystemResource(new File(rscPath));
            helper.addInline(rscId, res);

            mailSender.send(message);
            log.info("邮件发送成功！");
        } catch (MessagingException e) {
            log.error("邮件发送失败！");
            log.error("失败原因：{}", e);
        }
    }
}

```

## 邮件模板

在 resources 中建立 templates 文件夹，建立 emailTemplate.html 文件。

模板内容如下：

```html
<!DOCTYPE html>
<html lang="zh" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8"/>
    <title>邮件模板</title>
</head>
<body>
    您好,这是验证邮件,请点击下面的链接完成验证,<br/>
    <a href="#" th:href="@{ http://blog.csdn.net/jeikerxiao/article/details/{id}(id=${id}) }">激活账号</a>
</body>
</html>
```


## 测试类

```java
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
```
