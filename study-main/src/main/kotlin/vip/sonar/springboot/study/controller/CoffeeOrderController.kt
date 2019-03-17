package vip.sonar.springboot.study.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import vip.sonar.springboot.study.domain.CoffeeOrder
import org.springframework.http.HttpStatus
import vip.sonar.springboot.study.service.CoffeeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import vip.sonar.springboot.study.controller.request.NewOrderRequest
import vip.sonar.springboot.study.domain.Coffee
import vip.sonar.springboot.study.service.CoffeeOrderService


/**
 * @description: Order
 * @author better
 * @date 2019-03-16 14:42
 */
@RestController
@RequestMapping("/order")
class CoffeeOrderController {

    @Autowired
    lateinit var orderService: CoffeeOrderService
    @Autowired
    lateinit var coffeeService: CoffeeService

    val log: Logger = LoggerFactory.getLogger(RepositoryTest@ this.javaClass)

    @PostMapping("/",
            consumes = [MediaType.APPLICATION_JSON_VALUE],
            produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody newOrder: NewOrderRequest): CoffeeOrder {
        log.info("Receive new Order {}", newOrder)
        val coffeeList = coffeeService.getCoffeeByName(*newOrder.items?.toTypedArray()!!)
        return orderService.createOrder(newOrder.customer ?: "", *coffeeList.toTypedArray())
    }
}