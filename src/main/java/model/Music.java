package model;

import java.util.Objects;

/** Class that inherits from the Item class.
 * Used for parsing of Music generated data.
 * Inherits all properties of parent class and has
 * custom director, writers and starts properties.
 */
public class Music extends Item{
    public Music(){}
    public Music(String genre, String format, int year, String title, String artist) {
        super(genre, format, year, title);
        if(artist == null) throw new IllegalArgumentException("Artist should not be null!");
        this.Artist = artist;
    }

    /**
     * Field containing information about the artist
     * performing the music.
     */
    private String Artist;

    public String getArtist() {
        return Artist;
    }

    @Override
    public boolean equals(Object o) {
        if(!super.equals(o)) return false;
        if (this == o) return true;
        if (!(o instanceof Music)) return false;
        if (!super.equals(o)) return false;
        Music music = (Music) o;
        return getArtist().equals(music.getArtist());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getArtist());
    }
}
