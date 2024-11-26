package com.tlias.service;

import com.tlias.pojo.PageBean;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
}
