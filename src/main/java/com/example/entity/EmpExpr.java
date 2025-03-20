package com.example.entity;

import lombok.Data;
import java.time.LocalDate;

@Data
public class EmpExpr {
    private Integer id;
    private Integer empId;
    private String company;
    private String position;
    private LocalDate startDate;
    private LocalDate endDate;
}