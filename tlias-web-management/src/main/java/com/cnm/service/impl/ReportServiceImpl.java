package com.cnm.service.impl;

import com.cnm.mapper.EmpMapper;
import com.cnm.pojo.GenderOption;
import com.cnm.pojo.JobOption;
import com.cnm.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService
{
    @Autowired
    private EmpMapper empMapper;

    @Override
    public GenderOption getEmpGenderData()
    {
        List<Map<String,Object>> list=empMapper.countEmpGenderData();
        List<Object> genderList= list.stream().map(dataMap->dataMap.get("gender")).toList();
        List<Object> dataList= list.stream().map(dataMap->dataMap.get("num")).toList();
        return new GenderOption(genderList,dataList);
    }

    @Override
    public JobOption getEmpJobData()
    {
        List<Map<String,Object>> list=empMapper.countEmpJobData();
        List<Object> jobList =list.stream().map(dataMap->dataMap.get("pos")).toList();
        List<Object> dataList =list.stream().map(dataMap->dataMap.get("num")).toList();
        return new JobOption(jobList,dataList);
    }
}
