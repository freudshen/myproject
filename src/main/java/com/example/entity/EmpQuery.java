package com.example.entity;

import lombok.Data;
import java.time.LocalDate;

@Data
public class EmpQuery {
    private String name;
    private Integer gender;
    private LocalDate begin;
    private LocalDate end;
    private Integer page = 1;
    private Integer pageSize = 10;
}