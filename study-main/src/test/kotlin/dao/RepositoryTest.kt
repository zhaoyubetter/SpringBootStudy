package dao

import org.joda.money.CurrencyUnit
import org.joda.money.Money
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import vip.sonar.springboot.study.StudyApplication
import vip.sonar.springboot.study.domain.Coffee
import vip.sonar.springboot.study.repository.CoffeeMapper

/**
 * @description: Repository
 * @author better
 * @date 2019-03-10 21:08
 */
@RunWith(SpringJUnit4ClassRunner::class)  // SpringJUnit支持，由此引入Spring-Test框架支持！
@SpringBootTest(classes = [StudyApplication::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RepositoryTest {

    @Autowired
    lateinit var mapper: CoffeeMapper
    val log: Logger = LoggerFactory.getLogger(RepositoryTest@ this.javaClass)

    @Test
    fun test1() {
        val save = Coffee(
                name = "espresso",
                price = Money.of(CurrencyUnit.of("CNY"), 20.50)
        )
        mapper.save(save)

        log.info("coffee is id: ${save.id}, name: ${save.name}")



    }

    @Test
    fun test2() {
        val find = mapper.findById(3)
        log.info("coffee : $find, ${find?.updateTime}")
    }


}