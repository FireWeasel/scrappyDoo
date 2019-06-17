package model;

/** Class that inherits from the Item class.
 * Used for parsing of Music generated data.
 * Inherits all properties of parent class and has
 * custom director, writers and starts properties.
 */
public class Music extends Item {

    public Music() {
        super();
    }

    public Music(String artist) {
        Artist = artist;
    }

    public Music(String genre, String format, int year, String artist) {
        super(genre, format, year);
        Artist = artist;
    }

    /**
     * Field containing information about the artist
     * performing the music.
     */
    public String Artist;
}
