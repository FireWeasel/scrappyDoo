package rest;

import com.sun.javaws.exceptions.InvalidArgumentException;
import org.junit.Test;

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
    public void assertItemIsReturnedWhenParsingValidElement(){}
    /**
     * Test function that asserts if Movie is returned
     * when calling parseMovie of Parser class.
     * */
    @Test
    public void assertIfMovieCreatedCorrectlyWhenParsingMovieString(){}
    /**
     * Test function that asserts if Book is returned
     * when calling parseBook of Parser class.
     * */
    @Test
    public void assertIfBookCreatedCorrectlyWhenParsingBookString(){}
    /**
     * Test function that asserts if Music is returned
     * when calling parseMusic of Parser class.
     * */
    @Test
    public void assertIfMusicIsCreatedCorrectlyWhenParsingMusicString(){}
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
    @Test(expected = InvalidArgumentException.class)
    public void invalidArgumentIsThrownWhenParameterIsNull(){}
    /**
     * Test function that checks if exception is thrown
     * when passed string does not contain Movie.
     * */
    @Test (expected = Exception.class)
    public void exceptionShouldBeThrownWhenMovieStringDoesNotContainMovie(){}
    /**
     * Test function that checks if exception is thrown
     * when passed string does not contain Book.
     * */
    @Test (expected = Exception.class)
    public void exceptionShouldBeThrownWhenBookStringDoesNotContainBook(){}
    /**
     * Test function that checks if exception is thrown
     * when passed string does not contain Music.
     * */
    @Test (expected = Exception.class)
    public void exceptionShouldBeThrownWhenMusicStringDoesNotContainMusic(){}
    /**
     * Test function that checks if InvalidArgumentException
     * is thrown when parameters are null for parseMovie.
     * */
    @Test(expected = InvalidArgumentException.class)
    public void invalidArgumentIsThrownOnNullParametersMovie(){}
    /**
     * Test function that checks if InvalidArgumentException
     * is thrown when parameters are null for parseBook.
     * */
    @Test(expected = InvalidArgumentException.class)
    public void invalidArgumentIsThrownOnNullParametersBook(){}
    /**
     * Test function that checks if InvalidArgumentException
     * is thrown when parameters are null for parseMusic.
     * */
    @Test(expected = InvalidArgumentException.class)
    public void invalidArgumentIsThrownOnNullParametersMusic(){}
}