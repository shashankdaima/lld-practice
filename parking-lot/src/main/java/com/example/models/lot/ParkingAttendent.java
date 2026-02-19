package com.example.models.lot;

import java.util.UUID;

import com.example.exceptions.ParkingSlotUnavailableException;
import com.example.models.vehicles.Vehicle;
import com.example.services.FeeCalculatorService;
import com.example.strategies.HourlyFeeStrategy;

public class ParkingAttendent {
    public ParkingTicket assignParkingTicket(Vehicle vehicle) {
        ParkingLot lot = ParkingLot.getInstance();
        for (ParkingFloor floor : lot.getFloors()) {
            ParkingSpot availableSpot = floor.getAvailableParkingSpot(vehicle.getSize());
            if (availableSpot != null) {
                availableSpot.setOccupied(true);
                return new ParkingTicket(UUID.randomUUID().toString(), vehicle, availableSpot);
            }
        }
        throw new ParkingSlotUnavailableException();
    }

    public double unAssignParkingTicket(ParkingTicket ticket) {
        ticket.getSpot().setOccupied(false);
        FeeCalculatorService calculator = new FeeCalculatorService(new HourlyFeeStrategy());
        return calculator.calculateFee(ticket);

    }
}
