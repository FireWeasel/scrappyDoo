package rest;

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
import java.awt.print.Book;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
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
    @Before
    public void setUp(){
        crawlService = new CrawlService();
        crawlMock = mock(Crawler.class);
        domain = "http://i357989.hera.fhict.nl";
        baseUri = "http://i357989.hera.fhict.nl/catalog.php";
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
        List<String> stars = new ArrayList<String>(Arrays.asList("Tom Hanks", "Rebecca Williams", "Sally Field", "Michael Conner Humphreys"));
        List<String> writers = new ArrayList<String>(Arrays.asList("Winston Groom", "Eric Roth"));
        List<Item> expectedListOfItems = Arrays.asList(new Movie("Forrest Gump","Robert Zemeckis", "Drama",
                "DVD", 1994, writers,stars),new Music("Clasical", "CD" , 2012, "Symphony","Ludwig van Beethoven"));

        JsonObject expectedResponseJson = Json.createObjectBuilder()
                .add("id", 0)
                .add("time", "doesn't matter")
                .add("movies", expectedListOfItems.stream().filter(i -> i.getClass().equals(Movie.class)).map(e -> e.toString()).reduce("", String::concat))
                .add("music", expectedListOfItems.stream().filter(i -> i.getClass().equals(Music.class)).map(e -> e.toString()).reduce("", String::concat))
                .add("books", expectedListOfItems.stream().filter(i -> i.getClass().equals(Book.class)).map(e -> e.toString()).reduce("", String::concat))
                .build();
        PowerMockito.whenNew(Crawler.class).withArguments(domain).thenReturn(crawlMock);
        when(crawlMock.getAllData(baseUri)).thenReturn(expectedListOfItems);

        Response response = crawlService.crawlWholeWebsite(baseUri);
        JsonReader jsonReader = Json.createReader(new StringReader(response.getEntity().toString()));
        JsonObject returnedJsonResponse = jsonReader.readObject();
        jsonReader.close();

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
    public void verifyGetSpecificDataOfCrawlerWhenFindingData(){}
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