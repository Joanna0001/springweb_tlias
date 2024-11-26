package com.tlias.controller;

import com.tlias.pojo.PageBean;
import com.tlias.pojo.Result;
import com.tlias.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 员工管理Controller
 */
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;

    @GetMapping
    public Result list(@RequestParam(required = true, defaultValue = "1") Integer page,
                       @RequestParam(required = true, defaultValue = "10") Integer pageSize) {
        PageBean pageBean = empService.page(page, pageSize);
        return Result.success(pageBean);
    }
}
