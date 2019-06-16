package rest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mock;

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
     * Exception is thrown when an invalid url
     * is passed.
     */
    @Test
    public void exceptionIsThrownWhenInvalidUrlIsPassed(){}
    /**
     * Test function that checks if an
     * Exception is thrown when an invalid type
     * is passed.
     */
    @Test
    public void exceptionIsThrownWhenTypeIsEmpty(){}
}