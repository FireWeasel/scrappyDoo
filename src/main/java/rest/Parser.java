package rest;

import model.*;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Movie returnMovie = new Movie();
        String genre = movie.get("Genre");
        String format = movie.get("Format");
        int year = Integer.valueOf(movie.get("Year"));
        String director = movie.get("Director");
        List<String> writers = new ArrayList();
        for(String writer: movie.get("Writers").split(",")){
            writers.add(writer.trim());
        }
        List<String> stars = new ArrayList<>();
        for(String star: movie.get("Stars").split(",")){
            stars.add(star.trim());
        }

        returnMovie.Director = director;
        returnMovie.Writers = writers;
        returnMovie.Year = year;
        returnMovie.Format = format;
        returnMovie.Genre = genre;
        returnMovie.Stars = stars;

        return returnMovie;
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
