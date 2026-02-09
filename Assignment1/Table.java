import java.util.*;


/**
 * Table class
 * A two-dimensional data structure.
 * Utilizes ArrayLists as the underlying storage mechanism.
 * Supports natural ordering and custome ordiner (Comparator).
 * @author Andre Gardiner
 * @version 1.0
 * 
 * @see ArrayList
 * @see Comparator
 * @see Comparable
 * @see Row
 * 
 */

public class Table {

    // Instance variables
    private ArrayList<Row> rows;
    private int id;

    /**
     * Constructor for Table class.
     * Initializes an empty table with a unique identifier.
     */
    public Table() {
        this.rows = new ArrayList<>();
        this.id = 0; // Unique identifier can be set as needed
    }

    
    
}
