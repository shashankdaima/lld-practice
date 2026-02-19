package com.example.models;

import java.util.ArrayList;

public class Library {
    private static Library instance;
    private ArrayList<BorrowRecord> borrowRecords;
    private ArrayList<Book> books;
    private ArrayList<Member> members;
 
    private Library() {
        this.borrowRecords = new ArrayList<>();
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    public static synchronized Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    public ArrayList<BorrowRecord> getBorrowRecords() {
        return borrowRecords;
    }

    public void setBorrowRecords(ArrayList<BorrowRecord> borrowRecords) {
        this.borrowRecords = borrowRecords;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

public ArrayList<Member> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<Member> members) {
        this.members = members;
    }
}
    
