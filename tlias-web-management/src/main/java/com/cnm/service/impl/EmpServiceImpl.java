package com.cnm.service.impl;

import com.cnm.mapper.EmpExprMapper;
import com.cnm.mapper.EmpLogMapper;
import com.cnm.mapper.EmpMapper;
import com.cnm.pojo.*;
import com.cnm.service.EmpService;
import com.cnm.utils.JwtUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService
{
    @Autowired
    private  EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogMapper empLogMapper;

    @Override
    public LoginInfo login(Emp emp)
    {
        Emp e=empMapper.loginByUsernameAndPassword(emp);
        if(e!=null)
        {
            log.info("登录成功,用户信息:{}",e);
            Map<String,Object> claims=new HashMap<>();
            claims.put("id",e.getId());
            claims.put("username",e.getUsername());
            String jwt= JwtUtils.generateToken(claims);
            return new LoginInfo(e.getId(),e.getUsername(),e.getName(),jwt);
        }
        return null;
    }

    @Override
    public Emp getInfo(Integer id)
    {
        return empMapper.getInfoById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void update(Emp emp)
    {
        List<EmpExpr> exprList;
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);


            exprList = emp.getExprList();
            if (!CollectionUtils.isEmpty(exprList))
            {
                empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));
                exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
                empExprMapper.insertBatch(exprList);
            }

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<Integer> ids)
    {
        empMapper.deleteByIds(ids);
        empExprMapper.deleteByEmpIds(ids);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(Emp emp)
    {
        try
        {
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);
            List<EmpExpr>exprList=emp.getExprList();
            if(!CollectionUtils.isEmpty(exprList))
            {
                exprList.forEach(empExpr ->
                {
                    empExpr.setEmpId(emp.getId());
                });
                empExprMapper.insertBatch(exprList);
            }
        } finally
        {
            EmpLog empLog=new EmpLog(null,LocalDateTime.now(),"新增员工:"+emp);
            empLogMapper.insertLog(empLog);
        }
    }
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize)
//    {
//        //1.调用mapper接口，查询总记录数
//        Long total =empMapper.count();
//        //2.调用mapper接口，查询结果列表
//        Integer start=(page-1)*pageSize;
//        List<Emp> rows=empMapper.List(start,pageSize);
//        //3.封装结果PageResult
//        return new PageResult<Emp>(total,rows);
//    }

    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam)
    {
        //1.设置分页参数
        PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());
        //2.执行查询
        List<Emp> empList=empMapper.List(empQueryParam);
        //3.解析查询结果，并封装
        Page<Emp> p=(Page<Emp>) empList;
        return new PageResult<Emp>(p.getTotal(),p.getResult());
    }

}
