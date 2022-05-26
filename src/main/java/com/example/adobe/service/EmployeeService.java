package com.example.adobe.service;

import com.example.adobe.dto.EmployeeDto;
import com.example.adobe.entity.people.Employee;
import com.example.adobe.exception.EmployeeNotFoundException;
import com.example.adobe.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;


    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public ResponseEntity<String> insertEmployee(EmployeeDto employeeDto) {
        Employee newEmployee = modelMapper.map(employeeDto, Employee.class);

        Long id = employeeRepository.save(newEmployee).getId();

        return ResponseEntity.status(HttpStatus.OK)
                .body("Inserted with id: " + id);
    }

    public ResponseEntity<List<EmployeeDto>> getAllEmployee() {
        return ResponseEntity.ok(employeeRepository.findAll()
                .stream()
                .map(employee -> modelMapper.map(employee, EmployeeDto.class))
                .collect(Collectors.toList()));
    }

    public ResponseEntity<EmployeeDto> getEmployeeById(Long id) {
        return ResponseEntity.ok(employeeRepository.findById(id)
                .map(employee -> modelMapper.map(employee, EmployeeDto.class))
                .orElseThrow(() -> new EmployeeNotFoundException("Could not found employee with id: " + id)));

    }

    public ResponseEntity<String> deleteById(Long id) {
        employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Could not found employee with id: " + id));
        employeeRepository.deleteById(id);

        return ResponseEntity.ok("Deleted with id: " + id);
    }
}
