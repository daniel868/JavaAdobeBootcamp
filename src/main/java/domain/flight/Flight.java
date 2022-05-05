package domain.flight;

import domain.aircraft.Aircraft;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Flight {
    protected final String flightType;
    protected final Long flightDuration;
    protected final String from;
    protected final String to;

    protected Flight(String flightType, Long flightDuration, String from, String to) {
        this.flightType = flightType;
        this.flightDuration = flightDuration;
        this.from = from;
        this.to = to;
    }
}
