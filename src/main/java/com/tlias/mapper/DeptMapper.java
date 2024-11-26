package com.tlias.mapper;

import com.tlias.pojo.Dept;
import org.apache.ibatis.annotations.*;
import java.util.List;

/**
 * 部门管理
 */
@Mapper
public interface DeptMapper {
    /*
    * 查询全部部门数据
    */
    @Select("select * from dept")
    List<Dept> list();

    /*
    * 根据id 删除某个部门
    */
    @Delete("delete from dept where id = #{id}")
    void del(Integer id);


    /* 新增某个部门 */
    @Insert("insert into dept(name, create_time, update_time) values(#{name}, #{createTime}, #{updateTime})")
    void add(Dept dept);

    /* 修改部门 */
    @Update("update dept set name = #{name}, update_time = #{updateTime} where id = #{id}")
    void edit(Dept dept);

    /* 获取某个部门数据 */
    @Select("select * from dept where id = #{id}")
    Dept get(Integer id);
}
