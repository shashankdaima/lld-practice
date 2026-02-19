package com.example.models.lot;

import java.util.Optional;

import com.example.enums.SpotType;
import com.example.enums.VehicleSize;

import java.util.ArrayList;
import java.util.List;

public class ParkingFloor {
    private List<ParkingSpot> spots = new ArrayList<>();
    private int floor;
    
    public ParkingFloor(int floor){
        this.floor = floor;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public List<ParkingSpot> getSpots() {
        return spots;
    }

    public void setSpots(List<ParkingSpot> spots) {
        this.spots = spots;
    }

    private SpotType mapToSpotType(VehicleSize size){
        return switch (size) {
            case TWO_WHEELER -> SpotType.BIKE;
            case SMALL       -> SpotType.SMALL;
            case MEDIUM      -> SpotType.MEDIUM;
            case LARGE       -> SpotType.LARGE;
        };
    }

    public ParkingSpot getAvailableParkingSpot(VehicleSize vehicleSize){
        SpotType spotType = mapToSpotType(vehicleSize);
        for (ParkingSpot spot : spots) {
            if ((!spot.isOccupied()) && spot.getType() == spotType) {
                return spot;
            }
        }
        return null;
    }

    public boolean addSpot(ParkingSpot spot){
        this.spots.add(spot);
        return true;
    }
}
