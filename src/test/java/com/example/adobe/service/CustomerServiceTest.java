package com.example.adobe.service;

import com.amazonaws.services.alexaforbusiness.model.TemperatureUnit;
import com.example.adobe.domain.date_time.DateTimeDetails;
import com.example.adobe.dto.CustomerDto;
import com.example.adobe.dto.CustomerFlightDto;
import com.example.adobe.entity.flight.Flight;
import com.example.adobe.entity.flight.FlightType;
import com.example.adobe.entity.people.Customer;
import com.example.adobe.repository.CustomerRepository;
import com.example.adobe.repository.FlightRepository;
import com.example.adobe.service.date_time.FlightDateTimeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.*;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private FlightRepository flightRepository;

    @Mock
    private FlightDateTimeService flightDateTimeService;

    private CustomerService underTest;


    //mock data
    private Customer mockCustomer;
    private CustomerFlightDto mockCustomerFlightDto;
    private Flight mockFlight;


    @Before
    public void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");

        underTest = new CustomerService(customerRepository,
                modelMapper,
                flightRepository,
                flightDateTimeService,
                simpleDateFormat
        );
        mockCustomer = Customer.builder()
                .name("CustomerName")
                .surname("CustomerSurname")
                .emailAddress("email@gmail.com")
                .phoneNumber("0431321312")
                .build();
        mockCustomerFlightDto = new CustomerFlightDto("1",
                "OTP",
                "CDG");

        mockFlight = Flight.builder()
                .flightType(FlightType.COMMERCIAL_FLIGHT.getFlightType())
                .departureDateTime(Date.from(Instant.now()))
                .build();

    }

    @Test
    public void test_insertCustomer_success() {
        //given
        CustomerDto customerDto = new CustomerDto("customer1",
                "customer1",
                "094311321",
                "email@gmail.com");

        //when
        ResponseEntity<Customer> expected = underTest.insertCustomer(customerDto);

        //then
        assertThat(expected.getBody()).isNotNull();
        verify(customerRepository, times(1)).save(any());
        verifyNoMoreInteractions(customerRepository);
        verifyNoInteractions(flightRepository);
    }

    @Test
    public void test_getCustomerById_success() {
        //given

        Mockito.when(customerRepository.findById(1L))
                .thenReturn(Optional.ofNullable(mockCustomer));

        //when
        ResponseEntity<CustomerDto> expected = underTest.getCustomerById(1L);

        //then
        assertThat(expected).isNotNull();
        assertThat(expected.getBody().getName()).isEqualTo(mockCustomer.getName());

        verify(customerRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(customerRepository);

    }

    @Test
    public void test_bookCustomerFlight_fromDb_success() {
        //given
        Mockito.when(customerRepository.findById(1L))
                .thenReturn(Optional.ofNullable(mockCustomer));

        Mockito.when(flightRepository.getFlightByLocation("OTP", "CDG"))
                .thenReturn(asList(mockFlight));

        //when
        ResponseEntity<CustomerFlightDto> expected = underTest.bookFlight(mockCustomerFlightDto);

        //then
        assertThat(expected).isNotNull();

        verify(customerRepository, times(1)).findById(1L);
        verify(flightRepository, times(1)).getFlightByLocation("OTP", "CDG");


        verifyNoInteractions(flightDateTimeService);
    }

    @Test
    public void test_bookCustomerFlight_restClient_success() {
        //given
        Mockito.when(customerRepository.findById(1L))
                .thenReturn(Optional.ofNullable(mockCustomer));

        Mockito.when(flightRepository.getFlightByLocation("OTP", "CDG"))
                .thenReturn(new ArrayList<>());

        Mockito.when(flightDateTimeService.getFlightDateTime("OTP", "CDG"))
                .thenReturn(DateTimeDetails
                        .builder().airline_iata("mock_data")
                        .arr_iata("mock_data")
                        .dep_iata("mock_data")
                        .dep_time("2022-05-25 13:50")
                        .arr_time("2022-05-25 10:50")
                        .build());

        //when
        ResponseEntity<CustomerFlightDto> expected = underTest.bookFlight(mockCustomerFlightDto);

        assertThat(expected).isNotNull();
        assertThat(mockCustomer.getBookedFlights().size())
                .isGreaterThan(0);

        verify(flightDateTimeService).getFlightDateTime("OTP", "CDG");
        verify(customerRepository).findById(1L);

    }
}
