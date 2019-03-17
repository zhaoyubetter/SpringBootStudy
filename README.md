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

