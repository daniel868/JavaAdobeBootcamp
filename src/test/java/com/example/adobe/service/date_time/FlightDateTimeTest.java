package com.example.adobe.service.date_time;

import com.example.adobe.domain.date_time.DateTimeDetails;
import com.example.adobe.domain.date_time.DateTimeResponse;
import com.example.adobe.domain.date_time.DateTimeService;
import com.example.adobe.exception.FlightNotFoundException;
import kong.unirest.HttpResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.concurrent.CompletableFuture;

import static java.util.Arrays.asList;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class FlightDateTimeTest {

    @Mock
    public DateTimeService dateTimeService;

    private FlightDateTimeService underTest;

    @Before
    public void setUp() {
        underTest = new FlightDateTimeServiceImpl(dateTimeService);
    }

    @Test
    public void test_getFlightDateTime_success() {
        //given
        DateTimeDetails mockDateTimeDetails = DateTimeDetails.builder()
                .arr_time("2022-03-15 22:10")
                .dep_time("2022-03-15 220:10")
                .build();

        DateTimeResponse mockDateTimeResponse = new DateTimeResponse(asList(mockDateTimeDetails));

        HttpResponse<DateTimeResponse> mockResponse = Mockito.mock(HttpResponse.class);

        Mockito.when(mockResponse.getBody()).thenReturn(mockDateTimeResponse);


        Mockito.when(dateTimeService.getRemoteDateTimeFlight("OTP", "CDG"))
                .thenReturn(CompletableFuture.completedFuture(mockResponse));

        //when
        DateTimeDetails expected = underTest.getFlightDateTime("OTP", "CDG");

        //then
        assertThat(expected).isNotNull();
        assertThat(expected.getArr_time()).isEqualTo(mockDateTimeDetails.getArr_time());

    }

    @Test(expected = FlightNotFoundException.class)
    public void test_getFlightDateTime_failed() {
        //given
        HttpResponse<DateTimeResponse> mockResponse = Mockito.mock(HttpResponse.class);

        Mockito.when(mockResponse.getBody()).thenReturn(null);

        Mockito.when(dateTimeService.getRemoteDateTimeFlight("OTP", "CDG"))
                .thenReturn(CompletableFuture.completedFuture(mockResponse));

        //when
        DateTimeDetails expected = underTest.getFlightDateTime("OTP", "CDG");

        //then
    }
}
