package com.cnm.service;

import com.cnm.pojo.Emp;
import com.cnm.pojo.EmpQueryParam;
import com.cnm.pojo.LoginInfo;
import com.cnm.pojo.PageResult;

import java.time.LocalDate;
import java.util.List;

public interface EmpService
{
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    void save(Emp emp);

    void delete(List<Integer> ids);

    Emp getInfo(Integer id);

    void update(Emp emp);

    LoginInfo login(Emp emp);
}
