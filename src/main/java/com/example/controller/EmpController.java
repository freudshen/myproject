package com.example.controller;

import com.example.common.Result;
import com.example.entity.Emp;
import com.example.entity.EmpQuery;
import com.example.service.EmpService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;   // 添加这行导入
import java.util.Map;
import java.time.LocalDate;

@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;
    
    @GetMapping
    public Result list(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer gender,
            @RequestParam(required = false) LocalDate begin,
            @RequestParam(required = false) LocalDate end,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer pageSize) {
        
        EmpQuery query = new EmpQuery();
        query.setName(name);
        query.setGender(gender);
        query.setBegin(begin);
        query.setEnd(end);
        query.setPage(page);
        query.setPageSize(pageSize);
        
        Page<Emp> pageResult = empService.list(query);
        Map<String, Object> data = new HashMap<>();
        data.put("total", pageResult.getTotal());
        data.put("rows", pageResult.getResult());
        return Result.success(data);
    }
    
    @DeleteMapping
    public Result delete(@RequestParam String ids) {
        empService.deleteByIds(ids);
        return Result.success();
    }
    
    @PostMapping
    public Result add(@RequestBody Emp emp) {
        empService.add(emp);
        return Result.success();
    }
    
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        return Result.success(empService.getById(id));
    }
    
    @PutMapping
    public Result update(@RequestBody Emp emp) {
        empService.update(emp);
        return Result.success();
    }

    @GetMapping("/list")
    public Result listAll() {
        List<Emp> list = empService.listAll();
        return Result.success(list);
    }
}