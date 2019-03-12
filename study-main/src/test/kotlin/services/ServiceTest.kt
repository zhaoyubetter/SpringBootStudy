package services

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import vip.sonar.springboot.study.StudyApplication
import vip.sonar.springboot.study.service.CoffeeService

/**
 * @description: Services 层测试
 * @author better
 * @date 2019-03-11 21:36
 */
@RunWith(SpringJUnit4ClassRunner::class)  // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = [StudyApplication::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ServiceTest {


    @Autowired
    lateinit var coffeeService: CoffeeService

    @Test
    fun testFindByName() {
        coffeeService.getCoffeeByName("espresso")
    }
}