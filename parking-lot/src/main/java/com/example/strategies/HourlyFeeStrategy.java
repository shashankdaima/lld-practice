package com.example.strategies;

import java.time.Duration;
import java.time.LocalDateTime;

import com.example.models.lot.ParkingTicket;

public class HourlyFeeStrategy implements FeeStrategy{
    private static int HOURLY_RATE = 1000;

    public double calculateFee(ParkingTicket ticket) {
        LocalDateTime exit = LocalDateTime.now();
        long minutes = Duration.between(ticket.getEntryTime(), exit).toMinutes();
        long hours = (long) Math.ceil(minutes / 60.0); // round up to next hour
        return Math.max(1, hours) * HOURLY_RATE;
    }

}
