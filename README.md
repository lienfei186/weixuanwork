## 项目结构
下面是整个项目结构，主要类已做注释
```$xslt
-----------用到的配置-------------------------------------------------------------------------------------
数据库文件：weixuantest.sql
用到了redis 配置在 application.yml 和 com.cn.weixuan.config.ShiroConfig类的属性配置
登录安全、授权、缓存配置类 com.cn.weixuan.config.ShiroConfig

-----------配置文件-------------------------------------------------------------------------------------
修改了 application.yml 日志配置 
增加了 log4j2-spring.xml 日志配置

-----------拦截器和过滤器配置------------------------------------------------------------------------------------
自定义--拦截器 com.cn.weixuan.web.interceptor
自定义--过滤器 com.cn.weixuan.web.filter
拦截器和过滤器配置 
    拦截器 com.cn.weixuan.config.InterceptorConfig
    过滤器 com.cn.weixuan.config.FilterConfig

-----------登录和权限------------------------------------------------------------------------------------
登录 com.cn.weixuan.controller.LoginController 
    登录成功 role字段是权限
注册 com.cn.weixuan.controller.UserController
登录实现类 com.cn.weixuan.service.impl.UserServiceImpl
后面可用权限控制：com.cn.weixuan.config.MyShiroRealm.doGetAuthorizationInfo方法 
    后面可用注解控制权限，或者登录查询出多个权限

-----------数据源配置文件夹------------------------------------------------------------------------------------
mapper

-----------示例------------------------------------------------------------------------------------
登录 
    http://localhost:8081/login?userName=admin&password=123456
注册
    http://localhost:8081/user/regist?name=test&userName=test&password=123456

--------个人中心-------------------------------------------------------------------------------------
个人中心 com.cn.weixuan.controller.UserController
-------示例------------------------------------------------------
查询个人信息
    http://localhost:8081/user/userInfo？phone=15555555555
添加修改头像
    http://localhost:8081/user/profile？profile=1111.jpg