package vip.sonar.springboot.study

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

// mybatis 扫描注解（值为包路径）
@MapperScan("vip.sonar.springboot.study.repository")
@SpringBootApplication
class StudyApplication

fun main(args: Array<String>) {
	runApplication<StudyApplication>(*args)
}
