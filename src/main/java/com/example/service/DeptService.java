package com.example.service;

import com.example.entity.Dept;
import com.example.mapper.DeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptService {
    @Autowired
    private DeptMapper deptMapper;
    
    public List<Dept> list() {
        return deptMapper.list();
    }
    
    public void deleteById(Integer id) {
        deptMapper.deleteById(id);
    }
    
    public void add(Dept dept) {
        deptMapper.insert(dept);
    }
    
    public Dept getById(Integer id) {
        return deptMapper.getById(id);
    }
    
    public void update(Dept dept) {
        deptMapper.update(dept);
    }
}