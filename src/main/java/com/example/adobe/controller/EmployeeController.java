package com.example.adobe.controller;

import com.example.adobe.dto.EmployeeDto;
import com.example.adobe.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/insert-employee")
    public ResponseEntity<String> insertEmployee(@RequestBody EmployeeDto employeeDto) {
        return employeeService.insertEmployee(employeeDto);
    }

    @GetMapping("/get-employee")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        return employeeService.getAllEmployee();
    }

    @GetMapping("/get-employee/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @DeleteMapping("/delete-employee/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") Long employeeId) {
        return employeeService.deleteById(employeeId);
    }

}
