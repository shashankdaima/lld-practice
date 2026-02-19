package com.example.models.lot;

public class ParkingFloor {
    private ParkingSpot[] spots;
    private int floor;

    public ParkingFloor(ParkingSpot[] spots, int floor) {
        this.spots = spots;
        this.floor = floor;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public ParkingSpot[] getSpots() {
        return spots;
    }

    public void setSpots(ParkingSpot[] spots) {
        this.spots = spots;
    };

}
