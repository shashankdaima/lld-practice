package com.example.models.lot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private static ParkingLot instance = null;
    private List<ParkingFloor> floors;

    private ParkingLot() {
        this.floors = new ArrayList<>();
    }

    public static synchronized ParkingLot getInstance() {
        if (instance == null) {
            instance = new ParkingLot();
        }
        return instance;
    }

    public List<ParkingFloor> getFloors() {
        return floors;
    }

    public void setFloors(List<ParkingFloor> floors) {
        this.floors = floors;
    }

    public boolean addFloor(ParkingFloor floor) {
        if (floors == null) {
            floors = new ArrayList<>();
        }
        return floors.add(floor);
    }
}
