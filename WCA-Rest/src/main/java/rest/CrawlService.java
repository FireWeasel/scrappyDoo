package rest;

import java.util.ArrayList;

/**
 * Crawler service for crawling a website, finding data and getting the last crawled action
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
     */
    public void crawlWholeWebsite(String baseUrl) {

    }

    /**
     * Find data in website
     * @param baseUrl webiste to find data in
     * @param type - type of website
     * @param keywords - words to look for
     */
    public void findData(String baseUrl, String type, ArrayList<String> keywords) {

    }

    /**
     * Get the last crawled action
     */
    public void getLastCrawlingAction() {

    }
}
