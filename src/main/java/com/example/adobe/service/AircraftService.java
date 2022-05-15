package com.example.adobe.service;

import com.example.adobe.dto.AircraftDto;
import com.example.adobe.entity.aircraft.Aircraft;
import com.example.adobe.entity.aircraft.CargoAircraft;
import com.example.adobe.entity.aircraft.CommercialAircraft;
import com.example.adobe.repository.AircraftRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AircraftService {
    private final AircraftRepository repository;
    private final ModelMapper modelMapper;

    public AircraftService(AircraftRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public List<Aircraft> getCommercialAircraft() {
        return repository.getCommercialAircraft();
    }

    public List<Aircraft> getCargoAircraft() {
        return repository.getCargoAircraft();
    }

    public ResponseEntity<Aircraft> insertAircraft(AircraftDto aircraftDto) {
        Aircraft newAircraft = aircraftDto.getAircraftType() == 1 ?
                modelMapper.map(aircraftDto, CommercialAircraft.class) :
                modelMapper.map(aircraftDto, CargoAircraft.class);

        repository.save(newAircraft);

        return ResponseEntity.ok(newAircraft);
    }
}
