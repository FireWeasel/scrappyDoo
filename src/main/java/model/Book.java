package model;

import java.util.List;
import java.util.Objects;

/**
 * Derived class for a book
 * @version 0.1
 */
public class Book extends Item {
    public Book(){
    }
    public Book(String genre, String format, int year, String title, List<String> authors, String publisher, String isbn) {
        super(genre, format, year, title);
        if(publisher == null || isbn == null) throw new IllegalArgumentException("Publisher and ISBN should not be null");
        this.authors = authors;
        this.publisher = publisher;
        this.isbn = isbn;
    }
    /**
     * authors of the book
     */
    private List<String> authors;
    /**
     * publishers of the book
     */
    private String publisher;
    /**
     * ISBN of the book
     */
    private String isbn;

    public List<String> getAuthors() {
        return authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public boolean equals(Object o) {
        if(!super.equals(o)) return false;
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        if (!super.equals(o)) return false;
        Book book = (Book) o;
        return getAuthors().equals(book.getAuthors()) &&
                getPublisher().equals(book.getPublisher()) &&
                getIsbn().equals(book.getIsbn());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getAuthors(), getPublisher(), getIsbn());
    }
}
