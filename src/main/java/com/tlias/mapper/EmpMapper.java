package com.tlias.mapper;

import com.tlias.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 员工管理
 */
@Mapper
public interface EmpMapper {
    /* 获取员工总数量 */
    @Select("select count(*) from emp")
    public Long count();

    /* 获取员工分页列表数据 */
    @Select("select * from emp limit #{start}, #{pageSize}")
    List<Emp> page(Integer start, Integer pageSize);
}
