package com.example.adobe.entity.aircraft;

import lombok.*;

import javax.persistence.*;



@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Entity
@DiscriminatorColumn(name = "aircraft_type",discriminatorType = DiscriminatorType.INTEGER)
@Data
public abstract class Aircraft {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "aircraft_key_sequence_generator")
    @SequenceGenerator(name = "aircraft_key_sequence_generator", sequenceName = "aircraft_sequence", allocationSize = 1)
    private Long id;

    protected String aircraftName;
    protected int enginesNumber;
    protected float maxSpeed;

    public Aircraft(String aircraftName, int enginesNumber, float maxSpeed) {
        this.aircraftName = aircraftName;
        this.enginesNumber = enginesNumber;
        this.maxSpeed = maxSpeed;
    }
}
