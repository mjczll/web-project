package com.cnm.mapper;

import com.cnm.pojo.EmpLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Mapper
public interface EmpLogMapper
{
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Insert("insert into emplog(id, create_time, log) values (#{id},#{createTime},#{info})")
    void insertLog(EmpLog empLog);
    
}
