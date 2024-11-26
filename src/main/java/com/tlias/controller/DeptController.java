package com.tlias.controller;

import com.tlias.pojo.Dept;
import com.tlias.pojo.Result;
import com.tlias.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门管理Controller
 */
@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {
    @Autowired
    private DeptService deptService;

    @GetMapping
    public Result list() {
        log.info("获取部门列表");
        // 调用service查询部门数据
        List<Dept> deptList = deptService.list();
        return Result.success(deptList);
    }

    /* 获取某个部门数据 */
    @GetMapping("/{id}")
    public Result get(@PathVariable Integer id) {
        Dept dept = deptService.get(id);
        return Result.success(dept);
    }

    @DeleteMapping("/{id}")
    public Result del(@PathVariable Integer id) {
        deptService.del(id);
        return Result.success();
    }

    @PostMapping
    public Result add(@RequestBody Dept dept) {
        deptService.add(dept);
        return Result.success();
    }

    @PutMapping
    public Result edit(@RequestBody Dept dept) {
        deptService.edit(dept);
        return Result.success();
    }
}
