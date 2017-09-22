

# spring-boot-redis

pom.xml文件中引入redis:

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

application.properties文件中配置redis:

```java
# REDIS (RedisProperties)
# Redis数据库索引（默认为0）
spring.redis.database=0
# Redis服务器地址
spring.redis.host=localhost
# Redis服务器连接端口
spring.redis.port=6379
# Redis服务器连接密码（默认为空）
spring.redis.password=
# 连接池最大连接数（使用负值表示没有限制）
spring.redis.pool.max-active=8
# 连接池最大阻塞等待时间（使用负值表示没有限制）
spring.redis.pool.max-wait=-1
# 连接池中的最大空闲连接
spring.redis.pool.max-idle=8
# 连接池中的最小空闲连接
spring.redis.pool.min-idle=0
# 连接超时时间（毫秒）
spring.redis.timeout=0
```

测试用例中使用

```java

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRedisApplicationTests {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Test
	public void testRedis() throws Exception {
		// 保存字符串
		stringRedisTemplate.opsForValue().set("name", "xiao");
		// 查出字符串
		Assert.assertEquals("xiao", stringRedisTemplate.opsForValue().get("name"));
	}

}
```

本地启动redis:

```shell
redis-server
```

## 运行

运行测试用例。

查看Redis Server里的数据：（这里使用redis界面工具）

![image](images/redis.png)
