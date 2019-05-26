package rest;

import model.Item;

import javax.swing.text.Document;
import java.util.ArrayList;
import java.util.List;

public class Scraper {
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
    public List<Item> scrapeData(String htmlContent) {
        return new ArrayList<>();
    }
}
