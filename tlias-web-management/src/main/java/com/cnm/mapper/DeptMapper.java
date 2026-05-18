package com.cnm.mapper;

import com.cnm.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper
{
    /**
     *  查询所有部门数据
     */
    @Select("select id,name,create_time,update_time from dept order by create_time desc")
    List<Dept> findAll();

    /**
     * 根据id删除部门数据
     * @param id 部门id
     */
    @Delete("delete from dept where id=#{id}")
    void deleteById(Integer id);
    /**
     * 新增部门数据
     * @param dept 部门数据
     */
    @Insert("insert into dept (name,create_time,update_time) values(#{name},#{createTime},#{updateTime})")
    void add(Dept dept);

    /**
     * 根据id查询部门数据
     * @param id 部门id
     */
    @Select("select id,name,create_time,update_time from dept where id=#{id}")
    Dept getById(Integer id);

    /**
     * 修改部门数据
     * @param dept 部门数据
     */
    @Update("update dept set name=#{name},update_time=#{updateTime},create_time=#{createTime} where id =#{id}")
    void update(Dept dept);
}
