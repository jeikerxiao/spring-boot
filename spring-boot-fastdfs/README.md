# spring-boot-fastdfs

使用 Spring Boot 整合FastDFS进行文件上传，下载。

## 环境

先得需要安装搭建好FastDFS环境。

* Spring Boot 2.0.1.RELEASE
* IntelliJ IDEA 2017.3
* fastdfs-client-java 1.27-SNAPSHOT (需要自己构建工程构建)


## 依赖

使用 thymeleaf 模板展示界面，使用界面上传文件演示。

使用 fastdfs-client-java 客户端依赖API。

pom.xml

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>

<dependency>
	<groupId>org.csource</groupId>
	<artifactId>fastdfs-client-java</artifactId>
	<version>1.27-SNAPSHOT</version>
</dependency>
```

## 配置

application.properties 

设置文件大小限制。

Spring Boot 2.0以后使用与之前有所不同：

Spring Boot 2.0以前：

```java
spring.http.multipart.maxFileSize=10Mb
spring.http.multipart.maxRequestSize=10Mb
```

Spring Boot 2.0以后：

```java
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB
```

测试用例中使用

```java
@Controller
public class UploadController {

    private static Logger logger = LoggerFactory.getLogger(UploadController.class);

    @Autowired
    private FileService fileService;

    /**
     * 上传界面
     * @return
     */
    @GetMapping("/")
    public String index() {
        return "upload";
    }

    /**
     * 上传后状态界面
     * @return
     */
    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

    /**
     * 上传请求接口
     * @param file
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:uploadStatus";
        }
        try {
            String path = fileService.saveFile(file);
            redirectAttributes.addFlashAttribute("message", "You successfully uploaded '"
                    + file.getOriginalFilename() + "'");
            redirectAttributes.addFlashAttribute("path", "file path url '" + path + "'");
        } catch (Exception e) {
            logger.error("upload file failed:{}, {}", e.getMessage(), e);
        }
        return "redirect:/uploadStatus";
    }

}

```


## 运行

浏览器访问：

http://localhost:8080/

可在这个界面进行上传

上传成功后会跳转入状态界面，显示上传成功后的资源地址，可直接使用地址访问资源。


