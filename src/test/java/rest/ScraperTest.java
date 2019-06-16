package rest;

import org.junit.Test;

import static org.junit.Assert.*;

public class ScraperTest {
    private static String INVALID_URL = "invalid.url.should.throw";
    private Scraper scraper = new Scraper();

    /**
     * Exception should be thrown if an invalid url is passed to getPage method
     */
    @Test(expected = IllegalArgumentException.class)
    public void exceptionIsThrownWhenInvalidUrlIsPassedToGetPage() {
        scraper.getPage(INVALID_URL);
    }

    /**
     * Exception should be thrown if an invalid html content is passed to scrapeData method
     */
    @Test(expected = IllegalArgumentException.class)
    public void exceptionIsThrownWhenInvalidHtmlContentIsPassedToScrapeData() {

    }

    /**
     * When passing a valid element, an object of type Item should be returned and be not null
     */
    @Test
    public void assertReturnedScrapedItemIsNotNullWhenParsingValidElement() {

    }

    /**
     * Parsed movie is not null when being fed valid html content with one movie object
     */
    @Test
    public void assertScrapedMovieIsNotNullWhenParsingValidHtmlContent() {

    }

    /**
     * Parsed book is not null when being fed valid html content with one book object
     */
    @Test
    public void assertScrapedBookIsNotNullWhenParsingValidHtmlContent() {

    }

    /**
     * Parsed music is not null when being fed valid html content with one music object
     */
    @Test
    public void assertScrapedMusicIsNotNullWhenParsingValidHtmlContent() {

    }

    /**
     * Jsoup should make a connection to a website url inside of the getPage method
     */
    @Test
    public void verifyJsoupConnectFunctionIsBeingCalledInsideGetPage() {

    }
}