package com.example.adobe.domain.date_time;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import kong.unirest.HttpResponse;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class DateTimeServiceTest {

    private static WireMockServer wireMockServer;

    private static DateTimeService underTest;
    private static String hostUrl;
    private static ApiConnector apiConnector;

    @BeforeClass
    public static void setUp() {
        wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().dynamicPort());
        wireMockServer.start();

        int port = wireMockServer.port();
        WireMock.configureFor("localhost", port);

        hostUrl = "http://localhost:" + port+"?";

        apiConnector = new ApiConnector();
        apiConnector.setBaseUrl(hostUrl);

        underTest = new DateTimeClient(apiConnector);

    }

    @AfterClass
    public static void destroy() {
        wireMockServer.stop();
    }

    @Test
    public void test_getRemoteDateTimeFlight_success() throws Exception {
        //given

        String flightUrl = String.format("&dep_iata=%s&arr_iata=%s&api_key=16b2a750-2d0d-42aa-b776-5d91ff73a7d5",
                "OTP",
                "CDG");

        stubFor(
                WireMock.get(urlPathMatching(flightUrl))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withBody("   \"response\": [\n" +
                                        "        {\n" +
                                        "            \"airline_iata\": \"AZ\",\n" +
                                        "            \"airline_icao\": \"ITY\",\n" +
                                        "            \"flight_iata\": \"AZ5055\",\n" +
                                        "            \"flight_icao\": \"ITY5055\",\n" +
                                        "            \"flight_number\": \"5055\",\n" +
                                        "            \"dep_iata\": \"OTP\",\n" +
                                        "            \"dep_icao\": \"LROP\",\n" +
                                        "            \"dep_terminal\": null,\n" +
                                        "            \"dep_gate\": null,\n" +
                                        "            \"dep_time\": \"2022-05-25 13:50\",\n" +
                                        "            \"dep_time_utc\": \"2022-05-25 10:50\",\n" +
                                        "            \"arr_iata\": \"BAY\",\n" +
                                        "            \"arr_icao\": \"LRBM\",\n" +
                                        "            \"arr_terminal\": null,\n" +
                                        "            \"arr_gate\": null,\n" +
                                        "            \"arr_baggage\": null,\n" +
                                        "            \"arr_time\": \"2022-05-25 15:15\",\n" +
                                        "            \"arr_time_utc\": \"2022-05-25 12:15\",\n" +
                                        "            \"cs_airline_iata\": \"RO\",\n" +
                                        "            \"cs_flight_number\": \"617\",\n" +
                                        "            \"cs_flight_iata\": \"RO617\",\n" +
                                        "            \"status\": \"active\",\n" +
                                        "            \"duration\": 85,\n" +
                                        "            \"delayed\": null,\n" +
                                        "            \"aircraft_icao\": null,\n" +
                                        "            \"arr_time_ts\": 1653480900,\n" +
                                        "            \"dep_time_ts\": 1653475800\n" +
                                        "        },\n" +
                                        "        {\n" +
                                        "            \"airline_iata\": \"RO\",\n" +
                                        "            \"airline_icao\": \"ROT\",\n" +
                                        "            \"flight_iata\": \"RO617\",\n" +
                                        "            \"flight_icao\": \"ROT617\",\n" +
                                        "            \"flight_number\": \"617\",\n" +
                                        "            \"dep_iata\": \"OTP\",\n" +
                                        "            \"dep_icao\": \"LROP\",\n" +
                                        "            \"dep_terminal\": null,\n" +
                                        "            \"dep_gate\": null,\n" +
                                        "            \"dep_time\": \"2022-05-25 13:50\",\n" +
                                        "            \"dep_time_utc\": \"2022-05-25 10:50\",\n" +
                                        "            \"arr_iata\": \"BAY\",\n" +
                                        "            \"arr_icao\": \"LRBM\",\n" +
                                        "            \"arr_terminal\": null,\n" +
                                        "            \"arr_gate\": null,\n" +
                                        "            \"arr_baggage\": null,\n" +
                                        "            \"arr_time\": \"2022-05-25 15:15\",\n" +
                                        "            \"arr_time_utc\": \"2022-05-25 12:15\",\n" +
                                        "            \"cs_airline_iata\": null,\n" +
                                        "            \"cs_flight_number\": null,\n" +
                                        "            \"cs_flight_iata\": null,\n" +
                                        "            \"status\": \"active\",\n" +
                                        "            \"duration\": 85,\n" +
                                        "            \"delayed\": null,\n" +
                                        "            \"aircraft_icao\": \"AT75\",\n" +
                                        "            \"arr_time_ts\": 1653480900,\n" +
                                        "            \"dep_time_ts\": 1653475800\n" +
                                        "        }\n" +
                                        "]")));


        //when
        CompletableFuture<HttpResponse<DateTimeResponse>> expected = underTest.getRemoteDateTimeFlight("OTP", "CDG");

        //then
        HttpResponse<DateTimeResponse> response = expected.get();

        assertThat(response).isNotNull();
    }
}
