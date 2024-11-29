package com.tlias.service;

import com.tlias.pojo.Emp;
import com.tlias.pojo.PageBean;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理
 */
public interface EmpService {
    /**
     * 获取员工列表
     */
    PageBean page(Integer page, Integer pageSize, String name, Short gender, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                  @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end);

    void del(List<Integer> ids);

    Emp getInfoById(Integer id);

    void add(Emp emp);

    void update(Emp emp);

    // 用户登录
    Emp login(Emp emp);
}
