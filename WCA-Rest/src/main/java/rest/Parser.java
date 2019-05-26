package rest;

import model.Book;
import model.Item;
import model.Movie;
import model.Music;
import org.jsoup.nodes.Element;

/**
 * Class that handles parsing logic for CrawlerService.
 */
public class Parser {
    /**
     * Function that parses Element item of
     * jsoup library into an Item.
     * @param element - the element returned by the scraper
     * @return Item - instance of type Item
     */
    public Item parse(Element element){
        return null;
    }

    /**
     * Function that parses a string extracted
     * from Element in parse.
     * @param extractedItem - extracted movie string from the element
     * @return Movie - instance of type Item
     */
    public Movie parseMovie(String extractedItem){
        return null;
    }
    /**
     * Function that parses a string extracted
     * from Element in parse.
     * @param extractedItem - extracted music string from the element
     * @return Music - instance of type Item
     */
    public Music parseMusic(String extractedItem){
        return null;
    }
    /**
     * Function that parses a string extracted
     * from Element in parse.
     * @param extractedItem - extracted book string from the element
     * @return Book - instance of type Item
     */
    public Book parseBook(String extractedItem){
        return  null;
    }
}
