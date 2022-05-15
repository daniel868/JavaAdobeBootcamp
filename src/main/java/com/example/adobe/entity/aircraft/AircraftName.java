package com.example.adobe.entity.aircraft;

public enum AircraftName {
    BOEING_747("BOEING_747"), BOEING_748("BOEING_748"),
    AIRBUS_330("A330"), AIRBUS_338("A338");
    private final String aircraftName;

    AircraftName(String aircraftName) {
        this.aircraftName = aircraftName;
    }

    public String getAircraftName() {
        return aircraftName;
    }
}
