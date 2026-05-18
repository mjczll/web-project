package com.cnm.controller;

import com.cnm.mapper.EmpMapper;
import com.cnm.pojo.Emp;
import com.cnm.pojo.LoginInfo;
import com.cnm.pojo.Result;
import com.cnm.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping
public class LoginController
{
    @Autowired
    private EmpService empService;

    @PostMapping("login")
    public Result login(@RequestBody Emp emp)
    {
        log.info("登陆中，用户:{}",emp);
        LoginInfo info=empService.login(emp);
        if(info!= null)
        {
            return Result.success(info);
        }
        return Result.error("用户名或密码错误");
    }
}
