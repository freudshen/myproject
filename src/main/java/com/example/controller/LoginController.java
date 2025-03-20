package com.example.controller;

import com.example.common.Result;
import com.example.entity.Emp;
import com.example.entity.LoginRequest;
import com.example.mapper.EmpMapper;
import com.example.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private EmpMapper empMapper;

    @PostMapping("/login")
    public Result login(@RequestBody LoginRequest loginRequest) {
        Emp emp = empMapper.login(loginRequest);
        if (emp == null) {
            return Result.error("用户名或密码错误");
        }

        String token = JwtUtils.generateToken(emp.getId(), emp.getUsername());
        
        Map<String, Object> data = new HashMap<>();
        data.put("id", emp.getId());
        data.put("username", emp.getUsername());
        data.put("name", emp.getName());
        data.put("token", token);
        
        return Result.success(data);
    }
}