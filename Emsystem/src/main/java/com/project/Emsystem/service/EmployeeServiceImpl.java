package com.project.Emsystem.service;

import com.project.Emsystem.EmployeeEntity;
import com.project.Emsystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl extends EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepo;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        super(employeeRepository);
    }

    @Override
    public List<EmployeeEntity> readEmployees() {
        return employeeRepo.findAll();
    }

    @Override
    public String createEmployee(EmployeeEntity employee) {
        employeeRepo.save(employee);
        return "Employee saved successfully!";
    }

    @Override
    public String updateEmployees(Long id, EmployeeEntity employee) {
        EmployeeEntity emp = employeeRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found..."));
        emp.setName(employee.getName());
        emp.setEmail(employee.getEmail());
        emp.setPhone(employee.getPhone());
        employeeRepo.save(emp);
        return "Record updated successfully!";
    }

    @Override
    public boolean deleteEmployee(Long id) {
        if (employeeRepo.existsById(id)) {
            employeeRepo.deleteById(id);
            return true;
        }
        return false;
    }
}