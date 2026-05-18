package com.cnm.service;

import com.cnm.pojo.GenderOption;
import com.cnm.pojo.JobOption;

public interface ReportService
{
    JobOption getEmpJobData();

    GenderOption getEmpGenderData();
}
