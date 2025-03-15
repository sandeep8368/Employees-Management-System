package com.project.Emsystem.controller;


import com.project.Emsystem.EmployeeEntity;
import com.project.Emsystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController{
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<EmployeeEntity> getAllEmployees() {
    return employeeService.readEmployees();
    }

    @PostMapping
    public String createEmployees(@RequestBody EmployeeEntity employee) {
    return employeeService.createEmployee(employee);
    }

    @PutMapping("/{id}")
    public String updateEmployees(@PathVariable Long id, @RequestBody EmployeeEntity employee) {
    return employeeService.updateEmployees(id, employee);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        if (employeeService.deleteEmployee(id))
            return "Record deleted...";
        return "Not found...";
    }
}