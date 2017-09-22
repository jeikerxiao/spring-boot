
# spring-boot-jwt

JWT是 Json Web Token 的缩写。

## JWT的工作流程

1. 用户导航到登录页，输入用户名、密码，进行登录
2. 服务器验证登录鉴权，如果改用户合法，根据用户的信息和服务器的规则生成JWT Token
服务器将该token以json形式返回（不一定要json形式，这里说的是一种常见的做法）
3. 用户得到token，存在localStorage、cookie或其它数据存储形式中。
4. 以后用户请求受保护的API时，在请求的header中加入 Authorization: Bearer xxxx(token)。此处注意token之前有一个7字符长度的 Bearer
5. 服务器端对此token进行检验，如果合法就解析其中内容，根据其拥有的权限和自己的业务逻辑给出对应的响应结果。
6. 用户取得结果

## 运行

### 登录

![image](../images/jwt1.png)


### 获取用户信息

![image](../images/jwt2.png)