package com.example.models.lot;

public class ParkingLot {
    private static ParkingLot instance = null;
    private ParkingFloor[] floors;

    private ParkingLot(ParkingFloor[] floors) {
        this.floors = floors;
    }

    public static synchronized ParkingLot getInstance(ParkingFloor[] floors) {
        if (instance == null) {
            instance = new ParkingLot(floors);
        }
        return instance;
    }

    public static synchronized ParkingLot getInstance() {
        if (instance == null) {
            throw new IllegalStateException("PackingLot is not initialized. Call getInstance(ParkingFloor[] floors) first.");
        }
        return instance;
    }

    public ParkingFloor[] getFloors() {
        return floors;
    }

    public void setFloors(ParkingFloor[] floors) {
        this.floors = floors;
    }
}
