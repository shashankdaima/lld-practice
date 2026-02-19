package com.example.models;

import java.util.ArrayList;

public class Member {
    private String memberId;
    private String name;
    private String email;
    private ArrayList<BorrowRecord> borrowRecords;

    public Member(String memberId, String name, String email) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.borrowRecords = new ArrayList<>();
    }

    public String getMemberId() {
        return memberId;
    }
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public ArrayList<BorrowRecord> getBorrowRecords() {
        return borrowRecords;
    }
}
