package com.example.models.lot;

import java.util.UUID;

import com.example.exceptions.ParkingSlotUnavailableException;
import com.example.models.vehicles.Vehicle;

public class ParkingAttendent {
    public ParkingTicket assignParkingTicket(Vehicle vehicle){
        ParkingLot lot= ParkingLot.getInstance();
        for(ParkingFloor floor: lot.getFloors()){
            ParkingSpot availableSpot=floor.getAvailableParkingSpot(vehicle.getSize());
            if(availableSpot != null){
                availableSpot.setOccupied(true);
                return new ParkingTicket(UUID.randomUUID().toString(), vehicle, availableSpot);
            }
        }
        throw new ParkingSlotUnavailableException();
    }

    public void unAssignParkingTicket(ParkingTicket ticket){
        ticket.getSpot().setOccupied(false);
    }
}
