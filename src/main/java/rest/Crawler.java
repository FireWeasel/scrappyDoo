package rest;

import model.Book;
import model.Item;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Crawler for scraping data from a webpage
 * @version 0.1
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
     * The depth of crawling the crawler reached
     */
    public Integer depth;

    /**
     * Object used to get the data from one web page
     */
    public Scraper scraper;

    public Crawler() {
        visitedLinks = new ArrayList<String>();
        depth = 0;
    }

    public Crawler(String domain) {
        this.domain = domain;
        visitedLinks = new ArrayList<String>();
        depth = 0;
    }

    /**
     * Get all data from one crawled webpage
     * @return a list of all scraped data
     */
    public List<Item> getAllData(String baseUrl) {
        List<Item> items = new ArrayList<Item>();
        Scraper scraper = new Scraper();
        Document doc = null;
        try {
            if(baseUrl.contains(domain)) {
                doc = Jsoup.connect(baseUrl).get();
                if (doc != null) {
                    ArrayList<String> links = scraper.getAllLinks(doc);
                    visitedLinks.add(baseUrl);
                    List<Item> scrapedItems = scraper.scrapeData(doc.outerHtml());
                    for (Item scrapedItem : scrapedItems) {
                        items.add(scrapedItem);
                    }
                    for (String link : links) {
                        if (link.contains(domain) && !visitedLinks.contains(link)) {
                            List<Item> itemsToAppend = getAllData(link);
                            for (Item i : itemsToAppend) {
                                items.add(i);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }

    /**
     * Get only certain data from one crawled webpage
     * @param type - type of data
     * @param keyword - filter by which to look for a specific item
     * @return single item of filtered scraped data
     */
    public Item getSpecificData(String baseUrl, String type, String keyword) {
        Item item = null;
        Scraper s = new Scraper();
        try {
            Jsoup.connect("http://google.com").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        s.getPage(baseUrl);
        List<Item> items = s.scrapeData("");
        if(!items.isEmpty()) {
            item = items.get(0);
        }
        return item;
    }
}
