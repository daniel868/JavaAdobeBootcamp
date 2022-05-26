package com.example.adobe.entity.people;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
public class Employee extends People {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "employee_key_sequence_generator")
    @SequenceGenerator(name = "employee_key_sequence_generator", sequenceName = "employee_sequence", allocationSize = 1)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Role position;
    private BigDecimal salary;

}
