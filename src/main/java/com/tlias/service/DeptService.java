package com.tlias.service;

import com.tlias.pojo.Dept;

import java.util.List;

/**
 * 部门管理
 */
public interface DeptService {
    /*
    * 查询全部部门数据
    */
    List<Dept> list();

    /* 根据id删除某个部门 */
    void del(Integer id);

    /* 新增某个部门 */
    void add(Dept dept);

    /* 修改部门 */
    void edit(Dept dept);

    /* 获取某个部门数据 */
    Dept get(Integer id);
}
