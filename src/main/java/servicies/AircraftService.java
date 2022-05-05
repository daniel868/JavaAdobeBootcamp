package servicies;

import domain.aircraft.Aircraft;
import domain.aircraft.AircraftType;
import domain.aircraft.CargoAircraft;
import domain.aircraft.CommercialAircraft;
import storage.AircraftRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AircraftService {
    private AircraftRepository repository;

    private static AircraftService instance;

    private AircraftService() {
        repository = AircraftRepository.getInstance();
    }

    public static synchronized AircraftService getInstance() {
        if (instance == null) {
            instance = new AircraftService();
        }
        return instance;
    }

    public List<Aircraft> getCommercialsAircraft() {
        return repository.getMockAircraft()
                .stream()
                .filter(aircraft -> aircraft instanceof CommercialAircraft)
                .collect(Collectors.toList());
    }

    public List<Aircraft> getCargoAircraft() {
        return repository.getMockAircraft()
                .stream()
                .filter(aircraft -> aircraft instanceof CargoAircraft)
                .collect(Collectors.toList());
    }

    public List<Aircraft> getBoeing747Aircraft() {
        return repository.getMockAircraft()
                .stream()
                .filter(aircraft -> aircraft instanceof CargoAircraft &&
                        aircraft.getAircraftName().equals(AircraftType.BOEING_747.getAircraftName()))
                .collect(Collectors.toList());
    }

    public Double getLongUsedAircraft() {
        return repository.getMockAircraft()
                .stream()
                .max(Comparator.comparing(Aircraft::getTotalFlightHours))
                .get()
                .getTotalFlightHours();
    }
}
