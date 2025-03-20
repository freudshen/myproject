package com.example.mapper;

import com.example.entity.Emp;
import com.example.entity.EmpQuery;
import com.example.entity.LoginRequest;  // 添加这行导入
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface EmpMapper {
    List<Emp> list(EmpQuery query);
    
    void deleteByIds(@Param("ids") List<Integer> ids);
    
    @Insert("insert into emp(username,name,gender,phone,position,salary,image,hire_date,dept_id) " +
            "values(#{username},#{name},#{gender},#{phone},#{position},#{salary},#{image},#{hireDate},#{deptId})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Emp emp);
    
    @Select("select e.*, d.name as dept_name from emp e left join dept d on e.dept_id = d.id where e.id = #{id}")
    Emp getById(Integer id);
    
    @Update("update emp set username=#{username},name=#{name},gender=#{gender},phone=#{phone}," +
            "position=#{position},salary=#{salary},image=#{image},hire_date=#{hireDate},dept_id=#{deptId} where id=#{id}")
    void update(Emp emp);
    
    @Select("select * from emp")
    List<Emp> listAll();

    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp login(LoginRequest loginRequest);
}