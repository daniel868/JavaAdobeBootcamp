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
@DiscriminatorValue("2")
@Entity
public class CargoAircraft extends Aircraft {
    private float maxWeight;
    private int totalPackageNumber;

    @Builder
    public CargoAircraft(String aircraftName, int enginesNumber, float maxSpeed, float maxWeight, int totalPackageNumber) {
        super(aircraftName, enginesNumber, maxSpeed);
        this.maxWeight = maxWeight;
        this.totalPackageNumber = totalPackageNumber;
    }
}
