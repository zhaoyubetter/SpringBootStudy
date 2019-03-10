package vip.sonar.springboot.study.repository;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.session.RowBounds;

public interface Coffee2Mapper {
    long countByExample(Coffee2Example example);

    int deleteByExample(Coffee2Example example);

    @Delete({
        "delete from t_coffee",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into t_coffee (create_time, update_time, ",
        "name, price)",
        "values (#{createTime,jdbcType=INTEGER}, #{updateTime,jdbcType=INTEGER}, ",
        "#{name,jdbcType=VARCHAR}, #{price,jdbcType=BIGINT,typeHandler=vip.sonar.springboot.study.typehandler.MoneyTypeHandler})"
    })
    @SelectKey(statement="CALL IDENTITY()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(Coffee2 record);

    int insertSelective(Coffee2 record);

    List<Coffee2> selectByExampleWithRowbounds(Coffee2Example example, RowBounds rowBounds);

    List<Coffee2> selectByExample(Coffee2Example example);

    @Select({
        "select",
        "id, create_time, update_time, name, price",
        "from t_coffee",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ResultMap("vip.sonar.springboot.study.repository.Coffee2Mapper.BaseResultMap")
    Coffee2 selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Coffee2 record, @Param("example") Coffee2Example example);

    int updateByExample(@Param("record") Coffee2 record, @Param("example") Coffee2Example example);

    int updateByPrimaryKeySelective(Coffee2 record);

    @Update({
        "update t_coffee",
        "set create_time = #{createTime,jdbcType=INTEGER},",
          "update_time = #{updateTime,jdbcType=INTEGER},",
          "name = #{name,jdbcType=VARCHAR},",
          "price = #{price,jdbcType=BIGINT,typeHandler=vip.sonar.springboot.study.typehandler.MoneyTypeHandler}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Coffee2 record);
}