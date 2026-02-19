package com.example.models.lot;

public class PackingLot {
    private ParkingFloor[] floors;

    public PackingLot(ParkingFloor[] floors) {
        this.floors = floors;
    }

    public ParkingFloor[] getFloors() {
        return floors;
    }

    public void setFloors(ParkingFloor[] floors) {
        this.floors = floors;
    }
}
