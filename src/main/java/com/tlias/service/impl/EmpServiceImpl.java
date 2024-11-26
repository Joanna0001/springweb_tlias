package com.tlias.service.impl;

import com.tlias.mapper.EmpMapper;
import com.tlias.pojo.Emp;
import com.tlias.pojo.PageBean;
import com.tlias.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Override
    public PageBean page(Integer page, Integer pageSize) {
        Long total = empMapper.count();
        List<Emp> rows = empMapper.page((page - 1) * pageSize, pageSize);

        // 封装 pageBean 对象
        return new PageBean(total, rows);
    }
}
