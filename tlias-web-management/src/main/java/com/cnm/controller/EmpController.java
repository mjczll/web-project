package com.cnm.controller;

import com.cnm.anno.Log;
import com.cnm.pojo.Emp;
import com.cnm.pojo.EmpQueryParam;
import com.cnm.pojo.PageResult;
import com.cnm.pojo.Result;
import com.cnm.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("emps")
public class EmpController
{
    @Autowired
    private EmpService empService;

    /**
     * 分页查询
     */
    @GetMapping
    public Result page(EmpQueryParam empQueryParam)
    {
        log.info("分页查询:{}",empQueryParam);
        PageResult<Emp> pageResult= empService.page(empQueryParam);
        return Result.success(pageResult);
    }
    @Log
    @PostMapping
    public Result save(@RequestBody Emp emp)
    {
        log.info("新增员工:{}",emp);
        empService.save(emp);
        return Result.success();
    }
    @Log
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids)
    {
        log.info("删除员工：{}", ids);
        empService.delete(ids);
        return Result.success();
    }
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id)
    {
        log.info("回显员工信息:{}",id);
        Emp emp= empService.getInfo(id);
        return Result.success(emp);
    }
    @Log
    @PutMapping
    public Result update(@RequestBody Emp emp)
    {
        log.info("修改员工：{}",emp);
        empService.update(emp);
        return Result.success();

    }
}
