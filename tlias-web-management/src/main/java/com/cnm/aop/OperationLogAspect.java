package com.cnm.aop;

import com.cnm.mapper.OperateLogMapper;
import com.cnm.pojo.OperateLog;
import com.cnm.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class OperationLogAspect
{
    @Autowired
    private OperateLogMapper operateLogMapper;
    @Around("@annotation(com.cnm.anno.Log)")
    public Object logOperation(ProceedingJoinPoint joinPoint) throws Throwable
    {
        long startTime=System.currentTimeMillis();
        //执行目标方法
        Object result=joinPoint.proceed();
        //计算耗时
        long endTime=System.currentTimeMillis();
        long costTime=endTime-startTime;
        //获取方法签名
        MethodSignature signature=(MethodSignature) joinPoint.getSignature();
        Method method=signature.getMethod();

        //构建日志实体
        OperateLog olog=new OperateLog();
        olog.setOperateEmpId(CurrentHolder.getCurrentId());
        olog.setOperateTime(LocalDateTime.now());
        olog.setClassName(joinPoint.getTarget().getClass().getName());
        olog.setMethodName(signature.getName());
        olog.setMethodParams(Arrays.toString(joinPoint.getArgs()));
        olog.setReturnValue(result!=null?result.toString():"void");
        olog.setCostTime(costTime);

        log.info("记录操作日志：{}",log);
        //保存日志
        operateLogMapper.insert(olog);
        return result;
    }
}
