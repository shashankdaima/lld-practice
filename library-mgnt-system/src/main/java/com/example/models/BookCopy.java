package com.example.models;

import com.example.enums.BookStatus;

public class BookCopy {
    private String copyId;
    private boolean isAvailable;
    private BookStatus status;
    public BookCopy(String copyId) {
        this.copyId = copyId;
        this.isAvailable=true;
        this.status=BookStatus.AVAILABLE;
    }
    public String getCopyId() {
        return copyId;
    }
    public void setCopyId(String copyId) {
        this.copyId = copyId;
    }
    public boolean isAvailable() {
        return isAvailable;
    }
    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
    public BookStatus getStatus() {
        return status;
    }
    public void setStatus(BookStatus status) {
        this.status = status;
    }

}
