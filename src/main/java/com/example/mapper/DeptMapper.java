package com.example.mapper;

import com.example.entity.Dept;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface DeptMapper {
    @Select("select * from dept")
    List<Dept> list();
    
    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);
    
    @Insert("insert into dept(name) values(#{name})")
    void insert(Dept dept);
    
    @Select("select * from dept where id = #{id}")
    Dept getById(Integer id);
    
    @Update("update dept set name = #{name} where id = #{id}")
    void update(Dept dept);
}