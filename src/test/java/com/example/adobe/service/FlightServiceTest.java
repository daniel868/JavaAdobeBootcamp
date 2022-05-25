package com.example.adobe.service;

import com.example.adobe.dto.FlightDto;
import com.example.adobe.entity.aircraft.Aircraft;
import com.example.adobe.entity.aircraft.CommercialAircraft;
import com.example.adobe.entity.flight.Flight;
import com.example.adobe.entity.flight.FlightType;
import com.example.adobe.repository.AircraftRepository;
import com.example.adobe.repository.FlightRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.http.ResponseEntity;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class FlightServiceTest {

    @Mock
    FlightRepository flightRepository;

    @Mock
    AircraftRepository aircraftRepository;

    private FlightService underTest;

    private SimpleDateFormat simpleDateFormat;

    private ModelMapper modelMapper;

    @Before
    public void setUp() {
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        modelMapper = new ModelMapper();

        PropertyMap<Flight, FlightDto> flightProperty = new PropertyMap<Flight, FlightDto>() {
            @Override
            protected void configure() {
                skip(source.getFlightType(), destination.getFlightType());
            }
        };
        modelMapper.addMappings(flightProperty);

        underTest = new FlightService(flightRepository, aircraftRepository,
                simpleDateFormat, modelMapper);

    }

    @Test
    public void test_getFlightById_success() {
        //given
        Flight mockFlight = Flight.builder()
                .flightType(FlightType.COMMERCIAL_FLIGHT.getFlightType())
                .departureDateTime(Date.from(Instant.now()))
                .landingDateTime(Date.from(Instant.now()))
                .fromLocation("OTP")
                .toLocation("OPM")
                .build();

        Mockito.when(flightRepository.findById(1L))
                .thenReturn(Optional.ofNullable(mockFlight));

        //when
        ResponseEntity<FlightDto> expected = underTest.getFlightById(1L);

        //then
        assertThat(expected).isNotNull();
        assertThat(expected.getBody().getFlightType()).isNull();

        verify(flightRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(flightRepository);
        verifyNoInteractions(aircraftRepository);

    }

    @Test
    public void test_getAllFlights_success() {
        //given
        Mockito.when(flightRepository.findAll()).thenReturn(new ArrayList<>());

        //when
        ResponseEntity<List<FlightDto>> expected = underTest.getAllFlights();

        //then
        assertThat(expected).isNotNull();
        assertThat(expected.getBody().size()).isEqualTo(0);

        verify(flightRepository, times(1)).findAll();
        verifyNoMoreInteractions(flightRepository);
    }

    @Test
    public void test_insertFlight_success() throws Exception {
        //given
        FlightDto mockFlightDto = FlightDto.builder()
                .flightType(1L)
                .aircraftId(1L)
                .departureDateTime("2022-03-15 10:12")
                .landingDateTime("2022-03-15 10:12")
                .fromLocation("OTP")
                .toLocation("CDG")
                .build();

        Aircraft mockAircraft = CommercialAircraft.builder()
                .aircraftName("Some name")
                .maxSpeed(250.42f)
                .passengerSeats(250)
                .build();

        Mockito.when(aircraftRepository.findById(1L)).thenReturn(
                Optional.ofNullable(mockAircraft));

        //when
        underTest.insertFlight(mockFlightDto);

        //then
        verify(aircraftRepository, times(1)).findById(1L);
        verify(flightRepository,times(1)).save(any());

        verifyNoMoreInteractions(aircraftRepository);
        verifyNoMoreInteractions(aircraftRepository);
    }
}
