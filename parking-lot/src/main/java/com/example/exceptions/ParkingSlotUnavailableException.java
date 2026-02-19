package com.example.exceptions;

public class ParkingSlotUnavailableException extends RuntimeException {
    public ParkingSlotUnavailableException() {
        super("Parking slot unavailable");
    }

    public ParkingSlotUnavailableException(String message) {
        super(message);
    }
}
