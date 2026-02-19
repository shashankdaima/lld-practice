package com.example.strategy;

import com.example.models.BorrowRecord;

public interface BookFineStrategy {
    public double calculateFine(BorrowRecord record);    
}
