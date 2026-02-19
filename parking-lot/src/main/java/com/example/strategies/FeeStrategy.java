package com.example.strategies;

import com.example.models.lot.ParkingTicket;

public interface FeeStrategy {
    public double calculateFee(ParkingTicket ticket);

}
