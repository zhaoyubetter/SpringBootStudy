package vip.sonar.springboot.study.controller.request

/**
 * @description: 请求参数
 * @author better
 * @date 2019-03-16 14:46
 */
data class NewOrderRequest(
        var customer: String? = "",
        var items: List<String>? = null
)