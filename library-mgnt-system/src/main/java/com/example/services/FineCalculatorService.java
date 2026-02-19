package main.java.com.example.services;

import main.java.com.example.strategy.BookFineStrategy;

public class FineCalculatorService {
       public static double calculateFine(BookFineStrategy strategy,BorrowRecord record ){
            return strategy.calculateFine(record);
       }
}
