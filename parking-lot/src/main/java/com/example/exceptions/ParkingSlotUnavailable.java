package com.example.exceptions;

public class ParkingSlotUnavailable extends RuntimeException {
    public ParkingSlotUnavailable() {
        super("Parking slot unavailable");
    }

    public ParkingSlotUnavailable(String message) {
        super(message);
    }
}
