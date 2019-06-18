package rest;

import model.Book;
import model.Item;
import model.Movie;
import model.Music;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@PrepareForTest(Jsoup.class)
@RunWith(PowerMockRunner.class)
public class ScraperTest {
    private static String INVALID_URL = "invalid.url.should.throw";
    private Parser mockParser = mock(Parser.class);
    private Scraper scraperWithMock = new Scraper(mockParser);
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
     * Scrape data should return null if no parseable elements found
     */
    @Test
    public void scrapeDataShouldReturnNullIfNoParseableElementFound() {
        //arrange
        Document document = mock(Document.class);
        when(document.select("div.div-media-details")).thenReturn(new Elements());

        //act
        List<Item> list = scraper.scrapeData(document);

        //assert
        assertNull("scraped data should be null if no parseable element", list);
    }

    /**
     * Parsed movie is not null when being fed valid html content with one movie object
     */
    @Test
    public void assertScrapedMovieIsNotNullWhenParsingValidHtmlContent() throws Exception {
        //arrange
        String html = "<html><body><div class=\"div-media-details\">" +
                "<h1>Forrest Gump</h1>" +
                "<table><tbody>" +
                "<tr><th>Category</th><td>Movies</td></tr>" +
                "<tr><th>Genre</th><td>Drama</td></tr>" +
                "<tr><th>Format</th><td>DVD</td></tr>" +
                "<tr><th>Year</th><td>1994</td></tr>" +
                "<tr><th>Director</th><td>Robert Zemeckis</td></tr>" +
                "<tr><th>Writers</th><td>Winston Groom, Eric Roth</td></tr>" +
                "<tr><th>Stars</th><td>Tom Hanks, Rebecca Williams, Sally Field, Michael Conner Humphreys</td></tr>" +
                "</tbody></table>" +
                "</div></body></html>";
        Document document = Jsoup.parse(html);
        List<String> writers = new ArrayList<>();
        List<String> stars = new ArrayList<>();
        writers.add("Winston Groom");
        writers.add("Eric Roth");
        stars.add("Tom Hanks");
        stars.add("Rebecca Williams");
        stars.add("Sally Field");
        stars.add("Michael Conner Humphreys");
        Movie expected = new Movie(
                "Forrest Gump", "Robert Zemeckis", "Drama", "DVD", 1994,  writers, stars
        );
        when(mockParser.parse(document.select("div.div-media-details"))).thenReturn(expected);

        //act
        List<Item> list = scraperWithMock.scrapeData(document);

        //assert
        assertNotNull("return list should not be null", list);
        assertEquals("return list should have length equals one", 1, list.size());
        assertSame("returned item should be the same", expected, list.get(0));
    }

    /**
     * Parsed book is not null when being fed valid html content with one book object
     */
    @Test
    public void assertScrapedBookIsNotNullWhenParsingValidHtmlContent() throws Exception {
        //arrange
        String html = "<html><body><div class=\"div-media-details\">" +
                "<h1>Clean Code: A Handbook of Agile Software Craftsmanship</h1>" +
                "<table><tbody>" +
                "<tr><th>Category</th><td>Books</td></tr>" +
                "<tr><th>Genre</th><td>Tech</td></tr>" +
                "<tr><th>Format</th><td>Ebook</td></tr>" +
                "<tr><th>Year</th><td>2008</td></tr>" +
                "<tr><th>Authors</th><td>Robert C. Martin</td></tr>" +
                "<tr><th>Publisher</th><td>Prentice Hall</td></tr>" +
                "<tr><th>ISBN</th><td>978-0132350884</td></tr>" +
                "</tbody></table>" +
                "</div></body></html>";
        Document document = Jsoup.parse(html);
        List<String> authors = new ArrayList<>();
        authors.add("Robert C. Martin");
        Book expected = new Book(
                "Tech",  "Ebook",2008, "Clean Code: A Handbook of Agile Software Craftsmanship", authors,
                "Prentice Hall", "978-0132350884"
        );
        when(mockParser.parse(document.select("div.div-media-details"))).thenReturn(expected);

        //act
        List<Item> list = scraperWithMock.scrapeData(document);

        //assert
        assertNotNull("return list should not be null", list);
        assertEquals("return list should have length equals one", 1, list.size());
        assertSame("returned item should be the same", expected, list.get(0));
    }

    /**
     * Parsed music is not null when being fed valid html content with one music object
     */
    @Test
    public void assertScrapedMusicIsNotNullWhenParsingValidHtmlContent() throws Exception {
        //arrange
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
        Music expected = new Music("Classical", "CD", 2012, "Beethoven: Complete Symphonies", "Ludwig van Beethoven");
        when(mockParser.parse(document.select("div.div-media-details"))).thenReturn(expected);

        //act
        List<Item> list = scraperWithMock.scrapeData(document);

        //assert
        assertNotNull("return list should not be null", list);
        assertEquals("return list should have length equals one", 1, list.size());
        assertSame("returned item should be the same", expected, list.get(0));
    }

    @Test
    public void getAllLinksShouldReturnAllTheLinksFromAWebPage() {
        //arrange
        String html = "<html><body>" +
                "<a href=\"http://google.com\">To Google</a>" +
                "<a href=\"http://twitter.com\">To Twitter</a>" +
                "</body></html>";
        Document document = Jsoup.parse(html);
        ArrayList<String> expected = new ArrayList<>();
        expected.add("http://google.com");
        expected.add("http://twitter.com");

        //act
        ArrayList<String> list = scraper.getAllLinks(document);

        //assert
        assertEquals("array of returned links should have the same size", expected.size(), list.size());
        assertEquals("array of returned links should match", expected.get(0), list.get(0));
        assertEquals("array of returned links should match", expected.get(1), list.get(1));
    }

    @Test
    public void getAllLinksShouldReturnEmptyArrayIfNoLinksFound() {
        //arrange
        Document document = mock(Document.class);
        when(document.select("a[href]")).thenReturn(new Elements());

        //act
        ArrayList<String> list = scraper.getAllLinks(document);

        //assert
        assertEquals("array of returned links should be empty if no links", 0, list.size());
    }

    /**
     * Jsoup should make a connection to a website url inside of the getPage method
     */
    @Test
    public void verifyJsoupConnectFunctionIsBeingCalledInsideGetPage() throws IOException {
        //arrange
        PowerMockito.mockStatic(Jsoup.class);
        Connection connection = mock(Connection.class);
        String url = "http://google.com";
        when(connection.get()).thenReturn(new Document(url));
        when(Jsoup.connect(url)).thenReturn(connection);

        //act
        scraper.getPage(url);

        //assert
        verify(Jsoup.connect(url)).get();
    }
}
