package vip.sonar.springboot.study.repository

import org.apache.ibatis.annotations.*
import vip.sonar.springboot.study.domain.Coffee

/**
 * @description: dao 层
 * @author better
 * @date 2019-03-10 20:45
 */
@Mapper
interface CoffeeMapper {

    @Insert("""
        INSERT INTO t_coffee (name, price, create_time, update_time) VALUES
        (#{name}, #{price}, #{createTime}, #{updateTime})
    """)
    @Options(useGeneratedKeys = true)  // 返回生成的key
    fun save(coffee: Coffee): Long

    @Select("""
        SELECT * FROM t_coffee
        WHERE id = #{id}
    """)
    @Results(id = "result", value = [
        Result(column = "id", property = "id"),
        Result(column = "create_time", property = "createTime")
        // 其他省略，会自动映射上，因为：
        // mybatis.configuration.map-underscore-to-camel-case=true
    ]
    )
    fun findById(@Param("id") id: Long) : Coffee?
}