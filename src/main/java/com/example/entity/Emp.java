package com.example.entity;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Emp {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private Integer gender;
    private String phone;
    private Integer position;
    private Integer salary;
    private String image;
    private LocalDate hireDate;
    private Integer deptId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    
    // 关联字段
    private String deptName;
    private List<EmpExpr> exprList;
}