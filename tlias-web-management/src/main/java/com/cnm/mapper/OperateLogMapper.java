package com.cnm.mapper;

import com.cnm.pojo.OperateLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.slf4j.Logger;

@Mapper
public interface OperateLogMapper
{
    @Insert("insert into operate_log values (#{id},#{operateEmpId},#{operateTime},#{className},#{methodName},#{methodParams},#{returnValue},#{costTime})")
    void insert(OperateLog log);
}
