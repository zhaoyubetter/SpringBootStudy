package dao

import org.joda.money.CurrencyUnit
import org.joda.money.Money
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import vip.sonar.springboot.study.StudyApplication
import vip.sonar.springboot.study.domain.Coffee
import vip.sonar.springboot.study.repository.CoffeeOrderRepository
import vip.sonar.springboot.study.repository.CoffeeRepository
import javax.transaction.Transactional

/**
 * @description: Repository
 * @author better
 * @date 2019-03-10 21:08
 */
@RunWith(SpringJUnit4ClassRunner::class)  // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = [StudyApplication::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class RepositoryTest {

    @Autowired
    lateinit var mapper: CoffeeRepository
    @Autowired
    lateinit var orderMapper:CoffeeOrderRepository

    val log: Logger = LoggerFactory.getLogger(RepositoryTest@ this.javaClass)

    /**
     * 初始化数据，初始化时，Rollback 设置为 false
     */
    @Rollback(true)
    @Test
    fun initTest() {
        mapper.save(Coffee(
                name = "espresso",
                price = Money.of(CurrencyUnit.of("CNY"), 20.00)
        ))

        mapper.save(Coffee(
                name = "latte",
                price = Money.of(CurrencyUnit.of("CNY"), 25.00)
        ))

        mapper.save(Coffee(
                name = "capuccino",
                price = Money.of(CurrencyUnit.of("CNY"), 25.00)
        ))

        mapper.save(Coffee(
                name = "mocha",
                price = Money.of(CurrencyUnit.of("CNY"), 30.00)
        ))

        mapper.save(Coffee(
                name = "macchiato",
                price = Money.of(CurrencyUnit.of("CNY"), 30.00)
        ))
    }

    @Rollback
    @Test
    fun test1() {
        val save = Coffee(
                name = "espresso",
                price = Money.of(CurrencyUnit.of("CNY"), 20.50)
        )
        mapper.save(save)
        log.info("coffee is id: ${save.id}, name: ${save.name}")
    }

    @Rollback
    @Test
    fun test2() {
        val find = mapper.findById(3)
        log.info("coffee : $find, ${find?.updateTime}")
    }

    @Test
    fun testFindOrderById() {
        val order = orderMapper.queryOrderByOrderId(20)


        log.info("Order Info: {}", order.items?.size)
    }
}