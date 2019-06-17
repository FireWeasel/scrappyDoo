package rest;

import model.Book;
import model.Item;
import model.Movie;
import model.Music;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Crawler.class, Jsoup.class})
public class CrawlerTest {
    /**
     * Verifies that a JSOUP object is being created when GetAllData() is being called.
     */
    @Test
    public void verifyJsoupObjectIsBeingCreatedWhenGetAllDataIsCalled() throws Exception {
        Crawler crawler = new Crawler("google.com");
        Scraper scraper = mock(Scraper.class);
        List<Item> items = new ArrayList<Item>();
        Connection connection = mock(Connection.class);
        String url = "http://google.com";
        Document doc = new Document(url);
        when(scraper.scrapeData(doc)).thenReturn(items);
        PowerMockito.whenNew(Scraper.class).withAnyArguments().thenReturn(scraper);
        PowerMockito.mockStatic(Jsoup.class);
        when(connection.get()).thenReturn(new Document(url));
        when(Jsoup.connect(url)).thenReturn(connection);

        crawler.getAllData(url);

        verify(Jsoup.connect(url)).get();
    }

    /**
     * Verifies that a Scraper object is being created when GetAllData() is being called.
     */
    @Test
    public void verifyScraperObjectIsBeingCreatedWhenGetAllDataIsCalled() throws Exception {
        Crawler crawler = new Crawler("google.com");
        Scraper scraper = mock(Scraper.class);
        List<Item> items = new ArrayList<Item>();
        String url = "http://google.com";
        Document doc = new Document(url);
        when(scraper.scrapeData(doc)).thenReturn(items);
        PowerMockito.whenNew(Scraper.class).withAnyArguments().thenReturn(scraper);

        crawler.getAllData(url);

        PowerMockito.verifyNew(Scraper.class).withNoArguments();
    }

    /**
     * Checks if the return type of the functions is of type List<Item> when GetAllData() is being called.
     */
    @Test
    public void assertIfReturnTypeIsListOfItemsWhenGetAllDataIsCalled() throws Exception {
        Crawler crawler = new Crawler("google.com");
        Scraper scraper = mock(Scraper.class);
        List<Item> items = new ArrayList<Item>();
        String url = "http://google.com";
        Document doc = new Document(url);
        when(scraper.scrapeData(doc)).thenReturn(items);
        PowerMockito.whenNew(Scraper.class).withAnyArguments().thenReturn(scraper);

        List<Item> li = crawler.getAllData(url);

        Assert.assertTrue(li instanceof ArrayList);
    }

    /**
     * Verifies if an item is being added to the list which is going to be returned when
     * item is found in the website when GetAllDataIsCalled().
     */
    @Test
    public void verifyItemIsBeingAddedToTheListOfAllFoundItemsWhenGetAllDataIsCalled() throws Exception {
        Crawler crawler = new Crawler("google.com");
        Scraper scraper = mock(Scraper.class);
        List<Item> items = new ArrayList<Item>();
        ArrayList<String> links = new ArrayList<String>();
        String url = "http://google.com";
        Document doc = new Document(url);
        items.add(new Book());
        items.add(new Movie());
        PowerMockito.mockStatic(Jsoup.class);
        Connection connection = mock(Connection.class);
        when(connection.get()).thenReturn(doc);
        when(Jsoup.connect(url)).thenReturn(connection);
        when(scraper.getAllLinks(doc)).thenReturn(links);
        when(scraper.scrapeData(doc)).thenReturn(items);
        PowerMockito.whenNew(Scraper.class).withAnyArguments().thenReturn(scraper);

        List<Item> actualResult = crawler.getAllData(url);

        Assert.assertEquals(items, actualResult);
    }

    /**
     * Verifies if a link does not belong to the domain it does not craw and scrape it.
     */
    @Test
    public void verifyItDoesNotCollectAnythingWhenLinkDoesNotBelongToDomainWhenGetAllDataIsCalled() throws Exception {
        Crawler crawler = new Crawler("baidu.com");
        Scraper scraper = mock(Scraper.class);
        List<Item> items = new ArrayList<Item>();
        ArrayList<String> links = new ArrayList<String>();
        String url = "http://google.com";
        Document doc = new Document(url);
        items.add(new Book());
        items.add(new Movie());
        PowerMockito.mockStatic(Jsoup.class);
        Connection connection = mock(Connection.class);
        when(connection.get()).thenReturn(doc);
        when(Jsoup.connect(url)).thenReturn(connection);
        when(scraper.getAllLinks(doc)).thenReturn(links);
        when(scraper.scrapeData(doc)).thenReturn(items);
        PowerMockito.whenNew(Scraper.class).withAnyArguments().thenReturn(scraper);

        List<Item> actualResult = crawler.getAllData(url);

        Assert.assertEquals(new ArrayList<Item>(), actualResult);
    }

    /**
     * Verifies if an empty list of type List<Item> has been returned if no matches were found
     * when GetAllData() is being called.
     */
    @Test
    public void verifyIfAnEmptyListOfItemsIsReturnedWhenNoItemsAreFoundWhenGetAllDataIsCalled() throws Exception {
        Crawler crawler = new Crawler("google.com");
        Scraper scraper = mock(Scraper.class);
        List<Item> items = new ArrayList<Item>();
        String url = "http://google.com";
        Document doc = new Document(url);
        when(scraper.scrapeData(doc)).thenReturn(new ArrayList<Item>());
        PowerMockito.whenNew(Scraper.class).withAnyArguments().thenReturn(scraper);

        List<Item> actualResult = crawler.getAllData(url);

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
        Crawler crawler = new Crawler("google.com");
        Scraper scraper = mock(Scraper.class);
        String url = "http://google.com";
        Document doc = new Document(url);
        when(scraper.scrapeData(doc)).thenThrow(IllegalArgumentException.class);
        PowerMockito.whenNew(Scraper.class).withAnyArguments().thenReturn(scraper);

        List<Item> actualResult = crawler.getAllData(url);

        Assert.assertEquals(new ArrayList<Item>(), new ArrayList<Item>());
    }

    /**
     * Verifies that a JSOUP object is being created when GetSpecificData() is being called.
     */
    @Test
    public void verifyJsoupObjectIsBeingCreatedWhenGetSpecificDataIsCalled() throws Exception {
        Crawler crawler = new Crawler("google.com");
        Scraper scraper = mock(Scraper.class);
        List<Item> items = new ArrayList<Item>();
        ArrayList<String> links = new ArrayList<String>();
        String url = "http://google.com";
        Document doc = new Document(url);
        items.add(new Book());
        items.add(new Movie());
        PowerMockito.mockStatic(Jsoup.class);
        Connection connection = mock(Connection.class);
        when(connection.get()).thenReturn(doc);
        when(Jsoup.connect(url)).thenReturn(connection);
        when(scraper.getAllLinks(doc)).thenReturn(links);
        when(scraper.scrapeData(doc)).thenReturn(items);
        PowerMockito.whenNew(Scraper.class).withAnyArguments().thenReturn(scraper);

        crawler.getSpecificData(url, null, null);

        verify(Jsoup.connect(url)).get();
    }

    /**
     * Verifies that a Scraper object is being created when GetSpecificData() is being called.
     */
    @Test
    public void verifyScraperObjectIsBeingCreatedWhenGetSpecificDataIsCalled() throws Exception {
        Crawler crawler = new Crawler("google.com");
        Scraper scraper = mock(Scraper.class);
        List<Item> items = new ArrayList<Item>();
        ArrayList<String> links = new ArrayList<String>();
        String url = "http://google.com";
        Document doc = new Document(url);
        items.add(new Book());
        items.add(new Movie());
        PowerMockito.mockStatic(Jsoup.class);
        Connection connection = mock(Connection.class);
        when(connection.get()).thenReturn(doc);
        when(Jsoup.connect(url)).thenReturn(connection);
        when(scraper.getAllLinks(doc)).thenReturn(links);
        when(scraper.scrapeData(doc)).thenReturn(items);
        PowerMockito.whenNew(Scraper.class).withAnyArguments().thenReturn(scraper);

        crawler.getSpecificData(url, "book", null);

        PowerMockito.verifyNew(Scraper.class).withNoArguments();
    }

    /**
     * Verifies that exception is not being thrown when GetSpecificData() is being called without
     * parameter for item type is not passed to the function.
     */
    @Test
    public void verifyExceptionIsNotThrownWhenGetSpecificDataIsCalledWithoutTypeParameter() throws Exception {
        Crawler crawler = new Crawler("google.com");
        Scraper scraper = mock(Scraper.class);
        List<Item> items = new ArrayList<Item>();
        items.add(new Book("genre1", "format1", 1994, "author1", "publisher1", "isbn"));
        items.add(new Music());
        ArrayList<String> links = new ArrayList<String>();
        String url = "http://google.com";
        Document doc = new Document(url);
        PowerMockito.mockStatic(Jsoup.class);
        Connection connection = mock(Connection.class);
        when(connection.get()).thenReturn(doc);
        when(Jsoup.connect(url)).thenReturn(connection);
        when(scraper.getAllLinks(doc)).thenReturn(links);
        when(scraper.scrapeData(doc)).thenReturn(items);
        PowerMockito.whenNew(Scraper.class).withAnyArguments().thenReturn(scraper);

        Item i = crawler.getSpecificData(url, null, "author1");

        Assert.assertEquals(items.get(0), i);
    }

    /**
     * Verifies that exception is not being thrown when GetSpecificData() is being called without
     * parameter for item keyword is not passed to the function.
     */
    @Test
    public void verifyExceptionIsNotThrownWhenGetSpecificDataIsCalledWithoutKeywordsParameter() throws Exception {
        Crawler crawler = new Crawler("google.com");
        Scraper scraper = mock(Scraper.class);
        List<Item> items = new ArrayList<Item>();
        items.add(new Book());
        items.add(new Music());
        ArrayList<String> links = new ArrayList<String>();
        String url = "http://google.com";
        Document doc = new Document(url);
        PowerMockito.mockStatic(Jsoup.class);
        Connection connection = mock(Connection.class);
        when(connection.get()).thenReturn(doc);
        when(Jsoup.connect(url)).thenReturn(connection);
        when(scraper.getAllLinks(doc)).thenReturn(links);
        when(scraper.scrapeData(doc)).thenReturn(items);
        PowerMockito.whenNew(Scraper.class).withAnyArguments().thenReturn(scraper);

        Item i = crawler.getSpecificData(url, "music", null);

        Assert.assertEquals(items.get(1), i);
    }

    /**
     * Verifies that exception is not being thrown when GetSpecificData() is being called without
     * any parameters.
     */
    @Test
    public void verifyExceptionIsNotThrownWhenGetSpecificDataIsCalledWithoutAnyParameters() throws Exception {
        Crawler crawler = new Crawler("google.com");
        Scraper scraper = mock(Scraper.class);
        List<Item> items = new ArrayList<Item>();
        items.add(new Book());
        ArrayList<String> links = new ArrayList<String>();
        String url = "http://google.com";
        Document doc = new Document(url);
        PowerMockito.mockStatic(Jsoup.class);
        Connection connection = mock(Connection.class);
        when(connection.get()).thenReturn(doc);
        when(Jsoup.connect(url)).thenReturn(connection);
        when(scraper.getAllLinks(doc)).thenReturn(links);
        when(scraper.scrapeData(doc)).thenReturn(items);
        PowerMockito.whenNew(Scraper.class).withAnyArguments().thenReturn(scraper);

        Item i = crawler.getSpecificData(url, null, null);

        Assert.assertEquals(items.get(0), i);
    }

    /**
     * Checks if the function returns Item object when an Item has been found when GetSpecificData()
     * is being called.
     */
    @Test
    public void assertIfReturnTypeIsOfTypeItemWhenGetSpecificDataIsCalledAndItemsHaveBeenFound() throws Exception {
        Crawler crawler = new Crawler("google.com");
        Scraper scraper = mock(Scraper.class);
        List<Item> items = new ArrayList<Item>();
        ArrayList<String> links = new ArrayList<String>();
        items.add(new Book());
        items.add(new Music());
        items.add(new Book());
        String url = "http://google.com";
        Document doc = new Document(url);
        PowerMockito.mockStatic(Jsoup.class);
        Connection connection = mock(Connection.class);
        when(connection.get()).thenReturn(doc);
        when(Jsoup.connect(url)).thenReturn(connection);
        when(scraper.getAllLinks(doc)).thenReturn(links);
        when(scraper.scrapeData(doc)).thenReturn(items);
        PowerMockito.whenNew(Scraper.class).withAnyArguments().thenReturn(scraper);

        Item actualResult = crawler.getSpecificData(url, "music", null);

        Assert.assertEquals(items.get(1), actualResult);
    }

    /**
     * Checks if the function return null when no items have been found when GetSpecificData()
     * is being called.
     */
    @Test
    public void assertIfReturnTypeIsNullWhenGetSpecificDataIsCalledAndNoItemsAreFound() throws Exception {
        Crawler crawler = new Crawler("google.com");
        Scraper scraper = mock(Scraper.class);
        List<Item> items = new ArrayList<Item>();
        ArrayList<String> links = new ArrayList<String>();
        items.add(new Book());
        items.add(new Music());
        items.add(new Book());
        String url = "http://google.com";
        Document doc = new Document(url);
        PowerMockito.mockStatic(Jsoup.class);
        Connection connection = mock(Connection.class);
        when(connection.get()).thenReturn(doc);
        when(Jsoup.connect(url)).thenReturn(connection);
        when(scraper.getAllLinks(doc)).thenReturn(links);
        when(scraper.scrapeData(doc)).thenReturn(items);
        PowerMockito.whenNew(Scraper.class).withAnyArguments().thenReturn(scraper);

        Item actualResult = crawler.getSpecificData(url, "movie", null);

        Assert.assertEquals(null, actualResult);
    }

    /**
     * Test if an exception is thrown when the base url passed to the crawler is not found or broken
     * when GetAllData() is being called.
     */
    @Test(expected = Error.class)
    public void exceptionIsThrownWhenTheBaseUrlIsNotFoundWhenGetAllDataIsCalled() throws Exception {
        Crawler crawler = new Crawler("google.com");
        Scraper scraper = mock(Scraper.class);
        when(scraper.getPage("invalid.url")).thenThrow(Error.class);
        PowerMockito.whenNew(Scraper.class).withAnyArguments().thenReturn(scraper);

        crawler.getAllData("invalid.url");
    }

    /**
     * Test if an exception is thrown when the base url passed to the crawler is not found or broken
     * when GetSpecificData() is being called.
     */
    @Test(expected = Error.class)
    public void exceptionIsThrownWhenTheBaseUrlIsNotFoundWhenGetSpecificDataIsCalled() throws Exception {
        Crawler crawler = new Crawler();
        Scraper scraper = mock(Scraper.class);
        when(scraper.getPage("invalid.url")).thenThrow(Error.class);
        PowerMockito.whenNew(Scraper.class).withAnyArguments().thenReturn(scraper);

        crawler.getSpecificData("invalid.url", "", "");
    }
}
