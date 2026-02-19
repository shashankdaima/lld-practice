package main.java.com.example.models;

import java.time.LocalDateTime;

import com.example.models.Member;

public class BorrowRecord {
    private String recordId;
    private Member member;
    private BookCopy copy;
    private LocalDateTime borrowDate;
    private LocalDateTime dueDate;
    private LocalDateTime returnDate;
    public BorrowRecord(String recordId, Member member, BookCopy copy) {
        this.recordId = recordId;
        this.member = member;
        this.copy = copy;
        this.borrowDate= LocalDateTime.now();
    }

    public BorrowRecord(String recordId, Member member, BookCopy copy, int noOfDueDays) {
        this.recordId = recordId;
        this.member = member;
        this.copy = copy;
        this.borrowDate= LocalDateTime.now();
        this.dueDate = this.borrowDate.plusDays(noOfDueDays);
    }

    

    public String getRecordId() {
        return recordId;
    }
    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }
    public Member getMember() {
        return member;
    }
    public void setMember(Member member) {
        this.member = member;
    }
    public BookCopy getCopy() {
        return copy;
    }
    public void setCopy(BookCopy copy) {
        this.copy = copy;
    }
    public LocalDateTime getBorrowDate() {
        return borrowDate;
    }
    public void setBorrowDate(LocalDateTime borrowDate) {
        this.borrowDate = borrowDate;
    }
    public LocalDateTime getDueDate() {
        return dueDate;
    }
    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }
    public LocalDateTime getReturnDate() {
        return returnDate;
    }
    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

}
