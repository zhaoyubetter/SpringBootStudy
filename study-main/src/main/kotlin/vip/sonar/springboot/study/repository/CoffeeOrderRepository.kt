package vip.sonar.springboot.study.repository

import org.apache.ibatis.annotations.*
import vip.sonar.springboot.study.domain.Coffee
import vip.sonar.springboot.study.domain.CoffeeOrder

/**
 * @description: order
 * @author better
 * @date 2019-03-11 21:41
 */
@Mapper
interface CoffeeOrderRepository {

    /**
     * 保存订单
     */
    @Insert("""insert into t_coffee_order (create_time, update_time, customer, state) values (
        #{createTime}, #{updateTime},
        #{customer},
        #{state,typeHandler=org.apache.ibatis.type.EnumOrdinalTypeHandler, javaType=vip.sonar.springboot.study.domain.OrderState}
        )""")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    fun save(order: CoffeeOrder): Long  // 要去掉 @Param("order") ，才能返回生成的主键，加上了 @Param 表示只是参数

    @Update("""
        update t_coffee_order set state = #{state,typeHandler=org.apache.ibatis.type.EnumOrdinalTypeHandler, javaType=vip.sonar.springboot.study.domain.OrderState}
        where id = #{id}
    """)
    fun updateOrderState(order: CoffeeOrder)

    /**
     * 保存关系
     */
    fun saveCoffeeOrderRelation(@Param("order") order: CoffeeOrder,
                                @Param("coffees") vararg coffee: Coffee)

    /**
     * 查询订单
     */
    fun queryOrderByOrderId(@Param("id") id: Long):CoffeeOrder
}