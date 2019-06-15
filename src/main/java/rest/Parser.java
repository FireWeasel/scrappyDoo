package rest;

import model.*;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Class used to transform scraped objects into objects inheriting from the Item interface
 * @version 0.1
 */
public class Parser {
    /**
     * Parse one element
     * @param elements - object to be parsed
     * @return parsed item
     */
    public Item parse(Elements elements) {
        return null;
    }

    /**
     * Parse one movie
     * @param movie - string to be parsed
     * @return parsed movie
     */
    public Movie parseMovie(HashMap<String, String> movie) {
        String genre = movie.get("Genre");
        String format = movie.get("Format");
        String title = movie.get("Title");
        int year = Integer.valueOf(movie.get("Year"));
        String director = movie.get("Director");
        List<String> writers = Arrays.stream(movie.get("Writers").split(",")).map(String::trim).collect(Collectors.toList());
        List<String> stars = Arrays.stream(movie.get("Stars").split(",")).map(String::trim).collect(Collectors.toList());

        Movie returnMovie = new Movie(title, director, genre, format,year, writers, stars);
        return returnMovie;
    }

    /**
     * Parse one book
     * @param book - string to be parsed
     * @return parsed book
     */
    public Book parseBook(HashMap<String, String> book) {
        String genre = book.get("Genre");
        String format = book.get("Format");
        String title = book.get("Title");
        int year = Integer.valueOf(book.get("Year"));
        String publisher = book.get("Publisher");
        String isbn = book.get("ISBN");
        List<String> authors = Arrays.stream(book.get("Authors").split(",")).map(String::trim).collect(Collectors.toList());

        Book returnBook = new Book(genre, format, year, title, authors, publisher, isbn);
        return returnBook;
    }

    /**
     * Parse one music object
     * @param music - string to be parsed
     * @return parsed music
     */
    public Music parseMusic(HashMap<String, String> music) {
        return null;
    }
}
