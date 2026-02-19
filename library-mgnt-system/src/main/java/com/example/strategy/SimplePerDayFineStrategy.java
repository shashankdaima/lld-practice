package com.example.strategy;

import com.example.models.BorrowRecord;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class SimplePerDayFineStrategy implements BookFineStrategy{

    private static final double FINE_PER_DAY = 50.0; // Simple example: $50 per day overdue

    @Override
    public double calculateFine(BorrowRecord record) {
        LocalDateTime returnDate = record.getReturnDate();
        LocalDateTime dueDate = record.getDueDate();

        // If not returned yet or no due date, no fine
        if (returnDate == null || dueDate == null) {
            return 0.0;
        }

        long daysLate = ChronoUnit.DAYS.between(dueDate, returnDate);
        if (daysLate <= 0) {
            return 0.0;
        }

        return daysLate * FINE_PER_DAY;
    }
    
}
