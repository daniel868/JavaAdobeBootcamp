package storage;

import domain.aircraft.Aircraft;
import domain.aircraft.AircraftType;
import domain.aircraft.CargoAircraft;
import domain.aircraft.CommercialAircraft;

import java.util.ArrayList;
import java.util.List;

public class AircraftRepository {
    private static AircraftRepository instance;

    private static List<Aircraft> mockAircraft = new ArrayList<>();

    static {
        mockAircraft.add(CommercialAircraft.builder()
                .aircraftName(AircraftType.AIRBUS_338.getAircraftName())
                .enginesNumber(4)
                .totalFlightHours(10000d)
                .pilotsNumber(3)
                .seatsNumber(250)
                .build());

        mockAircraft.add(CommercialAircraft.builder()
                .aircraftName(AircraftType.BOEING_747.getAircraftName())
                .enginesNumber(2)
                .totalFlightHours(15000d)
                .pilotsNumber(2)
                .seatsNumber(150)
                .build());

        mockAircraft.add(CommercialAircraft.builder()
                .aircraftName(AircraftType.BOEING_747.getAircraftName())
                .enginesNumber(4)
                .totalFlightHours(16000d)
                .pilotsNumber(4)
                .seatsNumber(350)
                .build());

        mockAircraft.add(CargoAircraft.builder()
                .aircraftName(AircraftType.BOEING_747.getAircraftName())
                .enginesNumber(4)
                .totalFlightHours(10000d)
                .maxWeight(4500)
                .maxSpeed(350)
                .build());

        mockAircraft.add(CargoAircraft.builder()
                .aircraftName(AircraftType.BOEING_748.getAircraftName())
                .enginesNumber(4)
                .totalFlightHours(20000d)
                .maxWeight(4000)
                .maxSpeed(300)
                .build());

    }

    private AircraftRepository() {
    }

    public static synchronized AircraftRepository getInstance() {
        if (instance == null) {
            instance = new AircraftRepository();
        }
        return instance;
    }

    public List<Aircraft> getMockAircraft() {
        return mockAircraft;
    }
}
