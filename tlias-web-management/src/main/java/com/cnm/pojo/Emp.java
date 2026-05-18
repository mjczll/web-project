package com.cnm.pojo;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Emp
{
    private Integer id;
    private String username;
    private String password;
    private String name;
    private Integer gender;
    private String phone;
    private Integer job;
    private Integer salary;
    private String image;
    private LocalDate entryDate;
    private Integer deptId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    //封装部门名称
    private String deptName;
    private List<EmpExpr> exprList;
}
