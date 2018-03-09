

# spring-boot-multi-datasource

Spring Boot使用多数据源的例子。

主从数据库

# 环境

* Spring Boot 1.5.7.RELEASE
* IntelliJ IDEA 2017.2

# 运行

注意修改 application.yml 中数据库配置，主从库url 可以是不同数据库，此处演示使用同一个数据库。


# 测试

可运行单元测试 UserControllerTest

```java
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void test1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/test1").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void test2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/test2").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

}
```

或直接访问接口：

* GET http://localhost:8080/user/test1 主库
* GET http://localhost:8080/user/test2 从库

