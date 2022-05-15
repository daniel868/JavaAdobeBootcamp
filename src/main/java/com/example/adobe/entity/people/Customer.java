package com.example.adobe.entity.people;

import com.example.adobe.entity.flight.Flight;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Customer extends People {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "customer_key_sequence_generator")
    @SequenceGenerator(name = "customer_key_sequence_generator", sequenceName = "customer_sequence", allocationSize = 1)
    public Long id;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "customer_flight",
            joinColumns = @JoinColumn(name = "flight_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
    private Set<Flight> bookedFlights = new HashSet<>();

    @Builder
    public Customer(String name, String surname, String phoneNumber, String emailAddress) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }
}
