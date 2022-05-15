package com.example.adobe.entity.aircraft;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("1")
@Entity
public class CommercialAircraft extends Aircraft {

    private Double totalFlightHours;
    private int passengerSeats;

    @Builder
    public CommercialAircraft(String aircraftName, int enginesNumber, float maxSpeed, Double totalFlightHours, int passengerSeats) {
        super(aircraftName, enginesNumber, maxSpeed);
        this.totalFlightHours = totalFlightHours;
        this.passengerSeats = passengerSeats;
    }
}
