package domain.flight;

import domain.aircraft.Aircraft;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CargoFlight extends Flight {
    private final int numberOfPackages;

    @Builder
    protected CargoFlight(String flightType, Long flightDuration, int numberOfPackages, String from, String to) {
        super(flightType, flightDuration, from, to);
        this.numberOfPackages = numberOfPackages;
    }
}
