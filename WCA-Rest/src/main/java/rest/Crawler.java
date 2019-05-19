package rest;

import java.util.ArrayList;

/**
 * Crawler for scraping data from a webpage
 */
public class Crawler {
    /**
     * Domain to crawl
     */
    public String domain;

    /**
     * Array of previously visited links
     */
    public ArrayList<String> visitedLinks;

    /**
     * How deep should it crawl for within a website
     */
    public Integer depth;

    /**
     * Base url of the crawled website
     */
    public String baseUrl;
    /**
     * Get a page's content
     * @param url - page's url
     * @return page
     */
    public String getPage(String url) {
        return "page";
    }

    /**
     * Scrape data form a html page
     * @param htmlContent - html to scrape
     * @return list of scraped items
     */
    public List<Item> scrapeData(String htmlContent) {
        return new ArrayList<>();
    }

    /**
     * Get all data from one crawled webpage
     * @return a list of all scraped data
     */
    public List<Item> getAllData() {
        return new ArrayList<>();
    }

    /**
     * Get only certain data from one crawled webpage
     * @param type - type of data
     * @param keywords - words to search for
     * @return list of filtered scraped data
     */
    public Item getSpecificData(String type, ArrayList<String> keywords) {
        return new Book();
    }
}
