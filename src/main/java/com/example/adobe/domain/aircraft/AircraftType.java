package com.example.adobe.domain.aircraft;

public enum AircraftType {
    BOEING_747("Boeing_747"), BOEING_748("BOEING_748"),
    AIRBUS_330("A330"), AIRBUS_338("A338");
    private final String aircraftName;

    AircraftType(String aircraftName) {
        this.aircraftName = aircraftName;
    }

    public String getAircraftName() {
        return aircraftName;
    }
}
