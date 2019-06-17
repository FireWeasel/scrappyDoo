package model;

/** Class that inherits from the Item class.
 * Used for parsing of Movie generated data.
 * Inherits all properties of parent class and has
 * custom Artist property.
 */
public class Movie extends Item {
    /**
     * The director of the movie.
     */
    public String Director;

    /**
     * Field containing information about the writers
     * of the movie.
     */
    public String Writers;
    /**
     * Field containing information about the stars
     * participating in the movie.
     */
    public String Stars;

    public Movie(String title, String genre, String format, int year, String director, String writers, String stars) {
        super(title, genre, format, year);
        Director = director;
        Writers = writers;
        Stars = stars;
    }
}
