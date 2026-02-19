package com.example.exceptions;

public class ParkingLotUninitializedException extends IllegalStateException {
    public ParkingLotUninitializedException() {
        super("ParkingLot is not initialized. Call getInstance(ParkingFloor[] floors) first.");
    }
}
