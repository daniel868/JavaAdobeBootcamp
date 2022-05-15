package com.example.adobe.entity.people;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class Employee extends People {

    private final Role position;
    private final BigDecimal salary;

}
