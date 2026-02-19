package com.example.models.lot;

import com.example.exceptions.ParkingLotUninitializedException;

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
            throw new ParkingLotUninitializedException();
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
