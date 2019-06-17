package rest;

import model.Item;
import model.Music;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import org.jsoup.nodes.Document;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ScraperTest {
    private static String INVALID_URL = "invalid.url.should.throw";
    private Scraper scraper = new Scraper();

    /**
     * Exception should be thrown if an invalid url is passed to getPage method
     */
    @Test(expected = IllegalArgumentException.class)
    public void exceptionIsThrownWhenInvalidUrlIsPassedToGetPage() {
        // act
        scraper.getPage(INVALID_URL);
    }

    /**
     * Exception should be thrown if an invalid html content is passed to scrapeData method
     */
    @Test(expected = IllegalArgumentException.class)
    public void exceptionIsThrownWhenInvalidHtmlContentIsPassedToScrapeData() {
        Document INVALID_HTML = new Document(INVALID_URL);
        scraper.scrapeData(INVALID_HTML);
    }

    /**
     * When passing a valid element, an object of type Item should be returned and be not null
     */
    @Test
    public void assertReturnedScrapedItemIsNotNullWhenParsingValidElement() {
        //arrange
        Parser mockParser = mock(Parser.class);
        Scraper scraper = new Scraper(mockParser);
        String html = "<html><body><div class=\"div-media-details\">" +
                "<h1>Beethoven: Complete Symphonies</h1>" +
                "<table><tbody>" +
                "<tr><th>Category</th><td>Music</td></tr>" +
                "<tr><th>Genre</th><td>Classical</td></tr>" +
                "<tr><th>Format</th><td>CD</td></tr>" +
                "<tr><th>Year</th><td>2012</td></tr>" +
                "<tr><th>Artist</th><td>Ludwig van Beethoven</td></tr>" +
                "</tbody></table>" +
                "</div></body></html>";
        Document document = Jsoup.parse(html);
        Music expected = new Music("Beethoven: Complete Symphonies", "Classical", "CD", 2012, "Ludwig van Beethoven");
        when(mockParser.parse(document.select("div.div-media-details"))).thenReturn(expected);

        //act
        List<Item> list = scraper.scrapeData(document);

        //assert
        assertNotNull("return list should not be null", list);
        assertEquals("return list should have length equals one", 1, list.size());
        assertSame("returned item should be the same", expected, list.get(0));
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