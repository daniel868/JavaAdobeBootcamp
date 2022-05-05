package storage;

import domain.flight.CargoFlight;
import domain.flight.CommercialFlight;
import domain.flight.Flight;
import domain.flight.FlightType;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class FlightRepository {
    private static FlightRepository instance;

    private static List<Flight> flightList = new ArrayList<>();

    static {
        flightList.add(CommercialFlight.builder()
                .flightType(FlightType.COMMERCIAL_FLIGHT.getFlightType())
                .flightDuration(960L)
                .to("OTP,Bucharest")
                .to("JFK,New York")
                .numberOfPassengers(200)
                .build());

        flightList.add(CommercialFlight.builder()
                .flightType(FlightType.COMMERCIAL_FLIGHT.getFlightType())
                .flightDuration(120L)
                .to("IOW,Iowa")
                .to("JFK,New York")
                .numberOfPassengers(150)
                .build());


        flightList.add(CargoFlight.builder()
                .flightType(FlightType.CARGO_FLIGHT.getFlightType())
                .from("USA")
                .to("OTP,Bucharest")
                .numberOfPackages(100)
                .flightDuration(960L)
                .build());
    }

    private FlightRepository() {

    }

    public synchronized FlightRepository getInstance() {
        if (instance == null) {
            instance = new FlightRepository();
        }
        return instance;
    }

}
