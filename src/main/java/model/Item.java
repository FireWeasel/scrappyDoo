package model;

/** Abstract class with common properties
 * for the items the scraper generates.
 * Used for parsing the json data, extracted from the website.
 * Contains properties for genre, format and year of item.
 * @version 0.1
 */
public abstract class Item {
    /**
     * Field containing information about
     * the title of the item.
     */
    public String Title;
    /**
     * Field containing information about
     * the genre of the item.
     */
    public String Genre;
    /**
     * Field containing information about the
     * format of the item.
     */
    public String Format;
    /**
     * Field containing information about
     * the year of creation of the item.
     */
    public int Year;

    public Item(String title, String genre, String format, int year) {
        Title = title;
        Genre = genre;
        Format = format;
        Year = year;
    }
}
