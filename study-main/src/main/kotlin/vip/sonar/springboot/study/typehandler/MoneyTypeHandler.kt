package vip.sonar.springboot.study.typehandler

import org.apache.ibatis.type.BaseTypeHandler
import org.apache.ibatis.type.JdbcType
import org.joda.money.CurrencyUnit
import org.joda.money.Money
import java.sql.CallableStatement
import java.sql.PreparedStatement
import java.sql.ResultSet

/**
 * @description: 金额 TypeHandler,处理 CNY
 * @author better
 * @date 2019-03-10 20:54
 */
class MoneyTypeHandler : BaseTypeHandler<Money>() {

    override fun setNonNullParameter(ps: PreparedStatement?, i: Int, parameter: Money?, jdbcType: JdbcType?) {
        ps?.let {
            it.setLong(i, parameter?.amountMinorLong ?: 0)
        }
        // 100.00, 返回 10000 一个 Long 类型
    }

    override fun getNullableResult(rs: ResultSet?, columnIndex: Int): Money {
        return parseMoney(rs?.getLong(columnIndex) ?: 0)
    }

    override fun getNullableResult(rs: ResultSet?, columnName: String?): Money {
        return parseMoney(rs?.getLong(columnName) ?: 0)
    }

    override fun getNullableResult(cs: CallableStatement?, columnIndex: Int): Money {
        return parseMoney(cs?.getLong(columnIndex) ?: 0)
    }

    private fun parseMoney(value: Long): Money {
        return Money.of(CurrencyUnit.of("CNY"), value / 100.0)
    }

}