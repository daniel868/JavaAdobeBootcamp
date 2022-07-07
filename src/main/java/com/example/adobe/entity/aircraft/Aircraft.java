package com.example.adobe.entity.aircraft;

import com.example.adobe.entity.flight.Flight;
import lombok.*;

import javax.persistence.*;
import java.util.Set;


@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
@DiscriminatorColumn(name = "aircraft_type", discriminatorType = DiscriminatorType.INTEGER)
@Data
@NamedQueries(value = {
        @NamedQuery(name = "Aircraft.fetchByName", query = "SELECT a From Aircraft a WHERE a.aircraftName=:name"),
        @NamedQuery(name = "Aircraft.countValue", query = "SELECT COUNT(a.maxSpeed) FROM Aircraft  a WHERE a.maxSpeed>100")
})
public abstract class Aircraft {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "aircraft_key_sequence_generator")
    @SequenceGenerator(name = "aircraft_key_sequence_generator", sequenceName = "aircraft_sequence", allocationSize = 1)
    private Long id;
    protected String aircraftName;
    protected int enginesNumber;
    protected float maxSpeed;

    @OneToMany(mappedBy = "aircraft", cascade = CascadeType.ALL)
    private Set<Flight> flights;

    public Aircraft(String aircraftName, int enginesNumber, float maxSpeed) {
        this.aircraftName = aircraftName;
        this.enginesNumber = enginesNumber;
        this.maxSpeed = maxSpeed;
    }
}
