package domain.flight;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommercialFlight extends Flight {
    private final int numberOfPassengers;

    @Builder
    protected CommercialFlight(String flightType, Long flightDuration, int numberOfPassengers, String from, String to) {
        super(flightType, flightDuration, from, to);
        this.numberOfPassengers = numberOfPassengers;
    }
}
