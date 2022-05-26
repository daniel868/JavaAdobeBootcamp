package com.example.adobe.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EmployeeDto {
    private String name;
    private String surname;
    private String phoneNumber;
    private String emailAddress;
    private String position;
    private BigDecimal salary;
}
