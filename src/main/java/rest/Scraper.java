package rest;

import model.Item;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Scraper is the main class for handling Jsoup scraping logic.
 * It contains an instance of Parser class which is used for parsing html content into corresponding Item class.
 * @version 0.1
 */
public class Scraper {
    /**
     * Object to convert scraped data to objects
     */
    public Parser parser;

    /**
     * Get a page's content
     * @param url - page's url
     * @return html content of page
     */
    public Document getPage(String url) {
        return null;
    }

    /**
     * Scrape data form a html page
     * @param htmlContent - html to scrape
     * @return list of scraped items
     */
    public List<Item> scrapeData(Document htmlContent) {
        return new ArrayList<>();
    }
}
