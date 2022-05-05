package domain.employee;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Employee {
    private final String name;
    private final String surname;
    private final String phoneNumber;
    private final String emailAddress;
    private final Role position;
    private final BigDecimal salary;

    @Builder
    public Employee(String name, String surname, String phoneNumber, String emailAddress, Role position, BigDecimal salary) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.position = position;
        this.salary = salary;
    }
}
