package model;

import java.lang.reflect.WildcardType;
import java.util.List;
import java.util.Objects;

/** Class that inherits from the Item class.
 * Used for parsing of Movie generated data.
 * Inherits all properties of parent class and has
 * custom Artist property.
 */
public class Movie extends Item {
    public Movie(String Title, String Director, String Genre, String Format, int Year, List<String> Writers, List<String> Stars){
        super(Genre, Format, Year, Title);
        this.Director = Director;
        this.Writers = Writers;
        this.Stars = Stars;
    }
    /**
     * The director of the movie.
     */
    private String Director;

    /**
     * Field containing information about the writers
     * of the movie.
     */
    private List<String> Writers;

    /**
     * Field containing information about the stars
     * participating in the movie.
     */
    private List<String> Stars;

    public String getDirector() {
        return Director;
    }

    public List<String> getWriters() {
        return Writers;
    }

    public List<String> getStars() {
        return Stars;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;
        Movie movie = (Movie) o;
        return Objects.equals(getDirector(), movie.getDirector()) &&
                Objects.equals(getWriters(), movie.getWriters()) &&
                Objects.equals(getStars(), movie.getStars());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDirector(), getWriters(), getStars());
    }
}
