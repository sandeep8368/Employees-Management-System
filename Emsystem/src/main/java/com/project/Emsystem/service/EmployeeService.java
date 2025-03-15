package com.project.Emsystem.service;

import com.project.Emsystem.EmployeeEntity;
import com.project.Emsystem.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Marks this class as a Spring Service
public abstract class EmployeeService {

    private final EmployeeRepository employeeRepository;

    // Constructor-based Dependency Injection
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Read all employees
    public List<EmployeeEntity> readEmployees() {
        return employeeRepository.findAll();
    }

    // Create a new employee
    public String createEmployee(EmployeeEntity employee) {
        employeeRepository.save(employee);
        return "Employee created successfully";
    }

    // Update an existing employee
    public String updateEmployee(Long id, EmployeeEntity employee) {
        Optional<EmployeeEntity> existingEmployee = employeeRepository.findById(id);
        if (existingEmployee.isPresent()) {
            EmployeeEntity updatedEmployee = existingEmployee.get();
            updatedEmployee.setName(employee.getName());
            updatedEmployee.setPhone(employee.getPhone());
            updatedEmployee.setEmail(employee.getEmail());
            employeeRepository.save(updatedEmployee);
            return "Employee updated successfully";
        }
        return "Employee not found";
    }

    public abstract String updateEmployees(Long id, EmployeeEntity employee);

    // Delete an employee
    public boolean deleteEmployee(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
