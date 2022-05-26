package com.example.adobe.service;


import com.example.adobe.dto.EmployeeDto;
import com.example.adobe.entity.people.Employee;
import com.example.adobe.repository.EmployeeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceTest {

    @Mock
    EmployeeRepository employeeRepository;

    private ModelMapper modelMapper;
    private EmployeeService underTest;
    private EmployeeDto mockEmployeeDto;

    @Before
    public void setUp() {
        modelMapper = new ModelMapper();
        underTest = new EmployeeService(employeeRepository, modelMapper);

        mockEmployeeDto = EmployeeDto.builder()
                .name("EmployeeName1")
                .surname("EmployeeSurname")
                .phoneNumber("0931321321")
                .emailAddress("employee@email.com")
                .position("CEO")
                .salary(BigDecimal.valueOf(12000))
                .build();
    }

    @Test
    public void test_insertEmployee_success() {
        //given

        Employee mockEmployee = modelMapper.map(mockEmployeeDto, Employee.class);
        mockEmployee.setId(1L);

        Mockito.when(employeeRepository.save(any())).thenReturn(mockEmployee);

        //when
        ResponseEntity<String> response = underTest.insertEmployee(mockEmployeeDto);

        //then
        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        verify(employeeRepository, times(1)).save(any());
        verifyNoMoreInteractions(employeeRepository);
    }

    @Test
    public void test_getAllEmployees_success() {
        //given
        Employee mockEmployee1 = modelMapper.map(mockEmployeeDto, Employee.class);
        mockEmployee1.setId(1L);

        Employee mockEmployee2 = modelMapper.map(mockEmployeeDto, Employee.class);
        mockEmployee1.setId(2L);

        List<Employee> mockEmployeeList = asList(mockEmployee1, mockEmployee2);

        Mockito.when(employeeRepository.findAll()).thenReturn(mockEmployeeList);

        //when
        ResponseEntity<List<EmployeeDto>> expected = underTest.getAllEmployee();

        //then

        assertThat(expected).isNotNull();
        assertThat(expected.getBody().size()).isEqualTo(mockEmployeeList.size());

        verify(employeeRepository, times(1)).findAll();
        verifyNoMoreInteractions(employeeRepository);

    }

    @Test
    public void test_findEmployeeById_success() {
        //given
        Employee mockEmployee = modelMapper.map(mockEmployeeDto, Employee.class);

        Mockito.when(employeeRepository.findById(any())).
                thenReturn(Optional.ofNullable(mockEmployee));

        //when
        ResponseEntity<EmployeeDto> expected = underTest.getEmployeeById(1L);

        //then
        assertThat(expected).isNotNull();
        assertThat(expected.getBody().getName()).isEqualTo(mockEmployee.getName());

        verify(employeeRepository,times(1)).findById(any());
        verifyNoMoreInteractions(employeeRepository);
    }
}
