package vip.sonar.mongodb.domain

import org.joda.money.Money
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * @description: 实体
 * @author better
 * @date 2019-03-10 14:47
 */

@Document
data class Coffee(
        @Id
        var id: String? = "",
        var name: String = "",
        var price: Money? = null,
        var createTime: Long = 0,
        var updateTime: Long = 0)






