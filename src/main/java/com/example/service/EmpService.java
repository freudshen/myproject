package com.example.service;

import com.example.entity.Emp;
import com.example.entity.EmpQuery;
import com.example.mapper.EmpMapper;
import com.example.mapper.EmpExprMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmpService {
    @Autowired
    private EmpMapper empMapper;
    
    @Autowired
    private EmpExprMapper empExprMapper;
    
    public Page<Emp> list(EmpQuery query) {
        PageHelper.startPage(query.getPage(), query.getPageSize());
        return (Page<Emp>) empMapper.list(query);
    }
    
    @Transactional
    public void deleteByIds(String ids) {
        List<Integer> idList = Arrays.stream(ids.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        idList.forEach(empExprMapper::deleteByEmpId);
        empMapper.deleteByIds(idList);
    }
    
    @Transactional
    public void add(Emp emp) {
        empMapper.insert(emp);
        if (emp.getExprList() != null) {
            emp.getExprList().forEach(expr -> {
                expr.setEmpId(emp.getId());
                empExprMapper.insert(expr);
            });
        }
    }
    
    public Emp getById(Integer id) {
        Emp emp = empMapper.getById(id);
        if (emp != null) {
            emp.setExprList(empExprMapper.getByEmpId(id));
        }
        return emp;
    }
    
    @Transactional
    public void update(Emp emp) {
        empMapper.update(emp);
        // 先删除原有工作经历
        empExprMapper.deleteByEmpId(emp.getId());
        // 添加新的工作经历
        if (emp.getExprList() != null && !emp.getExprList().isEmpty()) {
            emp.getExprList().forEach(expr -> {
                expr.setEmpId(emp.getId());
                empExprMapper.insert(expr);
            });
        }
    }

    public List<Emp> listAll() {
        return empMapper.listAll();
    }
}