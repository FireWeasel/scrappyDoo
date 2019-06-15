package model;

/**
 * Derived class for a book
 * @version 0.1
 */
public class Book extends Item {
    /**
     * authors of the book
     */
    public String authors;
    /**
     * publishers of the book
     */
    public String publisher;
    /**
     * ISBN of the book
     */
    public String isbn;

    public Book(String genre, String format, int year, String title) {
        super(genre, format, year, title);
    }
}
