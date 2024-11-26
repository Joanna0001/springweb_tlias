package com.tlias.service;

import com.tlias.pojo.PageBean;

import java.util.List;

/**
 * 员工管理
 */
public interface EmpService {
    /**
     * 获取员工列表
     */
    PageBean page(Integer page, Integer pageSize);
}
