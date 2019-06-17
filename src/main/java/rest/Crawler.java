package rest;

import model.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Crawler for scraping data from a webpage
 * @version 0.1
 */
public class Crawler {
    public Crawler(String domain){}
    /**
     * Domain to crawl
     */
    public String domain;

    public ArrayList<String> getVisitedLinks() {
        return visitedLinks;
    }

    public Integer getDepth() {
        return depth;
    }

    /**
     * Array of previously visited links
     */
    public ArrayList<String> visitedLinks = new ArrayList<>();

    /**
     * The depth of crawling the crawler reached
     */
    public Integer depth;

    /**
     * Object used to get the data from one web page
     */
    public Scraper scraper;

    /**
     * Get all data from one crawled webpage
     * @return a list of all scraped data
     */
    public List<Item> getAllData(String baseUri) {
        return new ArrayList<>();
    }

    /**
     * Get only certain data from one crawled webpage
     * @param type - type of data
     * @param keyword - filter by which to look for a specific item
     * @return single item of filtered scraped data
     */
    public Item getSpecificData(String baseUri, String type, String keyword) {
        return null;
    }
}
