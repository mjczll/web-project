package com.cnm.mapper;

import com.cnm.pojo.Emp;
import com.cnm.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Mapper
public interface EmpMapper
{
    /**
     * 查询员工数量
     */
//    @Select("select count(*) from school_department d right join article a on d.id=a.department_id")
//    public Long count();
//
//    @Select("select a.*,d.name deptName from school_department d right join article a on d.id=a.department_id order by d.update_time limit #{start},#{pageSize}")
//    public List<Emp> List(Integer start,Integer pageSize);

    //@Select("select a.*,d.name deptName from school_department d right join article a on d.id=a.department_id order by d.update_time")
    List<Emp> List(EmpQueryParam empQueryParam);

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into emp(username,password,name,gender,phone,job,salary,image,entry_date,dept_id,create_time,update_time) values(#{username},#{password},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    /**
     *根据ID批量删除员工的基本信息
     */
    void deleteByIds(List<Integer> ids);

    Emp getInfoById(Integer id);

    void updateById(Emp emp);

    @MapKey("pos")
    List<Map<String, Object>> countEmpJobData();

    @MapKey("gender")
    List<Map<String, Object>> countEmpGenderData();

    @Select("select id, username ,name from emp where username=#{username} and password=#{password}")
    Emp loginByUsernameAndPassword(Emp emp);
}
