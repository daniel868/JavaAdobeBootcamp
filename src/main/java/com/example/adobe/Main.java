package com.example.adobe;

import com.example.adobe.domain.aircraft.Aircraft;
import com.example.adobe.servicies.AircraftService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class,args);

        AircraftService aircraftService = AircraftService.getInstance();
        List<Aircraft> boeing747Aircraft = aircraftService.getBoeing747Aircraft();
        List<Aircraft> commercialsAircraft = aircraftService.getCommercialsAircraft();
        List<Aircraft> cargoAircraft = aircraftService.getCargoAircraft();

        System.out.println(aircraftService.getLongUsedAircraft());
    }
}
