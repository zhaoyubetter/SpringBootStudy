package vip.sonar.springboot.study.service

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

    fun getCoffeeByName(name: String): Coffee? {
        val coffee = coffeeRepository.findCoffeeByName(name)
        log.info("Coffee Found: {}", coffee)
        return coffee
    }
}
