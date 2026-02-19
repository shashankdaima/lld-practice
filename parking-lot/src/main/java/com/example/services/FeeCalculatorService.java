package com.example.services;

import com.example.models.lot.ParkingTicket;
import com.example.strategies.FeeStrategy;

public class FeeCalculatorService {
    private FeeStrategy strategy;

    public FeeCalculatorService(FeeStrategy strategy) {
        this.strategy = strategy;
    }

    public FeeStrategy getStrategy() {
        return strategy;
    }

    public void setStrategy(FeeStrategy strategy) {
        this.strategy = strategy;
    }

    public double calculateFee(ParkingTicket ticket){
        return strategy.calculateFee(ticket);
    }

}
