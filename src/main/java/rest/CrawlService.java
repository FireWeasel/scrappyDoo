package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.*;

import javax.inject.Singleton;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Crawler service for crawling a website, finding data and getting the last crawled action
 * @version 0.1
 */
@Singleton
@Path("/")
public class CrawlService {
    private int id;
    public static int _id = 1;
    /**
     * Last crawl actions executed
     */
    public List<CrawlAction> lastActions = new ArrayList<>();

    /**
     * Object used for crawling a website and scraping web pages
     */
    public Crawler crawler;

    public CrawlService(){
        id +=_id;
    }

    /**
     * Crawl an entire website
     * @param baseUrl website to crawl
     * @return a JSON response containing scraped data or empty string
     */
    @GET
    @Path("wholepage")
    @Produces(MediaType.APPLICATION_JSON)
    public Response crawlWholeWebsite(String baseUrl) throws Exception {
        if(baseUrl.contains("http://")) {
            long start = System.nanoTime();
            String domain = baseUrl.split("nl/")[0];
            Crawler crawler = new Crawler(domain + "nl");
            List<Item> items = crawler.getAllData(baseUrl);

            long end = System.nanoTime();
            long duration = (end - start) / 1000000;

            Gson gson = new GsonBuilder().create();
            String moviesJson;
            String booksJson;
            String musicJson;
            if (items != null) {

                List<Item> movies = items.stream().filter(i -> i instanceof Movie).collect(Collectors.toList());
                List<Item> books = items.stream().filter(i -> i instanceof Book).collect(Collectors.toList());
                List<Item> music = items.stream().filter(i -> i instanceof Music).collect(Collectors.toList());

                moviesJson = gson.toJson(movies);
                booksJson = gson.toJson(books);
                musicJson = gson.toJson(music);
            } else {
                moviesJson = gson.toJson(new ArrayList<>());
                booksJson = gson.toJson(new ArrayList<>());
                musicJson = gson.toJson(new ArrayList<>());
            }
            List<String> deduplicated = new ArrayList<>();
            for (String link : crawler.getVisitedLinks()) {
                if (!deduplicated.contains(link)) {
                    deduplicated.add(link);
                }
            }
            lastActions.add(new CrawlAction(crawler.getVisitedLinks().size(), deduplicated.size(), duration, crawler.getDepth()));

            JsonObject expectedResponseJson = Json.createObjectBuilder()
                    .add("id", this.id)
                    .add("time", Long.toString(duration))
                    .add("movies", moviesJson)
                    .add("music", musicJson)
                    .add("books", booksJson)
                    .build();
            return Response.status(200).entity(expectedResponseJson).type(MediaType.APPLICATION_JSON).build();
        }
        else throw new Exception("Url needs to valid in order to crawl");
    }

    /**
     * Find data in website
     * @param baseUrl website to find data in
     * @param type - type of website
     * @param keyword - filter by which to look for a specific item
     * @return a JSON response containing scraped data or empty string
     */
    @GET
    @Path("finddata")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findData(String baseUrl, String type, String keyword) throws Exception {
        if (baseUrl.startsWith("http://")) {
            long start = System.nanoTime();
            String domain = baseUrl.split("nl/")[0];
            Crawler crawler = new Crawler(domain + "nl");
            Item item = crawler.getSpecificData(baseUrl, type, keyword);

            long end = System.nanoTime();
            long duration = (end - start) / 1000000;

            Gson gson = new GsonBuilder().create();
            String itemJson;
            if (item != null) {
                itemJson = gson.toJson(item);
            } else {
                itemJson = gson.toJson(new Object());
            }
            List<String> deduplicated = new ArrayList<>();
            for (String link : crawler.getVisitedLinks()) {
                if (!deduplicated.contains(link)) {
                    deduplicated.add(link);
                }
            }
            lastActions.add(new CrawlAction(crawler.getVisitedLinks().size(), deduplicated.size(), duration, crawler.getDepth()));

            JsonObject expectedResponseJson = Json.createObjectBuilder()
                    .add("id", this.id)
                    .add("time", Long.toString(duration))
                    .add("result", itemJson)
                    .build();
            return Response.status(200).entity(expectedResponseJson).type(MediaType.APPLICATION_JSON).build();
        }
        throw new Exception("Url needs to valid in order to crawl");
    }

    /**
     * Get the last crawled action
     * @return a JSON response containing scraped data or empty string
     */
    @GET
    @Path("lastaction")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLastCrawlingAction() {
        if (lastActions.size() == 0) {
            JsonObject jsonResponse = Json.createObjectBuilder().build();
            return Response.status(200).entity(jsonResponse).type(MediaType.APPLICATION_JSON).build();
        }
        int lastIndex = lastActions.size() - 1;
        CrawlAction last = lastActions.get(lastIndex);
        JsonObject jsonResponse = Json.createObjectBuilder()
                .add("id", this.id)
                .add("time_elapsed", Long.toString(last.timeElapsed))
                .add("pages_explored", last.nrOfPagesVisited)
                .add("unique_pages_explored", last.nrOfUniquePagesVisited)
                .add("search_depth", last.depth)
                .build();
        return Response.status(200).entity(jsonResponse).type(MediaType.APPLICATION_JSON).build();
    }
}
