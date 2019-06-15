package rest;

import org.junit.Test;

public class CrawlerTest {
    /**
     * Verifies that a JSOUP object is being created when GetAllData() is being called.
     */
    @Test
    public void verifyJsoupObjectIsBeingCreatedWhenGetAllDataIsCalled() {

    }

    /**
     * Verifies that a Scraper object is being created when GetAllData() is being called.
     */
    @Test
    public void verifyScraperObjectIsBeingCreatedWhenGetAllDataIsCalled() {

    }

    /**
     * Checks if the return type of the functions is of type List<Item> when GetAllData() is being called.
     */
    @Test
    public void assertIfReturnTypeIsListOfItemsWhenGetAllDataIsCalled() {

    }

    /**
     * Verifies if an item is being added to the list which is going to be returned when
     * item is found in the website when GetAllDataIsCalled().
     */
    @Test
    public void verifyItemIsBeingAddedToTheListOfAllFoundItemsWhenGetAllDataIsCalled() {

    }

    /**
     * Verifies if an empty list of type List<Item> has been returned if no matches were found
     * when GetAllData() is being called.
     */
    @Test
    public void verifyIfAnEmptyListOfItemsIsReturnedWhenNoItemsAreFoundWhenGetAllDataIsCalled() {

    }

    /**
     * Verifies that an exception is not thrown when a link found in the website is broken
     * when GetAllData() is being called.
     */
    @Test
    public void verifyIfALinkFoundInTheWebsiteIsBrokenCrawlerDoesNotThrowExceptionWhenGetAllDataIsCalled() {

    }

    /**
     * Verifies that crawling a website is not being interrupted if an exception is thrown
     * from the Scraper when GetAllData() is being called.
     */
    @Test
    public void verifyIfExceptionIsThrownFromScraperCrawlingIsNotInterruptedWhenGetAllDataIsCalled() {

    }

    /**
     * Verifies that a JSOUP object is being created when GetSpecificData() is being called.
     */
    @Test
    public void verifyJsoupObjectIsBeingCreatedWhenGetSpecificDataIsCalled() {

    }

    /**
     * Verifies that a Scraper object is being created when GetSpecificData() is being called.
     */
    @Test
    public void verifyScraperObjectIsBeingCreatedWhenGetSpecificDataIsCalled() {

    }

    /**
     * Verifies that exception is not being thrown when GetSpecificData() is being called without
     * parameter for item type is not passed to the function.
     */
    @Test
    public void verifyExceptionIsNotThrownWhenGetSpecificDataIsCalledWithoutTypeParameter() {

    }

    /**
     * Verifies that exception is not being thrown when GetSpecificData() is being called without
     * parameter for item keyword is not passed to the function.
     */
    @Test
    public void verifyExceptionIsNotThrownWhenGetSpecificDataIsCalledWithoutKeywordsParameter() {

    }

    /**
     * Verifies that exception is not being thrown when GetSpecificData() is being called without
     * any parameters.
     */
    @Test
    public void verifyExceptionIsNotThrownWhenGetSpecificDataIsCalledWithoutAnyParameters() {

    }

    /**
     * Checks if the function returns Item object when an Item has been found when GetSpecificData()
     * is being called.
     */
    @Test
    public void assertIfReturnTypeIsOfTypeItemWhenGetSpecificDataIsCalledAndItemsHaveBeenFound() {

    }

    /**
     * Checks if the function return null when no items have been found when GetSpecificData()
     * is being called.
     */
    @Test
    public void assertIfReturnTypeIsNullWhenGetSpecificDataIsCalledAndNoItemsAreFound() {

    }

    /**
     * Test if an exception is thrown when the base url passed to the crawler is not found or broken
     * when GetAllData() is being called.
     */
    @Test
    public void exceptionIsThrownWhenTheBaseUrlIsNotFoundWhenGetAllDataIsCalled() {

    }

    /**
     * Test if an exception is thrown when the base url passed to the crawler is not found or broken
     * when GetSpecificData() is being called.
     */
    @Test
    public void exceptionIsThrownWhenTheBaseUrlIsNotFoundWhenGetSpecificDataIsCalled() {

    }
}
