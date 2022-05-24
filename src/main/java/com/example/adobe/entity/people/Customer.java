package com.example.adobe.entity.people;

import com.example.adobe.entity.flight.Flight;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
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
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "flight_id")
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
