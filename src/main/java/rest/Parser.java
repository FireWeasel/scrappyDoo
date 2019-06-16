package rest;

import model.Book;
import model.Item;
import model.Movie;
import model.Music;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
    public Item parse(Elements elements) throws Exception {
        if(elements != null) {
            String type = "";
            HashMap<String, String> item = new HashMap<>();
            Item itemToReturn = null;


            if (elements.size() > 0) {
                for (Element element : elements.select("table").select("tbody").select("tr")) {
                    if (element.select("th").text().equals("Category")) {
                        type = element.select("td").text();
                    }
                    item.put(element.select("th").text(), element.select("td").text());
                }

                switch (type) {
                    case "Book":
                        itemToReturn = parseBook(item);
                        break;
                    case "Movie":
                        itemToReturn = parseMovie(item);
                        break;
                    case "Music":
                        itemToReturn = parseMusic(item);
                        break;
                    default:
                        break;
                }
            }
            return itemToReturn;
        }
        else throw new IllegalArgumentException("Elements must not be null!");
    }

    /**
     * Parse one movie
     * @param movie - string to be parsed
     * @return parsed movie
     */
    public Movie parseMovie(HashMap<String, String> movie) throws Exception {
        if(movie.get("Category").equals("Movie")) {
            String genre = movie.get("Genre");
            String format = movie.get("Format");
            String title = movie.get("Title");
            int year = Integer.valueOf(movie.get("Year"));
            String director = movie.get("Director");
            List<String> writers = Arrays.stream(movie.get("Writers").split(",")).map(String::trim).collect(Collectors.toList());
            List<String> stars = Arrays.stream(movie.get("Stars").split(",")).map(String::trim).collect(Collectors.toList());

            Movie returnMovie = new Movie(title, director, genre, format, year, writers, stars);
            return returnMovie;
        }
        else throw new Exception("This hashmap does not contain a Movie item!");
    }

    /**
     * Parse one book
     * @param book - string to be parsed
     * @return parsed book
     */
    public Book parseBook(HashMap<String, String> book) throws Exception {
        if(book.get("Category").equals("Book")) {
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
        else throw new Exception("This hashmap does not contain a Book item");
    }

    /**
     * Parse one music object
     * @param music - string to be parsed
     * @return parsed music
     */
    public Music parseMusic(HashMap<String, String> music) throws Exception {
        if(music.get("Category").equals("Music")) {
            String title = music.get("Title");
            String format = music.get("Format");
            String genre = music.get("Genre");
            int year = Integer.valueOf(music.get("Year"));
            String artist = music.get("Artist");

            Music returnMusic = new Music(genre, format, year, title, artist);
            return returnMusic;
        }
        else throw new Exception("This hashmap does not contain a Music item");
    }
}
