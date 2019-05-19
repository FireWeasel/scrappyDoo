package rest;

import model.CrawlAction;

import javax.xml.ws.Response;
import java.util.List;

/**
 * Crawler service for crawling a website, finding data and getting the last crawled action
 * @version 0.1
 */
public class CrawlService {
    /**
     * Last crawl actions executed
     */
    public List<CrawlAction> lastActions;

    /**
     * Object used for crawling a website and scraping web pages
     */
    public Crawler crawler;

    /**
     * Crawl an entire website
     * @param baseUrl website to crawl
     * @return a JSON response containing scraped data or empty string
     */
    public Response crawlWholeWebsite(String baseUrl) {
        return  null;
    }

    /**
     * Find data in website
     * @param baseUrl webiste to find data in
     * @param type - type of website
     * @param keyword - filter by which to look for a specific item
     * @return a JSON response containing scraped data or empty string
     */
    public Response findData(String baseUrl, String type, String keyword) {
        return  null;
    }

    /**
     * Get the last crawled action
     * @return a JSON response containing scraped data or empty string
     */
    public Response getLastCrawlingAction() {
        return null;
    }
}
