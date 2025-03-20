package com.example.mapper;

import com.example.entity.EmpExpr;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface EmpExprMapper {
    @Insert("insert into emp_expr(emp_id,company,position,start_date,end_date) " +
            "values(#{empId},#{company},#{position},#{startDate},#{endDate})")
    void insert(EmpExpr expr);
    
    @Select("select * from emp_expr where emp_id = #{empId}")
    List<EmpExpr> getByEmpId(Integer empId);
    
    @Delete("delete from emp_expr where emp_id = #{empId}")
    void deleteByEmpId(Integer empId);
}