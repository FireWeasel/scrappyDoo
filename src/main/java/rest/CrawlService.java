package rest;

import model.CrawlAction;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.Response;
import java.util.List;

/**
 * Crawler service for crawling a website, finding data and getting the last crawled action
 * @version 0.1
 */
@Singleton
@Path("/")
public class CrawlService {
    /**
     * Last crawl actions executed
     */
    public List<CrawlAction> lastActions;

    /**
     * Object used for crawling a website and scraping web pages
     */
    public Crawler crawler;

    public CrawlService(){}

    /**
     * Crawl an entire website
     * @param baseUrl website to crawl
     * @return a JSON response containing scraped data or empty string
     */
    @GET
    @Path("wholepage")
    @Produces(MediaType.APPLICATION_JSON)
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
    @GET
    @Path("finddata")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findData(String baseUrl, String type, String keyword) {
        return  null;
    }

    /**
     * Get the last crawled action
     * @return a JSON response containing scraped data or empty string
     */
    @GET
    @Path("lastaction")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLastCrawlingAction() {
        return null;
    }
}
