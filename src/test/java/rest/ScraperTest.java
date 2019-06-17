package rest;

import model.Book;
import model.Item;
import model.Movie;
import model.Music;
import org.jsoup.Jsoup;
import org.junit.Test;

import org.jsoup.nodes.Document;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

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
     * Exception should be thrown if an invalid html content is passed to scrapeData method
     */
    @Test(expected = IllegalArgumentException.class)
    public void exceptionIsThrownWhenInvalidHtmlContentIsPassedToScrapeData() {
        Document INVALID_HTML = new Document(INVALID_URL);
        scraper.scrapeData(INVALID_HTML);
    }

    /**
     * Parsed movie is not null when being fed valid html content with one movie object
     */
    @Test
    public void assertScrapedMovieIsNotNullWhenParsingValidHtmlContent() {
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
        Movie expected = new Movie(
                "Forrest Gump", "Drama", "DVD", 1994, "Robert Zemeckis", "Winston Groom, Eric Roth",
                "Tom Hanks, Rebecca Williams, Sally Field, Michael Conner Humphreys"
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
    public void assertScrapedBookIsNotNullWhenParsingValidHtmlContent() {
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
        Book expected = new Book(
                "Clean Code: A Handbook of Agile Software Craftsmanship", "Tech", "Ebook", 2008, "Robert C. Martin",
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
    public void assertScrapedMusicIsNotNullWhenParsingValidHtmlContent() {
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
        Music expected = new Music("Beethoven: Complete Symphonies", "Classical", "CD", 2012, "Ludwig van Beethoven");
        when(mockParser.parse(document.select("div.div-media-details"))).thenReturn(expected);

        //act
        List<Item> list = scraperWithMock.scrapeData(document);

        //assert
        assertNotNull("return list should not be null", list);
        assertEquals("return list should have length equals one", 1, list.size());
        assertSame("returned item should be the same", expected, list.get(0));
    }
}