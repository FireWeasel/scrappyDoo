package rest;

import model.Book;
import model.Item;
import model.Movie;
import model.Music;
import org.jsoup.Jsoup;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.verification.VerificationMode;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Crawler.class})
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
    public void verifyScraperObjectIsBeingCreatedWhenGetAllDataIsCalled() throws Exception {
        Crawler crawler = new Crawler();
        Scraper scraper = mock(Scraper.class);

        PowerMockito.whenNew(Scraper.class).withAnyArguments().thenReturn(scraper);
        crawler.getAllData("");

        PowerMockito.verifyNew(Scraper.class).withNoArguments();
    }

    /**
     * Checks if the return type of the functions is of type List<Item> when GetAllData() is being called.
     */
    @Test
    public void assertIfReturnTypeIsListOfItemsWhenGetAllDataIsCalled() {
        Crawler crawler = new Crawler();

        List<Item> li = crawler.getAllData("");

        Assert.assertTrue(li instanceof ArrayList);
    }

    /**
     * Verifies if an item is being added to the list which is going to be returned when
     * item is found in the website when GetAllDataIsCalled().
     */
    @Test
    public void verifyItemIsBeingAddedToTheListOfAllFoundItemsWhenGetAllDataIsCalled() throws Exception {
        Crawler crawler = new Crawler();
        Scraper scraper = mock(Scraper.class);
        List<Item> items = new ArrayList<Item>();
        items.add(new Book());
        items.add(new Movie());
        when(scraper.scrapeData("")).thenReturn(items);
        PowerMockito.whenNew(Scraper.class).withAnyArguments().thenReturn(scraper);

        List<Item> actualResult = crawler.getAllData("");

        Assert.assertEquals(items, actualResult);
    }

    /**
     * Verifies if an empty list of type List<Item> has been returned if no matches were found
     * when GetAllData() is being called.
     */
    @Test
    public void verifyIfAnEmptyListOfItemsIsReturnedWhenNoItemsAreFoundWhenGetAllDataIsCalled() throws Exception {
        Crawler crawler = new Crawler();
        Scraper scraper = mock(Scraper.class);
        List<Item> items = new ArrayList<Item>();
        when(scraper.scrapeData("")).thenReturn(new ArrayList<Item>());
        PowerMockito.whenNew(Scraper.class).withAnyArguments().thenReturn(scraper);

        List<Item> actualResult = crawler.getAllData("");

        Assert.assertEquals(items, actualResult);
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
    public void verifyIfExceptionIsThrownFromScraperCrawlingIsNotInterruptedWhenGetAllDataIsCalled() throws Exception {
        Crawler crawler = new Crawler();
        Scraper scraper = mock(Scraper.class);
        when(scraper.scrapeData("")).thenThrow(IllegalArgumentException.class);
        PowerMockito.whenNew(Scraper.class).withAnyArguments().thenReturn(scraper);

        List<Item> actualResult = crawler.getAllData("");

        Assert.assertEquals(new ArrayList<Item>(), actualResult);
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
    public void verifyScraperObjectIsBeingCreatedWhenGetSpecificDataIsCalled() throws Exception {
        Crawler crawler = new Crawler();
        Scraper scraper = mock(Scraper.class);

        PowerMockito.whenNew(Scraper.class).withAnyArguments().thenReturn(scraper);
        crawler.getSpecificData(null, null);

        PowerMockito.verifyNew(Scraper.class).withNoArguments();
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
    public void assertIfReturnTypeIsOfTypeItemWhenGetSpecificDataIsCalledAndItemsHaveBeenFound() throws Exception {
        Crawler crawler = new Crawler();
        Scraper scraper = mock(Scraper.class);
        List<Item> items = new ArrayList<Item>();
        items.add(new Book());
        items.add(new Music());
        items.add(new Book());
        items.add(new Movie());
        when(scraper.scrapeData("")).thenReturn(items);
        PowerMockito.whenNew(Scraper.class).withAnyArguments().thenReturn(scraper);

        Item actualResult = crawler.getSpecificData("", "");

        Assert.assertEquals(items.get(0), actualResult);
    }

    /**
     * Checks if the function return null when no items have been found when GetSpecificData()
     * is being called.
     */
    @Test
    public void assertIfReturnTypeIsNullWhenGetSpecificDataIsCalledAndNoItemsAreFound() throws Exception {
        Crawler crawler = new Crawler();
        Scraper scraper = mock(Scraper.class);
        List<Item> items = new ArrayList<Item>();
        when(scraper.scrapeData("")).thenReturn(new ArrayList<Item>());
        PowerMockito.whenNew(Scraper.class).withAnyArguments().thenReturn(scraper);

        Item actualResult = crawler.getSpecificData("", "");

        Assert.assertEquals(null, actualResult);
    }

    /**
     * Test if an exception is thrown when the base url passed to the crawler is not found or broken
     * when GetAllData() is being called.
     */
    @Test(expected = Error.class)
    public void exceptionIsThrownWhenTheBaseUrlIsNotFoundWhenGetAllDataIsCalled() throws Exception {
        Crawler crawler = new Crawler();
        Scraper scraper = mock(Scraper.class);
        when(scraper.getPage("invalid.url")).thenThrow(Error.class);
        PowerMockito.whenNew(Scraper.class).withAnyArguments().thenReturn(scraper);

        crawler.getAllData("invalid.url");
    }

    /**
     * Test if an exception is thrown when the base url passed to the crawler is not found or broken
     * when GetSpecificData() is being called.
     */
    @Test
    public void exceptionIsThrownWhenTheBaseUrlIsNotFoundWhenGetSpecificDataIsCalled() {

    }
}
