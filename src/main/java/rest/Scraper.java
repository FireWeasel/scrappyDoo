package rest;

import model.Item;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
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
     * Default constructor
     */
    public Scraper() {}

    /**
     * Constructor for testing purposes
     * @param parser - mock parser
     */
    public Scraper(Parser parser) {
        this.parser = parser;
    }

    /**
     * Get a page's content
     * @param url - page's url
     * @return html content of page
     */
    public Document getPage(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get all links in one webpage
     * @param page - the webpage
     * @return array of links
     */
    public ArrayList<String> getAllLinks(Document page) {
        Elements elements = page.select("a[href]");
        ArrayList<String> returnList = new ArrayList<>();
        for (Element element : elements) {
            returnList.add(element.attr("href"));
        }
        return returnList;
    }

    /**
     * Scrape data form a html page
     * @param htmlContent - html to scrape
     * @return list of scraped items
     */
    public List<Item> scrapeData(Document htmlContent) {
        ArrayList<Item> list = new ArrayList<>();
        Elements elements = htmlContent.select("div.div-media-details");
        if (elements.size() == 0) {
            return null;
        }
        Item parsed = parser.parse(elements);
        list.add(parsed);
        return list;
    }
}
