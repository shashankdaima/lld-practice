package com.example;

import com.example.enums.BookStatus;
import com.example.models.Author;
import com.example.models.Book;
import com.example.models.BookCopy;
import com.example.models.BorrowRecord;
import com.example.models.Library;
import com.example.models.LibraryActor;
import com.example.models.Member;
import com.example.strategy.SimplePerDayFineStrategy;

public class Main {
    public static void main(String[] args) {

        // Setup library
        Library library = Library.getInstance();
        LibraryActor actor = new LibraryActor();

        // Create authors
        Author author1 = new Author("Robert Martin", "A001", "robert@example.com");
        Author author2 = new Author("Joshua Bloch", "A002", "joshua@example.com");

        // Create books with copies
        Book book1 = new Book("B001", "Clean Code", author1, "978-0132350884");
        book1.getCopies().add(new BookCopy("B001-C1"));
        book1.getCopies().add(new BookCopy("B001-C2"));

        Book book2 = new Book("B002", "Effective Java", author2, "978-0134685991");
        book2.getCopies().add(new BookCopy("B002-C1"));

        // Register books
        actor.addBook(book1);
        actor.addBook(book2);

        // Register members
        Member member1 = new Member("M001", "Alice", "alice@example.com");
        Member member2 = new Member("M002", "Bob", "bob@example.com");
        actor.registerMember(member1);
        actor.registerMember(member2);

        // Borrow books
        boolean borrowed1 = actor.borrowBook(member1, "B001");
        System.out.println("Alice borrowed Clean Code: " + borrowed1);

        boolean borrowed2 = actor.borrowBook(member2, "B001");
        System.out.println("Bob borrowed Clean Code (2nd copy): " + borrowed2);

        boolean borrowed3 = actor.borrowBook(member1, "B002");
        System.out.println("Alice borrowed Effective Java: " + borrowed3);

        // Search
        System.out.println("\nSearch by author 'Joshua Bloch': "
            + actor.searchBooksByAuthor("Joshua Bloch").size() + " book(s) found");

        System.out.println("Search by ISBN '978-0132350884': "
            + actor.searchBooksByIsbn("978-0132350884").size() + " book(s) found");

        // Return a book
        BorrowRecord record = member1.getBorrowRecords().get(0);
        boolean returned = actor.returnBook(member1, record.getCopy().getCopyId());
        System.out.println("\nAlice returned book: " + returned);
        System.out.println("Copy status after return: " + record.getCopy().getStatus());

        // Calculate fine (simulate overdue by backdating due date)
        BorrowRecord record2 = member2.getBorrowRecords().get(0);
        record2.setDueDate(record2.getBorrowDate().minusDays(3)); // simulate 3 days overdue
        record2.setReturnDate(record2.getBorrowDate());
        double fine = actor.calculateFine(record2, new SimplePerDayFineStrategy());
        System.out.println("\nFine for Bob's overdue book: ₹" + fine);
    }
}
