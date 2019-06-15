package rest;

import model.*;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;

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
        return null;
    }

    /**
     * Parse one book
     * @param book - string to be parsed
     * @return parsed book
     */
    public Book parseBook(HashMap<String, String> book) {
        return null;
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
