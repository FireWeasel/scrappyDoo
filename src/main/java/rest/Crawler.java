package rest;

import model.Book;
import model.Item;
import model.Movie;
import model.Music;
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
        if (!baseUrl.contains("http://")) {
            throw new Error();
        }
        List<Item> items = new ArrayList<Item>();
        Scraper scraper = new Scraper();
        Document doc = null;
        try {
            if(baseUrl.contains(domain)) {
                doc = Jsoup.connect(baseUrl).get();
                if (doc != null) {
                    ArrayList<String> links = scraper.getAllLinks(doc);
                    visitedLinks.add(baseUrl);
                    List<Item> scrapedItems = scraper.scrapeData(doc);
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
        if (!baseUrl.contains("http://")) {
            throw new Error();
        }
        Scraper scraper = new Scraper();
        Document doc = null;
        try {
            if(baseUrl.contains(domain)) {
                doc = Jsoup.connect(baseUrl).get();
                if (doc != null) {
                    ArrayList<String> links = scraper.getAllLinks(doc);
                    visitedLinks.add(baseUrl);
                    List<Item> scrapedItems = scraper.scrapeData(doc);
                    for (Item scrapedItem : scrapedItems) {
                        if(scrapedItem instanceof Book) {
                            if(type != null) {
                                if(keyword != null) {
                                    if (type.toLowerCase().equals("book")) {
                                        if (((Book) scrapedItem).Genre.contains(keyword) || ((Book) scrapedItem).getPublisher().contains(keyword)
                                                || ((Book) scrapedItem).getAuthors().contains(keyword)) {
                                            return scrapedItem;
                                        }
                                    }
                                } else {
                                    if (type.toLowerCase().equals("book")) {
                                        return scrapedItem;
                                    }
                                }
                            } else if(keyword != null) {
                                if (((Book) scrapedItem).Genre.contains(keyword) || ((Book) scrapedItem).getPublisher().contains(keyword)
                                        || ((Book) scrapedItem).getAuthors().contains(keyword)) {
                                    return scrapedItem;
                                }
                            } else {
                                return scrapedItem;
                            }
                        } else if(scrapedItem instanceof Music) {
                            if(type != null) {
                                if(keyword != null) {
                                    if (type.toLowerCase().equals("music")) {
                                        if (((Music) scrapedItem).Genre.contains(keyword) || ((Music) scrapedItem).getArtist().contains(keyword)) {
                                            return scrapedItem;
                                        }
                                    }
                                } else {
                                    if (type.toLowerCase().equals("music")) {
                                        return scrapedItem;
                                    }
                                }
                            } else if(keyword != null) {
                                if (((Music) scrapedItem).Genre.contains(keyword) || ((Music) scrapedItem).getArtist().contains(keyword)) {
                                    return scrapedItem;
                                }
                            } else {
                                return scrapedItem;
                            }
                        } else if (scrapedItem instanceof Movie) {
                            if(type != null) {
                                if(keyword != null) {
                                    if (type.toLowerCase().equals("movie")) {
                                        if (((Movie) scrapedItem).Genre.contains(keyword) || ((Movie) scrapedItem).getDirector().contains(keyword)
                                                || ((Movie) scrapedItem).getWriters().contains(keyword)) {
                                            return scrapedItem;
                                        }
                                    }
                                } else {
                                    if (type.toLowerCase().equals("movie")) {
                                        return scrapedItem;
                                    }
                                }
                            } else if(keyword != null) {
                                if (((Movie) scrapedItem).Genre.contains(keyword) || ((Movie) scrapedItem).getDirector().contains(keyword)
                                        || ((Movie) scrapedItem).getWriters().contains(keyword)) {
                                    return scrapedItem;
                                }
                            } else {
                                return scrapedItem;
                            }
                        }
                    }
                    for (String link : links) {
                        if (link.contains(domain) && !visitedLinks.contains(link)) {
                            Item item = getSpecificData(link, type, keyword);
                            if(item != null) {
                                return item;
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
