package com.example.adobe.service;

import com.example.adobe.dto.AircraftDto;
import com.example.adobe.entity.aircraft.Aircraft;
import com.example.adobe.entity.aircraft.AircraftName;
import com.example.adobe.entity.aircraft.CommercialAircraft;
import com.example.adobe.repository.AircraftRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AircraftServiceTest {

    @Mock
    private AircraftRepository aircraftRepository;

    @Captor
    private ArgumentCaptor<Aircraft> aircraftArgumentCaptor;

    private AircraftService underTest;

    @Before
    public void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        underTest = new AircraftService(aircraftRepository, modelMapper);
    }

    @Test
    public void test_insertAircraft_success() {
        //given
        AircraftDto aircraftDto = AircraftDto.builder()
                .aircraftType(1)
                .aircraftName(AircraftName.BOEING_747.getAircraftName())
                .build();

        //when
        ResponseEntity<Aircraft> result = underTest.insertAircraft(aircraftDto);

        //then
        assertThat(result).isNotNull();
        assertThat(result.getBody()).isInstanceOf(CommercialAircraft.class);
        verify(aircraftRepository, times(1)).save(aircraftArgumentCaptor.capture());

        assertThat(aircraftArgumentCaptor.getValue()).isEqualTo(result.getBody());
    }

    @Test
    public void test_getCommercialAircraft_success() {
        //given
        Mockito.when(aircraftRepository.getCommercialAircraft())
                .thenReturn(new ArrayList<>());

        //when
        List<AircraftDto> result = underTest.getCommercialAircraft();

        //then
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(0);
        verify(aircraftRepository, times(1)).getCommercialAircraft();

        verifyNoMoreInteractions(aircraftRepository);
    }

    @Test
    public void test_getCargoAircraft_success() {
        //given
        Mockito.when(aircraftRepository.getCargoAircraft())
                .thenReturn(new ArrayList<>());

        //when
        List<AircraftDto> expected = underTest.getCargoAircraft();

        //then
        assertThat(expected).isNotNull();
        assertThat(expected.size()).isEqualTo(0);
        verify(aircraftRepository, times(1)).getCargoAircraft();

        verifyNoMoreInteractions(aircraftRepository);
    }
}
