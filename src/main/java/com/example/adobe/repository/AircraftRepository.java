package com.example.adobe.repository;

import com.example.adobe.entity.aircraft.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AircraftRepository extends JpaRepository<Aircraft, Long> {

    @Query(value = "Select * from aircraft Where aircraft_type = 1", nativeQuery = true)
    public List<Aircraft> getCommercialAircraft();

    @Query(value = "Select * from aircraft Where aircraft_type = 2", nativeQuery = true)
    public List<Aircraft> getCargoAircraft();


}
