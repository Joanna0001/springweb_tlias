package com.tlias.controller;

import com.tlias.pojo.Emp;
import com.tlias.pojo.PageBean;
import com.tlias.pojo.Result;
import com.tlias.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * 员工管理Controller
 */
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;

    // 分页条件查询
    @GetMapping
    public Result list(@RequestParam(required = true, defaultValue = "1") Integer page,
                       @RequestParam(required = true, defaultValue = "10") Integer pageSize,
                       String name, Short gender, @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        PageBean pageBean = empService.page(page, pageSize, name, gender, begin, end);
        return Result.success(pageBean);
    }

    // 批量删除
    @DeleteMapping("/{ids}")
    public Result del(@PathVariable List<Integer> ids) {
        empService.del(ids);
        return Result.success();
    }

    // 根据id查询员工信息
    @GetMapping("/{id}")
    public Result getInfoById(@PathVariable Integer id) {
        Emp emp = empService.getInfoById(id);
        return Result.success(emp);
    }

    // 新增员工
    @PostMapping
    public Result add(@RequestBody Emp emp) {
        empService.add(emp);
        return Result.success();
    }

    // 修改员工
    @PutMapping
    public Result update(@RequestBody Emp emp) {
        empService.update(emp);
        return Result.success();
    }
}
