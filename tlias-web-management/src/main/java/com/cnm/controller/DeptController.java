package com.cnm.controller;

import com.cnm.anno.Log;
import com.cnm.pojo.Dept;
import com.cnm.pojo.Result;
import com.cnm.service.DeptService;
import com.cnm.service.impl.DeptServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("depts")
@RestController
public class DeptController
{
//    private static final Logger log = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;
    @GetMapping
    public Result list()
    {
        //System.out.println("查询全部部门数据");
        log.info("查询全部部门数据");
        List<Dept> deptList=deptService.findAll();
        return Result.success(deptList);
    }
    @Log
    @DeleteMapping
    public Result delete(Integer id)
    {
        //System.out.println("删除部门数据，id："+id);
        log.info("删除部门数据，id：{}",id);
        deptService.deteById(id);
        return Result.success();
    }
    /**
     * 新增部门数据
     */
    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept)
    {
        //System.out.println("新增部门"+dept);
        log.info( "新增部门{}",dept);
        deptService.add(dept);
        return Result.success();
    }
    /**
     * 根据ID查询部门数据
     */
    @GetMapping(value="/{id}")
    public Result getInfo(@PathVariable Integer id)
    {
        //System.out.println("查询部门id："+id);
        log.info("查询部门id：{}",id);
        Dept dept= deptService.getById(id);
        return Result.success(dept);
    }
    /**
     * 修改部门数据
     */
    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept)
    {
        //System.out.println("修改部门数据："+dept);
        log.info("修改部门数据：{}",dept);
        deptService.update(dept);
        return Result.success();

    }
}
