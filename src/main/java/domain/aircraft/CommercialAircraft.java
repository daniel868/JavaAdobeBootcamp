package domain.aircraft;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommercialAircraft extends Aircraft {
    private final int seatsNumber;
    private final int pilotsNumber;

    @Builder
    public CommercialAircraft(String aircraftName, int enginesNumber, Double totalFlightHours, int seatsNumber, int pilotsNumber) {
        super(aircraftName, enginesNumber, totalFlightHours);
        this.seatsNumber = seatsNumber;
        this.pilotsNumber = pilotsNumber;
    }
}
