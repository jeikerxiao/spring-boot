# spring-boot-restdoc

用Spring官方推荐的restdoc去生成api文档。

创建一个简单的springboot工程，将http接口通过Api文档暴露出来。

只需要通过 JUnit单元测试和spring的MockMVC就可以生成文档。

## pom.xml依赖

```xml
<dependencies>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-web</artifactId>
	</dependency>

	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-test</artifactId>
		<scope>test</scope>
	</dependency>
	<dependency>
		<groupId>org.springframework.restdocs</groupId>
		<artifactId>spring-restdocs-mockmvc</artifactId>
		<scope>test</scope>
	</dependency>
</dependencies>
```

pom.xml插件

```xml
<plugins>

	<plugin>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-maven-plugin</artifactId>
	</plugin>

	<plugin>
		<groupId>org.asciidoctor</groupId>
		<artifactId>asciidoctor-maven-plugin</artifactId>
		<version>1.5.3</version>
		<executions>
			<execution>
				<id>generate-docs</id>
				<phase>prepare-package</phase>
				<goals>
					<goal>process-asciidoc</goal>
				</goals>
				<configuration>
					<backend>html</backend>
					<doctype>book</doctype>
					<!-- 配置模板位置 {snippets} -->
					<attributes>
						<snippets>${project.build.directory}/snippets</snippets>
					</attributes>
				</configuration>
			</execution>
		</executions>
		<dependencies>
			<dependency>
				<groupId>org.springframework.restdocs</groupId>
				<artifactId>spring-restdocs-asciidoctor</artifactId>
				<version>1.2.1.RELEASE</version>
			</dependency>
		</dependencies>
	</plugin>

</plugins>
```

## 单元测试类

```java
// 提供Spring测试上下文
@RunWith(SpringRunner.class)
@WebMvcTest(HomeController.class)
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void hello() throws Exception {
        // 建立web请求
        this.mockMvc.perform(get("/"))
                // 打印结果
                .andDo(print())
                // 检查状态码为200
                .andExpect(status().isOk())
                // 检查内容有"Hello World"
                .andExpect(content().string(containsString("Hello World")))
                // 检查在根目录下有一个名为name的节点，并且该节点对应的值是“Hello World”
                .andExpect(jsonPath("$.message").value("Hello World"))
                // 生成文件目录名称:home
                .andDo(document("home"));
    }
}
```

## 运行单元测试

启动单元测试，测试通过，你会发现在target文件下生成了一个snippets文件夹，其目录结构如下:

```
└── target
    └── snippets
        └── home
            └── httpie-request.adoc
            └── curl-request.adoc
            └── http-request.adoc
            └── http-response.adoc
```

## 使用snippets

创建一个新文件src/main/asciidoc/index.adoc

```
= 用 Spring REST Docs 构建接口文档

API服务器地址 http://localhost:8080:

.请求
include::{snippets}/home/http-request.adoc[]

.响应
include::{snippets}/home/http-response.adoc[]

这个例子非常简单，通过单元测试和一些简单的配置就能够得到api文档了。
```

## 生成API文档

这时只需要通过maven 打包命令就可以生成文档了：


```shell
$ mvn package
```

在/target/generated-docs下有个index.html，打开这个html,显示如下:

![image](../images/restdoc.png)

