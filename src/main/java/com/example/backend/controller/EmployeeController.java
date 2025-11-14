package com.example.backend.controller;

import com.example.backend.model.Employee;
import com.example.backend.service.HrServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private HrServiceImplementation hrService;

    // GET /employees/{email}
    @GetMapping("/{email}")
    public Employee getEmployee(@PathVariable String email) {
        return hrService.getEmployee(email);
    }

    // GET /employees
    @GetMapping
    public List<Employee> getEmployees() {
        return hrService.getEmployees();
    }

    // POST /employees
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return hrService.createEmployee(employee);
    }

    // PUT /employees
    @PutMapping
    public Employee updateEmployee(@RequestBody Employee employee) {
        return hrService.updateEmployee(employee);
    }

    // DELETE /employees/{id}
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        hrService.deleteEmployee(id);
    }
}
