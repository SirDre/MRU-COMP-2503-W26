import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


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
        this.id = 1; // Unique identifier can be set as needed
    }

    /**
     * Getter for the ArrayList of rows in the table.
     * @return the list of rows
    */
    public ArrayList<Row> getRows() {
        return rows;
    }

    /**
     * Getter for the next available id for new rows.
     * @return the next available id
     */
    public int getNextId() {
        return id;
    }

    /**
     * Sets the ArrayList of rows in the table.
     * @param rows the new list of rows to set
     */
    public void setRows(ArrayList<Row> rows) {
        this.rows = rows;
    }

    /**
     * Sets the next id value for new rows.
     * @param id the new id value to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Adds a new row to the table with the given text.
     * @param text the content of the new row
     */
    public void addRow(String text) {
       Row row = new Row(id++, text);
       rows.add(row);
    }

    /**
     * Returns the number of rows currently in the table.
     * @return the number of rows
     * O(n) = O(1) since ArrayList keeps track of its size
     */
    public int getRowCount() {
        return rows.size();
    }

    /**
     * Prints the table to standard output.
     * if r is 0, prints all rows.
     * if r is 1, prints only rows containing "the" (case-insensitive).
     * if r is greater than the number of rows, prints all rows.
     * 
     * debugging
     * O(n) = O(n) since we may need to iterate through all rows to print them
     * 
     * @param r the filter option for printing rows
     * 
     */
    public void printTable(int r) {

        int printRows; // number of rows to print

        if (r == 0) {
            printRows = rows.size(); // print all rows   
        } else {
            printRows = Math.min(r, rows.size()); // print all rows if r is greater than the number of rows
        } 

        // print each row based on the filter option
        for (int i = 0; i < printRows; i++) {       
            System.out.println(rows.get(i)); // print row id and text 
        }

    }

    /**
     * Sorts the rows in the table using natural ordering (based on the text content of the rows).
     * O(n log n) due to the sorting algorithm used by Collections.sort()
     * 
     * Note: The Row class must implement Comparable<Row> for this to work.
     * 
     * @see Row#compareTo(Row)
     * @see Collections#sort(List)
     */
    public void sortByNaturalRows() {
        Collections.sort(rows); // sorts based on natural ordering defined in Row class
    }

    /**
     * Sorts the rows in the table using a custom Comparator
     * O(n log n) due to the sorting algorithm used by Collections.sort()
     * 
     * @param comparator the Comparator to determine the order of the rows
     * @see Comparator
     * @see Collections#sort(List, Comparator)
     */
    public void sortByComparator(Comparator<Row> comparator) {
        Collections.sort(rows, comparator); // sorts based on the provided Comparator
    }
    
    /**
     * Searches for rows containing the specified keyword (case-insensitive) and returns a new Table with the filtered results.
     * O(n) since we need to iterate through all rows to filter them
     * 
     * @param keyword the keyword to search for in the row text
     * @return a new Table containing only the rows that match the search criteria
     */
    public Table search(String keyword) {
        
        Table filteredTable = new Table(); // create a new Table to hold the filtered results
        
        for (int i = 0; i < rows.size(); i++) {
            Row row = rows.get(i);

            if (row.getText().toLowerCase().contains(keyword.toLowerCase())) { // check if the row text contains the keyword (case-insensitive)
                filteredTable.addRow(row.getText()); // add the matching row to the filtered table
            }
        }

        return filteredTable; // return the new Table with the filtered results       
    }

    /**
     * Returns a string representation of the Table object.
     * O(n) since we need to iterate through all rows to build the string representation
     * @see Row#toString()
     * @return a string representation of the Table
     */
    @Override
    public String toString() {
        return id + ": " + rows.size();
    }
}
