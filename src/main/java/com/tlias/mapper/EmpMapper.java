package com.tlias.mapper;

import com.tlias.pojo.Emp;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理
 */
@Mapper
public interface EmpMapper {
    /* 获取员工分页列表数据 */
    List<Emp> list(String name, Short gender, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                   @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end);

    /* 根据id删除员工 */
    void del(List<Integer> ids);

    // 根据id查询员工信息
    @Select("select * from emp where id = #{id}")
    Emp getInfoById(Integer id);

    // 新增员工
    @Insert("insert into emp(username, name, gender, image, job, entrydate, dept_id, create_time, update_time) " +
            "values(#{username}, #{name}, #{gender}, #{image}, #{job}, #{entrydate}, #{deptId}, #{createTime}, #{updateTime})")
    void add(Emp emp);

    // 修改员工
    @Update("update emp set username = #{username}, name = #{name}, gender = #{gender}, image = #{image}, job = #{job}, entrydate = #{entrydate}, " +
            "dept_id = #{deptId}, update_time = #{updateTime} where id = #{id}")
    void update(Emp emp);
}
