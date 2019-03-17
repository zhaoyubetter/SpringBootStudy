package vip.sonar.springboot.study.support

import org.apache.commons.lang3.StringUtils
import org.apache.commons.lang3.math.NumberUtils
import org.joda.money.Money
import org.springframework.format.Formatter
import org.springframework.stereotype.Component
import java.util.Locale
import org.joda.money.CurrencyUnit
import java.text.ParseException


/**
 * @description: Money 类型Formatter
 * @author better
 * @date 2019-03-17 10:20
 */
@Component
class MoneyFormatter : Formatter<Money> {

    /**
     * 仅测试用
     */
    @Throws(ParseException::class)
    override fun parse(text: String, locale: Locale): Money {
        if (NumberUtils.isParsable(text)) {
            return Money.of(CurrencyUnit.of("CNY"), NumberUtils.createBigDecimal(text))
        } else if (StringUtils.isNotEmpty(text)) {
            val split = StringUtils.split(text, " ")
            return if (split != null && split!!.size == 2 && NumberUtils.isParsable(split!![1])) {
                Money.of(CurrencyUnit.of(split!![0]),
                        NumberUtils.createBigDecimal(split!![1]))
            } else {
                throw ParseException(text, 0)
            }
        }
        throw ParseException(text, 0)
    }

    override fun print(money: Money?, locale: Locale): String? {
        return if (money == null) {
            null
        } else money.currencyUnit.code + " " + money.amount
    }
}
