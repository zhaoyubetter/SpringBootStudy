package vip.sonar.springboot.study

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.mybatis.generator.api.MyBatisGenerator
import org.mybatis.generator.internal.DefaultShellCallback
import org.mybatis.generator.config.xml.ConfigurationParser
import java.util.ArrayList


// mybatis 扫描注解（值为包路径）
@MapperScan("vip.sonar.springboot.study.repository")
@SpringBootApplication
class StudyApplication : ApplicationRunner {

    /**
     * run
     */
    override fun run(args: ApplicationArguments?) {
        mybatisGenerate()
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
}

fun main(args: Array<String>) {
    runApplication<StudyApplication>(*args)

}



