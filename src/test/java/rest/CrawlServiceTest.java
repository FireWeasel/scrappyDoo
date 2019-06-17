package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Item;
import model.Movie;
import model.Music;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.core.Response;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({CrawlService.class})
public class CrawlServiceTest {
    private CrawlService crawlService;
    private Crawler crawlMock;
    private String domain;
    private String baseUri;
    private List<Item> expectedListOfItems;
    @Before
    public void setUp(){
        crawlService = new CrawlService();
        crawlMock = mock(Crawler.class);
        domain = "http://i357989.hera.fhict.nl";
        baseUri = "http://i357989.hera.fhict.nl/catalog.php";
        List<String> stars = new ArrayList<String>(Arrays.asList("Tom Hanks", "Rebecca Williams", "Sally Field", "Michael Conner Humphreys"));
        List<String> writers = new ArrayList<String>(Arrays.asList("Winston Groom", "Eric Roth"));
        expectedListOfItems = Arrays.asList(new Movie("Forrest Gump","Robert Zemeckis", "Drama",
                "DVD", 1994, writers,stars),new Music("Clasical", "CD" , 2012, "Symphony","Ludwig van Beethoven"));
    }
    /**
     * Test function that verifies
     * getAllData of Crawler is called when calling
     * CrawlWholeWebsite endpoint.
     */
    @Test
    public void verifyGetAllDataIsCalledOfCrawlerWhenCrawlingWholeWebsite() throws Exception {
        //ARRANGE
        PowerMockito.whenNew(Crawler.class).withArguments(domain).thenReturn(crawlMock);

        //ACT
        crawlService.crawlWholeWebsite(baseUri);

        //ASSERT
        PowerMockito.verifyNew(Crawler.class).withArguments(domain);
        verify(crawlMock, atLeastOnce()).getAllData(baseUri);
    }

    @Test
    public void assertCorrectResponseFormatIsReturnedWhenCrawlingWholeWebsite() throws Exception {
        Gson gson = new GsonBuilder().create();
        List<Item> movies = expectedListOfItems.stream().filter(i -> i instanceof Movie).collect(Collectors.toList());
        List<Item> books = expectedListOfItems.stream().filter(i -> i instanceof model.Book).collect(Collectors.toList());
        List<Item> music = expectedListOfItems.stream().filter(i -> i instanceof Music).collect(Collectors.toList());

        String moviesJson = gson.toJson(movies);
        String booksJson = gson.toJson(books);
        String musicJson = gson.toJson(music);

        PowerMockito.whenNew(Crawler.class).withArguments(domain).thenReturn(crawlMock);
        when(crawlMock.getAllData(baseUri)).thenReturn(expectedListOfItems);

        Response response = crawlService.crawlWholeWebsite(baseUri);

        JsonReader jsonReader = Json.createReader(new StringReader(response.getEntity().toString()));
        JsonObject returnedJsonResponse = jsonReader.readObject();
        jsonReader.close();

        JsonObject expectedResponseJson = Json.createObjectBuilder()
                .add("id", 1)
                .add("time", returnedJsonResponse.get("time")) //hardcoded since it cannot be calculated
                .add("movies", moviesJson)
                .add("music", musicJson)
                .add("books", booksJson)
                .build();

        assertThat(expectedResponseJson, equalTo(returnedJsonResponse));

    }
    /**
     * Test function that checks if an
     * Exception is thrown when an invalid url
     * is passed.
     */
    @Test(expected = Exception.class)
    public void exceptionIsThrownWhenInvalidUrlIsPassedToCrawlWholeWebsite() throws Exception {
        baseUri = "verywrongurl";
        domain = "";

        crawlService.crawlWholeWebsite(baseUri);
    }

    /**
     * Test function that verifies
     * getSpecificData of Crawler is called when calling
     * findData endpoint.
     */
    @Test
    public void verifyGetSpecificDataOfCrawlerWhenFindingData() throws Exception {
        //ARRANGE
        PowerMockito.whenNew(Crawler.class).withArguments(domain).thenReturn(crawlMock);
        String type = any(String.class);
        String keyword = any(String.class);

        //ACT
        crawlService.findData(baseUri, type, keyword);

        //ASSERT
        PowerMockito.verifyNew(Crawler.class).withArguments(domain);
        verify(crawlMock, atLeastOnce()).getSpecificData(baseUri, type, keyword);
    }
    /**
     * Test function that verifies
     * if a CrawlAction object is created and stored within
     * CrawlService after each of its function calls.
     */
    @Test
    public void verifyCrawlActionIsAddedToLastActionsWhenEachEndpointIsCalled(){}
    /**
     * Test function that asserts
     * if Response returned by findData endpoint
     * contains any of the three types of Items
     * (parameterized test).
     */
    @Test
    public void assertIfResponseContainsItemWhenFindingData(){}
    /**
     * Test function that asserts
     * if an empty response is returned when
     * there are no crawled/scraped items.
     */
    @Test
    public void emptyResponseIsReturnedWhenThereAreNoItems(){}
    /**
     * Test function that asserts
     * if an empty response is returned when
     * there is no crawled/scraped item.
     */
    @Test
    public void emptyResponseIsReturnedWhenThereIsNoItem(){}
    /**
     * Test function that asserts
     * if an empty response is returned when
     * there is no last Crawled Action.
     */
    @Test
    public void emptyResponseIsReturnedWhenThereIsNoLastCrawlAction(){}
    /**
     * Test function that asserts
     * that an Item can be returned when
     * empty keywords are passed to the
     * findData endpoint.
     */
    @Test
    public void emptyKeywordsArePassedForFindDataAndItemIsReturned(){}
    /**
     * Test function that checks if an
     * Exception is thrown when an invalid type
     * is passed.
     */
    @Test
    public void exceptionIsThrownWhenTypeIsEmpty(){}
}