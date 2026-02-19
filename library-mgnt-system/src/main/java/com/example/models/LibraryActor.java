package com.example.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.example.enums.BookStatus;
import com.example.strategy.BookFineStrategy;

public class LibraryActor {

    // Add and manage books in the library catalog
    public void addBook(Book book) {
        Library library = Library.getInstance();
        library.getBooks().add(book);
    }

    public void removeBook(Book book) {
        Library library = Library.getInstance();
        library.getBooks().remove(book);
    }

    // Register and manage library members
    public void registerMember(Member member) {
        Library library = Library.getInstance();
        library.getMembers().add(member);
    }

    public void removeMember(Member member) {
        Library library = Library.getInstance();
        library.getMembers().remove(member);
    }

    // Allow members to borrow books (check availability)
    public boolean borrowBook(Member member, String bookId) {
        Library library = Library.getInstance();
        for (Book book : library.getBooks()) {
            if (book.getBookId().equals(bookId)) {
                for (BookCopy copy : book.getCopies()) {
                    if (copy.getStatus() == BookStatus.AVAILABLE) {
                        copy.setStatus(BookStatus.BORROWED);
                        BorrowRecord record = new BorrowRecord(UUID.randomUUID().toString(), member, copy, 14);
                        library.getBorrowRecords().add(record);
                        member.getBorrowRecords().add(record);
                        return true;
                    }
                }
            }
        }
        return false; // not available
    }

    // Allow members to return borrowed books
    public boolean returnBook(Member member, String copyId) {
        Library library = Library.getInstance();
        for (BorrowRecord record : library.getBorrowRecords()) {
            if (record.getMember().equals(member) &&
                record.getCopy().getCopyId().equals(copyId) &&
                record.getReturnDate() == null) {
                record.setReturnDate(LocalDateTime.now());
                record.getCopy().setStatus(BookStatus.AVAILABLE);
                return true;
            }
        }
        return false;
    }

    // Track overdue borrowed books
    public List<BorrowRecord> getOverdueBorrowRecords() {
        Library library = Library.getInstance();
        List<BorrowRecord> overdue = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        for (BorrowRecord record : library.getBorrowRecords()) {
            if (record.getReturnDate() == null && record.getDueDate() != null && record.getDueDate().isBefore(now)) {
                overdue.add(record);
            }
        }
        return overdue;
    }

    // Calculate fine for a borrow record
    public double calculateFine(BorrowRecord record, BookFineStrategy strategy) {
        return strategy.calculateFine(record);
    }

    // Search books by title
    public List<Book> searchBooksByTitle(String title) {
        Library library = Library.getInstance();
        List<Book> result = new ArrayList<>();
        for (Book book : library.getBooks()) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                result.add(book);
            }
        }
        return result;
    }

    // Search books by author name
    public List<Book> searchBooksByAuthor(String authorName) {
        Library library = Library.getInstance();
        List<Book> result = new ArrayList<>();
        for (Book book : library.getBooks()) {
            if (book.getAuthor().getName().equalsIgnoreCase(authorName)) {
                result.add(book);
            }
        }
        return result;
    }

    // Search books by ISBN
    public List<Book> searchBooksByIsbn(String isbn) {
        Library library = Library.getInstance();
        List<Book> result = new ArrayList<>();
        for (Book book : library.getBooks()) {
            if (book.getIsbn().equalsIgnoreCase(isbn)) {
                result.add(book);
            }
        }
        return result;
    }
}
