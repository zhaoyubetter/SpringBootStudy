package vip.sonar.springboot.study.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import vip.sonar.springboot.study.domain.Coffee
import vip.sonar.springboot.study.domain.CoffeeOrder
import vip.sonar.springboot.study.domain.OrderState
import vip.sonar.springboot.study.repository.CoffeeOrderRepository
import vip.sonar.springboot.study.repository.CoffeeRepository

/**
 * @description: 订单服务
 * @author better
 * @date 2019-03-11 21:40
 */
@Service
@Transactional
class CoffeeOrderService {

    @Autowired
    lateinit var coffeeOrderRepository: CoffeeOrderRepository
    @Autowired
    lateinit var coffeRepository: CoffeeRepository

    val log = LoggerFactory.getLogger("CoffeeOrderService")

    fun createOrder(customer: String, vararg coffees: Coffee) {
        // 1. create order
        val order = CoffeeOrder(
                customer = customer,
                state = OrderState.INIT
        )
        // 2. save order & relation
        coffeeOrderRepository.save(order)
        coffeeOrderRepository.saveCoffeeOrderRelation(order, *coffees)

        log.info("New Order: {}", order)
    }

    fun updateOrder(order:CoffeeOrder, state:OrderState) {
        order.state = state
        coffeeOrderRepository.updateOrderState(order)
        log.info("Updated Order: {}", order)
    }
}