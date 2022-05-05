import domain.aircraft.Aircraft;
import servicies.AircraftService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        AircraftService aircraftService = AircraftService.getInstance();
        List<Aircraft> boeing747Aircraft = aircraftService.getBoeing747Aircraft();
        List<Aircraft> commercialsAircraft = aircraftService.getCommercialsAircraft();
        List<Aircraft> cargoAircraft = aircraftService.getCargoAircraft();

        System.out.println(aircraftService.getLongUsedAircraft());
    }
}
