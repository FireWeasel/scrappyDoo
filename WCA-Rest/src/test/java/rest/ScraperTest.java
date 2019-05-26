package rest;

import org.junit.Test;

import static org.junit.Assert.*;

public class ScraperTest {
    /**
     * Exception should be thrown if an invalid url is passed to getPage method
     */
    @Test(expected = IllegalArgumentException.class)
    public void exceptionIsThrownWhenInvalidUrlIsPassedToGetPage() {

    }

    /**
     * Exception should be thrown if an invalid html content is passed to scrapeData method
     */
    @Test
    public void exceptionIsThrownWhenInvalidHtmlContentIsPassedToScrapeData() {

    }

    /**
     * parseMovie method of class Parser should be called if fed a movie element
     */
    @Test
    public void verifyParseMovieIsCalledForAMovieElement() {

    }

    /**
     * parseBook method of class Parser should be called if fed a book element
     */
    @Test
    public void verifyParseBookIsCalledForABookElement() {

    }

    /**
     * parseMusic method of class Parser should be called if fed a music element
     */
    @Test
    public void verifyParseMusicIsCalledForAMusicElement() {

    }
}