package com.tlias.service.impl;

import com.tlias.mapper.DeptMapper;
import com.tlias.mapper.EmpMapper;
import com.tlias.pojo.Dept;
import com.tlias.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    /**
     * spring 事务处理，默认只有运行异常RuntimeException，才会回滚
     * rollbackFor 用于控制出现何种异常类型，回滚事务
     * propagation 当一个事务方法被另一个事务方法调用时，这个事务方法应该如何进行事务控制
     * REQUIRED [默认值] 需要事务，有则加入，无则创建新事务
     * REQUIRED_NEW 需要新事务，无论有无，总是创建新事务
     * SUPPORTS 支持事务，有则加入，无则在无事务状态中运行
     * NOT_SUPPORTS 不支持事务，在无事务状态下运行，如果当前存在已有事务，则挂起当前事务
     * MANDATORY 必须有事务，否则抛异常
     * NEVER 必须没事务，否则抛异常
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void del(Integer id) {
        deptMapper.del(id);
        empMapper.delByDeptId(id);
    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.add(dept);
    }

    @Override
    public void edit(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.edit(dept);
    }

    @Override
    public Dept get(Integer id) {
        return deptMapper.get(id);
    }
}
