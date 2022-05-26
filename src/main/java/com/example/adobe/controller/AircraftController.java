package com.example.adobe.controller;

import com.example.adobe.dto.AircraftDto;
import com.example.adobe.entity.aircraft.Aircraft;
import com.example.adobe.service.AircraftService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/aircraft")
@CrossOrigin("*")
public class AircraftController {
    private final AircraftService aircraftService;

    public AircraftController(AircraftService aircraftService) {
        this.aircraftService = aircraftService;
    }

    @GetMapping("/commercial-aircraft")
    public List<AircraftDto> getCommercialAircraft() {
        return aircraftService.getCommercialAircraft();
    }

    @GetMapping("/cargo-aircraft")
    public List<AircraftDto> getCargoAircraft() {
        return aircraftService.getCargoAircraft();
    }

    @PostMapping("/insert-aircraft")
    public ResponseEntity<Aircraft> insertNewAircraft(@RequestBody AircraftDto aircraftDto) {
        return aircraftService.insertAircraft(aircraftDto);
    }
}
