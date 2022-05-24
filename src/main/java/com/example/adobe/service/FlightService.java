package com.example.adobe.service;


import com.example.adobe.dto.FlightDto;
import com.example.adobe.entity.aircraft.Aircraft;
import com.example.adobe.entity.flight.Flight;
import com.example.adobe.entity.flight.FlightType;
import com.example.adobe.repository.AircraftRepository;
import com.example.adobe.repository.FlightRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

@Service
public class FlightService {
    private final FlightRepository flightRepository;
    private final AircraftRepository aircraftRepository;
    private final SimpleDateFormat dateFormat;

    private final ModelMapper modelMapper;

    public FlightService(FlightRepository flightRepository, AircraftRepository aircraftRepository, SimpleDateFormat dateFormat, ModelMapper modelMapper) {
        this.flightRepository = flightRepository;
        this.aircraftRepository = aircraftRepository;
        this.dateFormat = dateFormat;
        this.modelMapper = modelMapper;
    }

    public ResponseEntity<FlightDto> getFlightById(Long id) {
        return ResponseEntity.ok(flightRepository.findById(id)
                .map(flight -> modelMapper.map(flight, FlightDto.class))
                .orElseThrow(() -> new RuntimeException("Could not found flight with id: " + id)));
    }


    public void insertFlight(FlightDto flightDto) throws Exception {
        Aircraft requestedAircraft = aircraftRepository.findById(flightDto.getAircraftId())
                .orElseThrow(() -> new RuntimeException("Could not found aircraft with id: " + flightDto.getAircraftId()));

        String flightType = flightDto.getFlightType() == 1 ?
                FlightType.COMMERCIAL_FLIGHT.getFlightType() :
                FlightType.CARGO_FLIGHT.getFlightType();


        Flight newFlight = Flight.builder()
                .flightType(flightType)
                .departureDateTime(dateFormat.parse(flightDto.getDepartureDateTime()))
                .landingDateTime(dateFormat.parse(flightDto.getLandingDateTime()))
                .toLocation(flightDto.getToLocation())
                .fromLocation(flightDto.getToLocation())
                .build();

        newFlight.setAircraft(requestedAircraft);

        flightRepository.save(newFlight);
    }
}
