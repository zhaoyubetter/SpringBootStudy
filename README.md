# SpringBoot 框架学习

Kotlin + SpringBoot + Gradle

# 1. Mybatis 框架
## 1.1 问题
### 1.1.1 多模块构建 mybatis-generator 不生成代码？

    > 总感觉生成的代码作用不大，是用不到的代码一大堆！

    需配置成
    ```xml
    <!--生成实体类的位置以及包的名字, 注意 targetProject 路径问题，加上项目名，即可-->
    <javaModelGenerator targetPackage="vip.sonar.springboot.study.domain"
                        targetProject="study-main/src/main/java">
        <property name="enableSubPackages" value="true"/>
        <property name="trimStrings" value="true"/>
    </javaModelGenerator>

    ```
