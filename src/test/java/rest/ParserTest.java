package rest;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import model.Book;
import model.Item;
import model.Movie;
import model.Music;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;


/**
 *  Test class for testing Parser class logic
 * */
@RunWith(JUnitParamsRunner.class)
public class ParserTest {
    private static final Object[] getBook(){
        return new Object[]{
                new Object[] {new Book("Sci-fi", "Paperback", 2012,"Book Title",new ArrayList<String>(Arrays.asList("Winston Groom", "Eric Roth")),"MyBooks.com","978-0132350884"),
                        new HashMap<String, String>(){{
                            put("Category", "Book");
                            put("Title", "Book Title");
                            put("Format", "Paperback");
                            put("Genre", "Sci-fi");
                            put("Year", "2012");
                            put("Authors", "Winston Groom, Eric Roth");
                            put("ISBN", "978-0132350884");
                            put("Publisher","MyBooks.com");
                }}}
                ,
                new Object[]{new Book("Drama", "Online", 2016, "Book Title 2",new ArrayList<String>(Arrays.asList("Eric Roth")),"MyBooks.com","978-0132350884"),
                        new HashMap<String, String>(){{
                            put("Category", "Book");
                            put("Title", "Book Title 2");
                            put("Format", "Online");
                            put("Genre", "Drama");
                            put("Year", "2016");
                            put("Authors", "Eric Roth");
                            put("ISBN", "978-0132350884");
                            put("Publisher","MyBooks.com");
                }}}};
    }
    private static final Object[] getMusic(){
        return new Object[]{
                new Object[] {new Music("Clasical", "CD", 2012,"Complete suite", "Ludwig van Bethoven"),
                        new HashMap<String, String>(){{
                            put("Category", "Music");
                            put("Title", "Complete suite");
                            put("Format", "CD");
                            put("Genre", "Clasical");
                            put("Year", "2012");
                            put("Artist", "Ludwig van Bethoven");
                        }}}
                ,
                new Object[]{new Music("Rock", "CD", 2016, "It's my life", "Bon Jovi"),
                        new HashMap<String, String>(){{
                            put("Category", "Music");
                            put("Title", "It's my life");
                            put("Format", "CD");
                            put("Genre", "Rock");
                            put("Year", "2016");
                            put("Artist", "Bon Jovi");
                        }}}};
    }

    private Parser parser;
    private Music expectedMusic;
    private HashMap<String, String> expectedMusicProps;
    private HashMap<String, String> expectedBookProps;
    private HashMap<String, String> expectedMovieProps;
    private Elements elementsMock;
    private Parser parserMock;

    @Before
    public void setUp(){
        // ARRANGE
        elementsMock = mock(Elements.class);
        parserMock = mock(Parser.class);
        parser  = new Parser();
        expectedMusic = new Music("Clasical", "CD" , 2012, "Symphony","Ludwig van Beethoven");
        expectedMusicProps = new HashMap<String, String>(){{
            put("Category", "Music");
            put("Genre", "Clasical");
            put("Format", "CD");
            put("Year", "2012");
            put("Title", "Symphony");
            put("Artist", "Ludwig van Beethoven");
        }};
        expectedBookProps = new HashMap<String, String>(){{
                put("Category", "Book");
                put("Title", "Book Title 2");
                put("Format", "Online");
                put("Genre", "Drama");
                put("Year", "2016");
                put("Authors", "Eric Roth");
                put("ISBN", "978-0132350884");
                put("Publisher", "MyBooks.com");
            }};
        expectedMovieProps = new HashMap<String, String>(){{
            put("Category", "Movie");
            put("Title", "Forrest Gump");
            put("Genre", "Drama");
            put("Format", "DVD");
            put("Year", "1994");
            put("Director", "Robert Zemeckis");
            put("Writers", "Winston Groom, Eric Roth");
            put("Stars", "Tom Hanks, Rebecca Williams, Sally Field, Michael Conner Humphreys");
        }};

        when(elementsMock.select("table")).thenReturn(elementsMock);
        when(elementsMock.select("tbody")).thenReturn(elementsMock);
        when(elementsMock.select("tr")).thenReturn(elementsMock);
    }
    /**
     * Test function that asserts if Item is returned
     * when calling parse of Parser class.
     * Element that is passed as argument is mocked.
     * */
    @Test
    public void assertItemIsReturnedWhenParsingExistingElements() throws Exception {
        // ARRANGE
        Iterator<Element> expectedElements = mock(Iterator.class);
        when(expectedElements.hasNext()).thenReturn(true, true, true, true, true, true, false);
        when(expectedElements.next())
                .thenReturn(new Element("tr").appendChild(new Element("th").appendText("Category")).appendChild(new Element("td").appendText("Music")))
                .thenReturn(new Element("tr").appendChild(new Element("th").appendText("Genre")).appendChild(new Element("td").appendText("Clasical")))
                .thenReturn(new Element("tr").appendChild(new Element("th").appendText("Title")).appendChild(new Element("td").appendText("Symphony")))
                .thenReturn(new Element("tr").appendChild(new Element("th").appendText("Format")).appendChild(new Element("td").appendText("CD")))
                .thenReturn(new Element("tr").appendChild(new Element("th").appendText("Year")).appendChild(new Element("td").appendText("2012")))
                .thenReturn(new Element("tr").appendChild(new Element("th").appendText("Artist")).appendChild(new Element("td").appendText("Ludwig van Beethoven")));

        when(elementsMock.size()).thenReturn(5);
        when(elementsMock.select("h1")).thenReturn(elementsMock);
        when(elementsMock.iterator()).thenReturn(expectedElements);
        when(parserMock.parseMusic(expectedMusicProps)).thenReturn(expectedMusic);

        // ACT
        Item item = parser.parse(elementsMock);

        // ASSERT
        Assert.assertNotNull("Null when there are not elements to parse",item);
    }

    @Test
    public void verifyItemIsOfCorrectTypeWhenParsingElementsAndCorrectParseFunctionIsCalledForCategoryMovie() throws Exception {
        //ARRANGE
        Iterator<Element> expectedElements = mock(Iterator.class);
        when(expectedElements.hasNext()).thenReturn(true, true, true, true, true, true,true,true ,false);
        when(expectedElements.next())
                .thenReturn(new Element("tr").appendChild(new Element("th").appendText("Category")).appendChild(new Element("td").appendText("Movie")))
                .thenReturn(new Element("tr").appendChild(new Element("th").appendText("Genre")).appendChild(new Element("td").appendText("Drama")))
                .thenReturn(new Element("tr").appendChild(new Element("th").appendText("Title")).appendChild(new Element("td").appendText("Forrest Gump")))
                .thenReturn(new Element("tr").appendChild(new Element("th").appendText("Format")).appendChild(new Element("td").appendText("DVD")))
                .thenReturn(new Element("tr").appendChild(new Element("th").appendText("Year")).appendChild(new Element("td").appendText("1994")))
                .thenReturn(new Element("tr").appendChild(new Element("th").appendText("Director")).appendChild(new Element("td").appendText("Robert Zemeckis")))
                .thenReturn(new Element("tr").appendChild(new Element("th").appendText("Writers")).appendChild(new Element("td").appendText("Winston Groom, Eric Roth")))
                .thenReturn(new Element("tr").appendChild(new Element("th").appendText("Stars")).appendChild(new Element("td").appendText("Tom Hanks, Rebecca Williams, Sally Field, Michael Conner Humphreys")));

        when(elementsMock.size()).thenReturn(5);
        when(elementsMock.select("h1")).thenReturn(elementsMock);
        when(elementsMock.iterator()).thenReturn(expectedElements);
        // When the parser mock calls parseMovie with the expectedMovieProps, the real method is called so that we can validate if the returned Item is a real instance of type Movie
        when(parserMock.parseMovie(expectedMovieProps)).thenCallRealMethod();
        // When the parser calls parse function, we call real method with the elementsMock, so that we can validate that parseMovie is called correctly in the switch statement
        when(parserMock.parse(elementsMock)).thenCallRealMethod();

        //ACT
        Item item = parserMock.parse(elementsMock);

        //ASSERT
        // Here we verify if the parseMovie function of the mocked parser is called at least once.
        verify(parserMock, atLeastOnce()).parseMovie(expectedMovieProps);
        // Here we assert if the returned item is of the correct category.
        assertThat(item, instanceOf(Movie.class));
    }

    @Test
    public void verifyItemIsOfCorrectTypeWhenParsingElementsAndCorrectParseFunctionIsCalledForCategoryBook() throws Exception {
        // ARRANGE
        Iterator<Element> expectedElements = mock(Iterator.class);
        when(expectedElements.hasNext()).thenReturn(true, true, true, true, true, true,true,true ,false);
        when(expectedElements.next())
                .thenReturn(new Element("tr").appendChild(new Element("th").appendText("Category")).appendChild(new Element("td").appendText("Book")))
                .thenReturn(new Element("tr").appendChild(new Element("th").appendText("Genre")).appendChild(new Element("td").appendText("Drama")))
                .thenReturn(new Element("tr").appendChild(new Element("th").appendText("Title")).appendChild(new Element("td").appendText("Book Title 2")))
                .thenReturn(new Element("tr").appendChild(new Element("th").appendText("Format")).appendChild(new Element("td").appendText("Online")))
                .thenReturn(new Element("tr").appendChild(new Element("th").appendText("Year")).appendChild(new Element("td").appendText("2016")))
                .thenReturn(new Element("tr").appendChild(new Element("th").appendText("Authors")).appendChild(new Element("td").appendText("Eric Roth")))
                .thenReturn(new Element("tr").appendChild(new Element("th").appendText("ISBN")).appendChild(new Element("td").appendText("978-0132350884")))
                .thenReturn(new Element("tr").appendChild(new Element("th").appendText("Publisher")).appendChild(new Element("td").appendText("MyBooks.com")));

        when(elementsMock.size()).thenReturn(5);
        when(elementsMock.select("h1")).thenReturn(elementsMock);
        when(elementsMock.iterator()).thenReturn(expectedElements);
        // When the parser mock calls parseBook with the expectedBookProps, the real method is called so that we can validate if the returned Item is a real instance of type Book
        when(parserMock.parseBook(expectedBookProps)).thenCallRealMethod();
        // When the parser calls parse function, we call real method with the elementsMock, so that we can validate that parseBook is called correctly in the switch statement
        when(parserMock.parse(elementsMock)).thenCallRealMethod();

        //ACT
        Item item = parserMock.parse(elementsMock);

        //ASSERT
        // Here we verify if the parseBook function of the mocked parser is called at least once.
        verify(parserMock, atLeastOnce()).parseBook(expectedBookProps);
        // Here we assert if the returned item is of the correct category.
        assertThat(item, instanceOf(Book.class));
    }
    /**
     * Test function that asserts if Movie is returned
     * when calling parseMovie of Parser class.
     * */
    @Test
    public void assertIfMovieCreatedCorrectlyWhenParsingMovieHashMap() throws Exception {
        // ARRANGE
        String director = "Robert Zemeckis";
        String title = "Forrest Gump";
        List<String> stars = new ArrayList<String>(Arrays.asList("Tom Hanks", "Rebecca Williams", "Sally Field", "Michael Conner Humphreys"));
        List<String> writers = new ArrayList<String>(Arrays.asList("Winston Groom", "Eric Roth"));
        String genre = "Drama";
        int year = 1994;
        String format = "DVD";
        Movie expectedMovie = new Movie(title, director, genre, format, year, writers, stars);

        // ACT
        Movie resultMovie = parser.parseMovie(expectedMovieProps);

        // ASSERT
        assertThat("Failing when object props do not match.",expectedMovie, new ReflectionEquals(resultMovie));
    }
    /**
     * Test function that asserts if Book is returned
     * when calling parseBook of Parser class.
     * */
    @Test
    @Parameters(method="getBook")
    public void assertIfBookCreatedCorrectlyWhenParsingBookHashMap(Book book, HashMap<String,String> itemParams) throws Exception {
        // ACT
        Book resultBook = parser.parseBook(itemParams);

        // ASSERT
        assertThat("Failing when object props do not match.",book, new ReflectionEquals(resultBook));
    }
    /**
     * Test function that asserts if Music is returned
     * when calling parseMusic of Parser class.
     * */
    @Test
    @Parameters(method="getMusic")
    public void assertIfMusicIsCreatedCorrectlyWhenParsingMusicHashMap(Music music, HashMap<String, String> itemParams) throws Exception {
        // ACT
        Music resultMusic = parser.parseMusic(itemParams);

        // ASSERT
        assertThat(music,new ReflectionEquals(resultMusic));
    }
    /**
     * Test function that asserts if null object is returned
     * when calling parse of Parser class and
     * verifies parseMusic,parseBook and parseMovie is never called.
     * Element that is passed as argument is mocked.
     * */
    @Test
    public void nullObjectIsReturnedWhenThereIsNoItemInElement() throws Exception {
        // ARRANGE
        when(elementsMock.size()).thenReturn(0);
        when(parserMock.parse(elementsMock)).thenCallRealMethod();

        // ACT
        Item result = parserMock.parse(elementsMock);

        // ASSERT
        assertThat("Should be null, on empty elements",result, is(nullValue()));
    }

    /**
     * Test which verifies that
     * no function for parsing one of the
     * three existing items is called, even if the
     * elements contain something.
     */
    @Test
    public void verifyNoParseMovieMusicOrBookIsCalledWhenItemExistingInElementsIsNotOfOneOfThoseTypes() throws Exception {
        // ARRANGE
        when(elementsMock.size()).thenReturn(1);
        Iterator<Element> expectedElements = mock(Iterator.class);
        when(expectedElements.hasNext()).thenReturn(true, true, true, true, true, false);
        when(expectedElements.next())
                .thenReturn(new Element("tr").appendChild(new Element("th").appendText("Category")).appendChild(new Element("td").appendText("Toys")));

        when(elementsMock.select("h1")).thenReturn(elementsMock);
        when(elementsMock.iterator()).thenReturn(expectedElements);
        when(parserMock.parse(elementsMock)).thenCallRealMethod();

        // ACT
        parserMock.parse(elementsMock);

        // ASSERT
        verify(parserMock, never()).parseMusic(expectedMovieProps);
        verify(parserMock, never()).parseBook(expectedBookProps);
        verify(parserMock, never()).parseMovie(expectedMovieProps);
    }
    /**
     * Test function that checks if exception is thrown
     * when passed parameters are null.
     * */
    @Test(expected = IllegalArgumentException.class)
    public void illegalArgumentIsThrownWhenParameterIsNull() throws Exception {
        // ACT
        parser.parse(null);
    }
    /**
     * Test function that checks if exception is thrown
     * when passed hashmap does not contain Movie.
     * */
    @Test (expected = Exception.class)
    public void exceptionShouldBeThrownWhenMovieHashMapDoesNotContainMovie() throws Exception {
        // ARRANGE
        expectedMovieProps.put("Category", "Book");

        //ACT
        parser.parseMovie(expectedMovieProps);
    }
    /**
     * Test function that checks if exception is thrown
     * when passed hashmap does not contain Book.
     * */
    @Test (expected = Exception.class)
    public void exceptionShouldBeThrownWhenBookHashMapDoesNotContainBook() throws Exception {
        //ARRANGE
        expectedBookProps.put("Category", "Music");

        // ACT
        parser.parseBook(expectedBookProps);
    }
    /**
     * Test function that checks if exception is thrown
     * when passed hashmap does not contain Music.
     * */
    @Test (expected = Exception.class)
    public void exceptionShouldBeThrownWhenMusicHashMapDoesNotContainMusic() throws Exception {
        // ARRANGE
        expectedMusicProps.put("Category", "Movie");

        // ACT
        parser.parseMusic(expectedMusicProps);
    }
    /**
     * Test function that checks if InvalidArgumentException
     * is thrown when parameters are null for parseMovie.
     * */
    @Test(expected = IllegalArgumentException.class)
    public void invalidArgumentIsThrownOnNullParametersMovie() throws Exception {
        expectedMovieProps.put("Title", null);
        expectedMovieProps.put("Year", "0");

        parser.parseMovie(expectedMovieProps);
    }
    /**
     * Test function that checks if InvalidArgumentException
     * is thrown when parameters are null for parseBook.
     * */
    @Test(expected = IllegalArgumentException.class)
    public void invalidArgumentIsThrownOnNullParametersBook() throws Exception {
        expectedBookProps.put("Genre", null);
        expectedBookProps.put("Publisher", null);
        expectedBookProps.put("Year", "0");

        parser.parseBook(expectedBookProps);
    }
    /**
     * Test function that checks if InvalidArgumentException
     * is thrown when parameters are null for parseMusic.
     * */
    @Test(expected = IllegalArgumentException.class)
    public void invalidArgumentIsThrownOnNullParametersMusic() throws Exception {
        expectedMusicProps.put("Artist", null);
        expectedMusicProps.put("Year", "0");

        parser.parseMusic(expectedMusicProps);
    }
}