package com.cnm.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OperateLog
{
    private Integer id;//操作日志id
    private Integer operateEmpId;//操作员工id
    private LocalDateTime operateTime;//操作时间
    private String className;//类名
    private String methodName;//方法名
    private String methodParams;//参数
    private String returnValue;//返回值
    private Long costTime;//执行时长
}
