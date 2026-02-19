package main.java.com.example.models;

import java.util.ArrayList;

public class Book {
    private String bookId;
    private String title;
    private Author author;
    private String isbn;
    private ArrayList<BookCopy> copies;

    public Book(String bookId, String title, Author author, String isbn) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.copies = new ArrayList<>();
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public ArrayList<BookCopy> getCopies() {
        return copies;
    }

    public void setCopies(ArrayList<BookCopy> copies) {
        this.copies = copies;
    }

}
