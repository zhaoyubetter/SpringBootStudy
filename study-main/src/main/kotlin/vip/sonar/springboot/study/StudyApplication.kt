package vip.sonar.springboot.study

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.mybatis.generator.api.MyBatisGenerator
import org.mybatis.generator.internal.DefaultShellCallback
import org.mybatis.generator.config.xml.ConfigurationParser
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import vip.sonar.springboot.study.controller.PerformanceInteceptor
import java.util.ArrayList
import java.util.*


// mybatis 扫描注解（值为包路径）
@MapperScan("vip.sonar.springboot.study.repository")
@SpringBootApplication
class StudyApplication : ApplicationRunner, WebMvcConfigurer {

    /**
     * run
     */
    override fun run(args: ApplicationArguments?) {
//        mybatisGenerate()
    }

    // 生成mybatis信息
    private fun mybatisGenerate() {
        val warnings = ArrayList<String>()
        val cp = ConfigurationParser(warnings)
        val config = cp.parseConfiguration(
                this.javaClass.getResourceAsStream("/mybatis_generate.xml"))
        val callback = DefaultShellCallback(true)
        val myBatisGenerator = MyBatisGenerator(config, callback, warnings)
        myBatisGenerator.generate(null)
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(PerformanceInteceptor())
                // 拦截指定url下的调用
                .addPathPatterns("/coffee/**").addPathPatterns("/order/**")
    }
}

fun main(args: Array<String>) {
    runApplication<StudyApplication>(*args)

}



