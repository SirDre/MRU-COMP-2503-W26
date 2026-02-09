
/**
 * Row class
 * a single row in a Table data structure.
 * The comparable interface is implemented to allow for natural ordering of rows based on their content.
 * @author Andre Gardiner
 * @version 1.0
 * 
 * @see Comparable
 * @see Table
 */

public class Row implements Comparable<Row> {

    private int id;
    private String text;

    /**
     * Constructor for Row class
     * @param id the unique identifier for the row
     * @param text the content of the row
     */
    public Row(int id, String text) {
        this.id = id;
        this.text = text;   
    }

    /**
     * Getter for id
     * @return the unique identifier for the row
     */
    public int getId() {
        return id;
    }

    /**
     * Getter for text
     * @return the content of the row
     */
    public String getText() {
        return text;
    }

    /**
     * Setter for text
     * @param text the new content for the row
     * @return void
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Setter for id
     * @param id the new unique identifier for the row
     * @return void
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns a string representation of the Row object
     * @return a string in the format "Row{id=ID, text='TEXT'}
     * 
     */
    @Override
    public String toString() {
        return "Row{id=" + id + ", text='" + text + "'}";
    }

    /**
     * Compares this Row object with another Row object for natural ordering.
     * The comparison is based on the text content of the rows, ignoring case.
     * @param other the Row object to be compared
     * @return a integer 
     */
    @Override
    public int compareTo(Row other) {
        return this.text.compareTo(other.text); // follows the comparable contract for natural ordering based on text content
    }
    
}
