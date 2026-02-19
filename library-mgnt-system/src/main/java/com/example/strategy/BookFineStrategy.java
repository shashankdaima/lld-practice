package main.java.com.example.strategy;

import main.java.com.example.models.BorrowRecord;

public interface BookFineStrategy {
    public double calculateFine(BorrowRecord record);    
}
