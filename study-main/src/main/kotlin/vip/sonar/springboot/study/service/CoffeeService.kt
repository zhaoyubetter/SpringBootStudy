package vip.sonar.springboot.study.service

import org.joda.money.Money
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import vip.sonar.springboot.study.domain.Coffee
import vip.sonar.springboot.study.repository.CoffeeRepository


/**
 * @description: CoffeeService
 * @author better
 * @date 2019-03-11 21:30
 */
@Service
class CoffeeService {
    @Autowired
    lateinit var coffeeRepository: CoffeeRepository

    val log = LoggerFactory.getLogger(CoffeeService@ this.javaClass)

    fun getCoffeeByName(vararg names: String): List<Coffee> {
        val coffee = coffeeRepository.findCoffeeByName(*names)
        log.info("Coffee Found: {}", coffee)
        return coffee
    }

    fun saveCoffee(name: String, price: Money): Coffee {
        return Coffee(name = name, price = price).apply {
            coffeeRepository.save(this)
        }
    }

    fun getAll(): List<Coffee> = coffeeRepository.queryAll()


}
