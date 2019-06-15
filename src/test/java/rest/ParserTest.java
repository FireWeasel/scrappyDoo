package rest;

import model.Movie;
import org.junit.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;

import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;


/**
 *  Test class for testing Parser class logic
 * */
public class ParserTest {
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
        Parser parser = new Parser();
        Movie expectedMovie = new Movie();
        expectedMovie.Director = "Robert Zemeckis";
        expectedMovie.Stars = new String[]{"Tom Hanks", "Rebecca Williams","Sally Field", "Michael Conner Humphreys"};
        expectedMovie.Writers = new String[]{"Winston Groom", "Eric Roth"};
        expectedMovie.Genre = "Drama";
        expectedMovie.Year = 1994;
        expectedMovie.Format = "DVD";

        HashMap<String, String> inputItemProps = new HashMap<>();
        inputItemProps.put("Category", "Music");
        inputItemProps.put("Genre", "Drama");
        inputItemProps.put("Format", "DVD");
        inputItemProps.put("Year", "1994");
        inputItemProps.put("Director", "Robert Zemeckis");
        inputItemProps.put("Writers", "Winston Groom, Eric Roth");
        inputItemProps.put("Stars", "Tom Hanks, Rebecca Williams, Sally Field, Michael Conner Humphreys");

        Movie resultMovie = parser.parseMovie(inputItemProps);

        assertThat("Failing when object props do not match.",expectedMovie, new ReflectionEquals(resultMovie));
    }
    /**
     * Test function that asserts if Book is returned
     * when calling parseBook of Parser class.
     * */
    @Test
    public void assertIfBookCreatedCorrectlyWhenParsingBookHashMap(){}
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