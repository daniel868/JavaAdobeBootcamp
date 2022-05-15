package com.example.adobe.entity.flight;

import com.example.adobe.entity.people.Customer;
import com.example.adobe.entity.people.People;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "flight_key_sequence_generator")
    @SequenceGenerator(name = "flight_key_sequence_generator", sequenceName = "flight_sequence", allocationSize = 1)
    private Long id;

    private String flightType;
    private Date departureDateTime;
    private Date landingDateTime;
    private String fromLocation;
    private String toLocation;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "customer_flight",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "flight_id")
    )
    private Set<Customer> customerList = new HashSet<>();

    @Builder
    public Flight(String flightType, Date departureDateTime, Date landingDateTime, String fromLocation, String toLocation) {
        this.flightType = flightType;
        this.departureDateTime = departureDateTime;
        this.landingDateTime = landingDateTime;
        this.fromLocation = fromLocation;
        this.toLocation = toLocation;
    }
}
