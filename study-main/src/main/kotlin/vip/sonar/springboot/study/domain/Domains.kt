package vip.sonar.springboot.study.domain

import org.joda.money.Money

/**
 * @description: 实体
 * @author better
 * @date 2019-03-10 14:47
 */
abstract class Base(
        var id: Long = 0,
        var createTime: Long = System.currentTimeMillis(),
        var updateTime: Long = System.currentTimeMillis()
)

// t_coffee
data class Coffee(
        var name: String = "",
        var price: Money? = null) : Base()

// t_coffee_order 咖啡订单表
// 一个订单可能有多个coffee，多个 coffee，管理一个订单；
open class CoffeeOrder(
        var customer: String = "",
        var state: OrderState = OrderState.INIT,
        // 多对多关系
        var items:List<Coffee>? = mutableListOf()) : Base()

// relation 多对多
class CoffeeOrderRelation(
        var coffeeId: Long = 0,
        var coffeeOrderId: Long = 0) : Base()

// OrderState
enum class OrderState {
    INIT, PAID, BREWING, BREWED, TAKEN, CANCELLED
}





