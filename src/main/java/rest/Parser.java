package rest;

import model.*;
import org.jsoup.nodes.Element;

/**
 * Class used to transform scraped objects into objects inheriting from the Item interface
 * @version 0.1
 */
public class Parser {
    /**
     * Parse one element
     * @param element - object to be parsed
     * @return parsed item
     */
    public Item parse(Element element) {
        return null;
    }

    /**
     * Parse one movie
     * @param movie - string to be parsed
     * @return parsed movie
     */
    public Movie parseMovie(String movie) {
        return null;
    }

    /**
     * Parse one book
     * @param book - string to be parsed
     * @return parsed book
     */
    public Book parseBook(String book) {
        return null;
    }

    /**
     * Parse one music object
     * @param music - string to be parsed
     * @return parsed music
     */
    public Music parseMusic(String music) {
        return null;
    }
}
