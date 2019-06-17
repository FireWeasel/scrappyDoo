package model;

/**
 * Derived class for a book
 * @version 0.1
 */
public class Book extends Item {

    public Book() {
        super();
    }

    public Book(String genre, String format, int year, String authors, String publisher, String isbn) {
        super(genre, format, year);
        this.authors = authors;
        this.publisher = publisher;
        this.isbn = isbn;
    }

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
}
