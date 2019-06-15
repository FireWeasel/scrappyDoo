package model;

import java.util.List;

/**
 * Derived class for a book
 * @version 0.1
 */
public class Book extends Item {
    /**
     * authors of the book
     */
    public List<String> authors;
    /**
     * publishers of the book
     */
    public String publisher;
    /**
     * ISBN of the book
     */
    public String isbn;

    public Book(String genre, String format, int year, String title, List<String> authors, String publisher, String isbn) {
        super(genre, format, year, title);
        this.authors = authors;
        this.publisher = publisher;
        this.isbn = isbn;
    }
}
