package com.example.employeeapp.controller;

import com.example.employeeapp.entity.Employee;
import com.example.employeeapp.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
@Tag(name = "Employee Management", description = "APIs for managing employees")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    @Operation(summary = "Get all employees")
    public List<Employee> getAllEmployees() {
        logger.info("Received request to get all employees");
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get employee by ID")
    public Employee getEmployeeById(@PathVariable Long id) {
        logger.info("Received request to get employee by id: {}", id);
        return employeeService.getEmployeeById(id).orElseThrow();
    }

    @PostMapping
    @Operation(summary = "Create a new employee")
    public Employee createEmployee(@RequestBody Employee employee) {
        logger.info("Received request to create employee: {}", employee.getName());
        return employeeService.createEmployee(employee);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an employee")
    public Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        logger.info("Received request to update employee with id: {}", id);
        return employeeService.updateEmployee(id, employee);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an employee")
    public void deleteEmployee(@PathVariable Long id) {
        logger.info("Received request to delete employee with id: {}", id);
        employeeService.deleteEmployee(id);
    }
}