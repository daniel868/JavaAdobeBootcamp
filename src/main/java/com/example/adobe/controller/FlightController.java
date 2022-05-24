package com.example.adobe.controller;

import com.example.adobe.dto.FlightDto;
import com.example.adobe.service.FlightService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/flight")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping("/insert-flight")
    public void insertFlight(@RequestBody FlightDto flightDto) throws Exception {
        flightService.insertFlight(flightDto);
    }

}
