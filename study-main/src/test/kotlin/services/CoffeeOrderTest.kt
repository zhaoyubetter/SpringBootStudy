package services

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import vip.sonar.springboot.study.StudyApplication
import vip.sonar.springboot.study.service.CoffeeOrderService
import vip.sonar.springboot.study.service.CoffeeService

/**
 * @description: 订单测试
 * @author better
 * @date 2019-03-11 22:57
 */
@RunWith(SpringJUnit4ClassRunner::class)  // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = [StudyApplication::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CoffeeOrderTest {

    @Autowired
    lateinit var coffeeService: CoffeeService
    @Autowired
    lateinit var coffeeOrderService: CoffeeOrderService

    @Test
    fun testOrder() {
        val coffee1 = coffeeService.getCoffeeByName("latte")
        val coffee2 = coffeeService.getCoffeeByName("espresso")
        // 创建订单，一个订单对应多个coffee
        coffeeOrderService.createOrder("Better", coffee1!!, coffee2!!)
    }

}