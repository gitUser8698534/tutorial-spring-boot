package spring.boot.batch.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import spring.boot.batch.entity.TestEntity;

import java.util.List;

// TODO 2. mapper Interface 생성
public interface TestEntityMapper {
    @Select("select * from testTable")
    List<TestEntity> selectAll();

    @Select("<script>select * from testTable where id in (" +
            "<foreach item='item' collection='list' separator=',' >#{item}</foreach> )" +
            "</script>")
    List<TestEntity> selectAllByIdIn(@Param("list")List<Long> testIds);
}
