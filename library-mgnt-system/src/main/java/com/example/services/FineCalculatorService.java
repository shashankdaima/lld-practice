package com.example.services;

import com.example.models.BorrowRecord;
import com.example.strategy.BookFineStrategy;

public class FineCalculatorService {
    public static double calculateFine(BookFineStrategy strategy, BorrowRecord record) {
        return strategy.calculateFine(record);
    }
}
