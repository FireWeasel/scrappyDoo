package rest;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import model.Book;
import model.Item;
import model.Movie;
import model.Music;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;


/**
 *  Test class for testing Parser class logic
 * */
@RunWith(JUnitParamsRunner.class)
public class ParserTest {
    private static final Object[] getItem(){
        return new Object[]{
                new Object[] {new Book("Sci-fi", "Paperback", 2012,"Book Title",new ArrayList<String>(Arrays.asList("Winston Groom", "Eric Roth")),"MyBooks.com","978-0132350884"),
                        new HashMap<String, String>(){{
                            put("Category", "Books");
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
                            put("Category", "Books");
                            put("Title", "Book Title 2");
                            put("Format", "Online");
                            put("Genre", "Drama");
                            put("Year", "2016");
                            put("Authors", "Eric Roth");
                            put("ISBN", "978-0132350884");
                            put("Publisher","MyBooks.com");
                }}}};
    }

    private Parser parser;
    private Book expectedBook;
    private Movie expectedMovie;
    private Music expectedMusic;

    @Before
    public void setUp(){
        parser  = new Parser();
//        expectedBook = new Book("Sci-fi", "Paperback", 2012, "Book Title");
        expectedMovie = new Movie("Forrest Gump", "Robert Zemeckis", "Drama", "DVD", 1994, Arrays.asList("Winston Groom", "Eric Roth"),Arrays.asList("Tom Hanks", "Rebecca Williams", "Sally Field", "Michael Conner Humphreys"));
        expectedMusic = new Music("Rap", "Online", 2001, "Rap song");

        HashMap<String, String> inputPropsOfMovie = new HashMap<>();

        inputPropsOfMovie.put("Category", "Music");
        inputPropsOfMovie.put("Title", "Forrest Gump");
        inputPropsOfMovie.put("Genre", "Drama");
        inputPropsOfMovie.put("Format", "DVD");
        inputPropsOfMovie.put("Year", "1994");
        inputPropsOfMovie.put("Director", "Robert Zemeckis");
        inputPropsOfMovie.put("Writers", "Winston Groom, Eric Roth");
        inputPropsOfMovie.put("Stars", "Tom Hanks, Rebecca Williams, Sally Field, Michael Conner Humphreys");
    }
    /**
     * Test function that asserts if Item is returned
     * when calling parse of Parser class.
     * Element that is passed as argument is mocked.
     * */
    @Test
    public void assertItemIsReturnedWhenParsingExistingElements(){}
    /**
     * Test function that asserts if Movie is returned
     * when calling parseMovie of Parser class.
     * */
    @Test
    public void assertIfMovieCreatedCorrectlyWhenParsingMovieHashMap(){
        // ARRANGE
        Parser parser = new Parser();
        String director = "Robert Zemeckis";
        String title = "Forrest Gump";
        List<String> stars = new ArrayList<String>(Arrays.asList("Tom Hanks", "Rebecca Williams", "Sally Field", "Michael Conner Humphreys"));
        List<String> writers = new ArrayList<String>(Arrays.asList("Winston Groom", "Eric Roth"));
        String genre = "Drama";
        int year = 1994;
        String format = "DVD";
        Movie expectedMovie = new Movie(title, director, genre, format, year, writers, stars);
        HashMap<String, String> inputItemProps = new HashMap<>();
        inputItemProps.put("Category", "Music");
        inputItemProps.put("Title", "Forrest Gump");
        inputItemProps.put("Genre", "Drama");
        inputItemProps.put("Format", "DVD");
        inputItemProps.put("Year", "1994");
        inputItemProps.put("Director", "Robert Zemeckis");
        inputItemProps.put("Writers", "Winston Groom, Eric Roth");
        inputItemProps.put("Stars", "Tom Hanks, Rebecca Williams, Sally Field, Michael Conner Humphreys");

        // ACT
        Movie resultMovie = parser.parseMovie(inputItemProps);

        // ASSERT
        assertThat("Failing when object props do not match.",expectedMovie, new ReflectionEquals(resultMovie));
    }
    /**
     * Test function that asserts if Book is returned
     * when calling parseBook of Parser class.
     * */
    @Test
    @Parameters(method="getItem")
    public void assertIfBookCreatedCorrectlyWhenParsingBookHashMap(Book book, HashMap<String,String> itemParams){
        Book resultBook = parser.parseBook(itemParams);

        assertThat("Failing when object props do not match.",book, new ReflectionEquals(resultBook));
    }
    /**
     * Test function that asserts if Music is returned
     * when calling parseMusic of Parser class.
     * */
    @Test
    public void assertIfMusicIsCreatedCorrectlyWhenParsingMusicHashMap(){}
    /**
     * Test function that asserts if null object is returned
     * when calling parse of Parser class.
     * Element that is passed as argument is mocked.
     * */
    @Test
    public void nullObjectIsReturnedWhenThereIsNoItemInElement(){}
    /**
     * Test function that checks if exception is thrown
     * when passed parameters are null.
     * */
    @Test(expected = IllegalArgumentException.class)
    public void illegalArgumentIsThrownWhenParameterIsNull(){}
    /**
     * Test function that checks if exception is thrown
     * when passed hashmap does not contain Movie.
     * */
    @Test (expected = Exception.class)
    public void exceptionShouldBeThrownWhenMovieHashMapDoesNotContainMovie(){}
    /**
     * Test function that checks if exception is thrown
     * when passed hashmap does not contain Book.
     * */
    @Test (expected = Exception.class)
    public void exceptionShouldBeThrownWhenBookHashMapDoesNotContainBook(){}
    /**
     * Test function that checks if exception is thrown
     * when passed hashmap does not contain Music.
     * */
    @Test (expected = Exception.class)
    public void exceptionShouldBeThrownWhenMusicHashMapDoesNotContainMusic(){}
    /**
     * Test function that checks if InvalidArgumentException
     * is thrown when parameters are null for parseMovie.
     * */
    @Test(expected = IllegalArgumentException.class)
    public void invalidArgumentIsThrownOnNullParametersMovie(){}
    /**
     * Test function that checks if InvalidArgumentException
     * is thrown when parameters are null for parseBook.
     * */
    @Test(expected = IllegalArgumentException.class)
    public void invalidArgumentIsThrownOnNullParametersBook(){}
    /**
     * Test function that checks if InvalidArgumentException
     * is thrown when parameters are null for parseMusic.
     * */
    @Test(expected = IllegalArgumentException.class)
    public void invalidArgumentIsThrownOnNullParametersMusic(){}
}