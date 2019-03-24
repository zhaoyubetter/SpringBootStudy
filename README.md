# SpringBoot 框架学习

Kotlin + SpringBoot + Gradle

## tag

# 1. Mybatis 框架

## 1.1 插件 pageHelper

参考github

## 1.4 问题

### 1.4.1 多模块构建 mybatis-generator 不生成代码？

需配置成

```xml
<!--生成实体类的位置以及包的名字, 注意 targetProject 路径问题，加上项目名，即可-->
<javaModelGenerator targetPackage="vip.sonar.springboot.study.domain"
targetProject="study-main/src/main/java">
<property name="enableSubPackages" value="true"/>
<property name="trimStrings" value="true"/>
</javaModelGenerator>

```

# 2. MongoDB

**环境准备：**

先通过 docker 下载 `mongo` 镜像

```
docker pull mongo
```

```shell
# 创建数据卷
docker volume create --name mongodata

# 启动容器
docker run --name mongo -p 27017:27017 -v mongodata:/data/db -e MONGO_INITDB_ROOT_USERNAME=admin -e MONGO_INITDB_ROOT_PASSWORD=admin -d mongo

# 登录到mongodb
docker exec -it mongo bash

# 通过shell链接mongoDB
mongo -u admin -p admin
```



`MongoDB`是一款开源的文档型数据库；

## 2.1 初始化MongoDB的库和权限

```shell
# 创建库
use  springbucks;

# 创建用户，用于读写权限
db.createUser(
	{
        user: "springbucks",
        pwd: "springbucks",
        roles: [
            {role : "readWrite", db: "springbucks"}
        ]
	}
)
```



登录到`monogo` DB后，通过`show dbc`可以查看当前所有数据库:

```shell
# 显示当前数据库
> show dbs
admin   0.000GB
config  0.000GB
local   0.000GB

# 创建数据库与用户
use springbucks;

db.createUser(
	{
        user: "springbucks",
        pwd: "springbucks",
        roles: [
            {role : "readWrite", db: "springbucks"}
        ]
	}
)

```



```shell
# 线上用户
> show users;
{
	"_id" : "springbucks.springbucks",
	"user" : "springbucks",
	"db" : "springbucks",
	"roles" : [
		{
			"role" : "readWrite",
			"db" : "springbucks"
		}
	],
	"mechanisms" : [
		"SCRAM-SHA-1",
		"SCRAM-SHA-256"
	]
}
```

## 2.2 Convert 数据类型转换

## 2.3 数据操作

MongoTemplate

Spring Data MongoDB Repository

# 3. Redis

内存KV存储数据库，支持多种数据结构；

# 4.Spring 的缓存抽象



# Thymeleaf

模板引擎，配置相关简单

1. 引入 Thymeleaf 依赖；
2. Springboot 会自动配置，对应的是 `ThymeleafAutoConfiguraion`

## Thymeleaf 常用默认配置

```properties
# 模板缓存，开发阶段使用false
spring.thymeleaf.cache=true
# 校验模板
spring.thymeleaf.check-template=true
# 检查模板位置
spring.thymeleaf.check-template-location=true
spring.thymeleaf.enabled=true
spring.thymeleaf.encoding=UTF-8
spring.thymeleaf.mode=HTML
spring.thymeleaf.servlet.content-type=text/html
# 前缀路径配置
spring.thymeleaf.prefix=classpath:/templates/
# 后缀
spring.thymeleaf.suffix=.html
```



# 静态资源与缓存

不建议在Java中做，而采用`Nginx` 做静态资源服务；

## SpringBoot 中的静态资源配置

核心逻辑：

- WebMvcConfigurer.addResourceHandlers()

常用配置：

```properties
# 默认从根路径开始寻找
spring.mvc.static-path-pattern=/**
# 会从以下目录开始寻找
spring.resources.static-locations=classpath:/META-INF/resources/,classpath:/resources/,classpath:/static/,classpath:/public/
```

## 缓存配置

常用配置(默认单位是秒)

通过类 `ResourceProperties.Cache` 进行设置

```properties
spring.resources.cache.cachecontrol.max-age=
spring.resources.cache.cachecontrol.no-cache=true/false
spring.resources.cache.cachecontrol.s-max-age=

```

> 一般使用`spring`拦截器进行缓存设置



# Spring MVC 异常解析

核心接口：

- HandlerExceptionResolver

常用实现类：

- SimpleMappingExceptionResolver

- DefaultHandlerExceptionResolver

- ResponseStatusExceptionResolver

  ```kotlin
  @ExceptionHandler(MethodArgumentNotValidException::class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)  // 指定响应码
  @ResponseBody
  fun addCoffee2()

  ```

- ExceptionHandlerExceptionResolver

> 异常入口在 `DispatcherServlet`中；



## 异常处理方法

处理方法：

- @ExceptionHander

  在方法上标明此注解，指定该方法用来处理异常，如下代码：

  ```kotlin
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  @ExceptionHandler(
    InvalidParameterException::class,
    MissingServletRequestParameterException::class,
    HttpMessageNotReadableException::class)
  @ResponseBody
  fun handleArgumentException(request: HttpServletRequest, exception: RuntimeException,
                                  locale: Locale): ResponseModel<*> {
          return ResponseModel.fail(
            MessageCodeEnum.CODE_BAD_REQUEST.code, exception.message ?: "")
  }

  ```



添加位置：

- @Controller / @RestController

  在Controller类上方法添加

- @ControllerAdvice / @RestControllerAdvice

  在类似AOP形式拦截器上的类添加，会对所有`Controller`类进行拦截；

# Spring MVC 切入点 - 拦截器

核心接口拦截器：

- HandlerInteceptor

  - boolean preHandle()

    预处理，false 终止，true继续；

  - void postHandle()

  - void afterHandler()

针对@ResponseBody 和 ResponseEntity 的情况

- ResponseBodyAdvice

针对异步请求的接口：

- AsyncHandlerInterceptor

> 入口 `DispatcherServlet` 调用



## 配置拦截器

常规方法：

- WebMvcConfigurer.addInterceptors()

Spring Boot 中的配置：

- 创建带有`@Configuration`注解的`WebMvcConfigurer`的配置类
- 不能带`@EnableWebMvc`注解（想自己彻底控制MVC配置除外）；



我们在Application类实现 `WebMvcConfigurer`接口，并添加拦截：

```kotlin
@SpringBootApplication
class StudyApplication : WebMvcConfigurer {
  override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(PerformanceInteceptor())
                // 拦截指定url下的调用
               .addPathPatterns("/coffee/**").addPathPatterns("/order/**")
    }
}

```

