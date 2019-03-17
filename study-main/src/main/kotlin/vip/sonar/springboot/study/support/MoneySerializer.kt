package vip.sonar.springboot.study.support

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import org.joda.money.Money
import org.springframework.boot.jackson.JsonComponent
import java.io.IOException
import org.joda.money.CurrencyUnit
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer


/**
 * @description: Money 类型序列化与反序列化
 * @author better
 * @date 2019-03-17 15:39
 */
@JsonComponent
class MoneySerializer protected constructor()
    : StdSerializer<Money>(Money::class.java) {

    @Throws(IOException::class)
    override fun serialize(money: Money, jsonGenerator: JsonGenerator,
                           serializerProvider: SerializerProvider) {
        jsonGenerator.writeNumber(money.amount)
    }
}

@JsonComponent
class MoneyDeserializer protected constructor() : StdDeserializer<Money>(Money::class.java) {

    @Throws(IOException::class, JsonProcessingException::class)
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Money {
        return Money.of(CurrencyUnit.of("CNY"), p.decimalValue)
    }
}

