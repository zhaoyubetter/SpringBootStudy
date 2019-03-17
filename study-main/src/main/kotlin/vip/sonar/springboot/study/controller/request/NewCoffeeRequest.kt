package vip.sonar.springboot.study.controller.request

import org.joda.money.Money
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

/**
 * @description: VO
 * @author better
 * @date 2019-03-17 10:36
 */
data class NewCoffeeRequest(
        @NotEmpty
        var name: String,
        @NotNull
        var price: Money
)