package rest;

import org.junit.Test;

import static org.junit.Assert.*;

public class CrawlServiceTest {
    /**
     * Test function that verifies
     * getAllData of Crawler is called when calling
     * CrawlWholeWebsite endpoint.
     */
    @Test
    public void verifyGetAllDataIsCalledOfCrawlerWhenCrawlingWholeWebsite(){}

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