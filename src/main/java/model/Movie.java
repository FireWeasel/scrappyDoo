package model;

import java.util.ArrayList;
import java.util.List;

/** Class that inherits from the Item class.
 * Used for parsing of Movie generated data.
 * Inherits all properties of parent class and has
 * custom Artist property.
 */
public class Movie extends Item {
    public Movie(){
        this.Writers = new ArrayList();
        this.Stars = new ArrayList();
    }
    /**
     * The director of the movie.
     */
    public String Director;

    /**
     * Field containing information about the writers
     * of the movie.
     */
    public List<String> Writers;
    /**
     * Field containing information about the stars
     * participating in the movie.
     */
    public List<String> Stars;
}
