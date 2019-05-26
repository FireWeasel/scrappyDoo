package rest;

import model.Item;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Class meant for recursively finding items in one web page
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
     * Recursively scrape data form a html page
     * @param htmlContent - html to scrape
     * @return list of scraped items
     */
    public List<Item> scrapeData(String htmlContent) {
        return new ArrayList<>();
    }
}
