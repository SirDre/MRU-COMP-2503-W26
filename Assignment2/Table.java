import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Table class
 * A two-dimensional data structure.
 * Supports:
 *   - loading data from a CSV file
 *   - storing rows in an ArrayList
 *   - sorting by natural order (Row id) and by a named column (Comparator)
 *   - Database relational-style project (column selection) and select (row filtering)
 *   - printing the table with custom column formating
 *
 * @author Andre Gardiner
 * @version 2.0
 * @see Row
 * @see A2
 */

public class Table {
    private ArrayList<Row> rows = new ArrayList<>();
    private int id;

    public Table() {
        this.rows = new ArrayList<>();
        this.id = 1; 
    }

    public Table(String fileName) throws FileNotFoundException {
        this(); // Default constructor
        Scanner sc = new Scanner(new File(fileName));
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            addRow(id, line);
        }
        sc.close();        
    }
    
 
    // Get the list of rows in the table  
    public ArrayList<Row> getRows() {
        return rows;
    }

    // Set the list of rows in the table
    public void setRows(ArrayList<Row> rows) {
        this.rows = rows;
    }

    // Get the column headers of the table, or null if the table is empty
    public int getId() {
        return id;
    }

    // Set the next unique ID for new rows in the table    
    public void setId(int Id) {
        this.id = Id;
    }

    // Overloaded method: Add a new row to the table
    public void addRow(Row r) {
        rows.add(r);
    }

    // Overloaded method: Add a new row to the table with the given CSV line, assigning it a unique ID
    public void addRow(int Id, String s) {
        rows.add(new Row(Id, s));
        id = Id + 1; // add
    }

    // Overloaded method: Add a new row to the table
    public void addRow(int Id, String[] cols) {
        String s = String.join(",", cols);
        addRow(Id, s);
    }

    // Sorts the table by natural order (Row id).
    public void sort() {
        Collections.sort(rows);
    }

    // Sorts the table by the specified column name.
    public void sortByComparator(String colName) {
        int index = 1;
 
        if (rows.size() <= 1) {
            return;
        }

        Row header = rows.remove(0); // Remove header before sorting
       
        rows.sort((a, b) -> a.getColumn(index).trim().compareTo(b.getColumn(index).trim())); // sort by column value

        rows.add(0, header); // Add header back to the top
    }    

    // Print the table with formatted columns. 
    public void printTable(int maxRows) {
        if(rows.isEmpty()) {
            return;
        }
        // If r is 0, the whole table is printed, otherwise the first r rows are printed
        int limit;
        if (maxRows <= 0 || maxRows > rows.size() - 1) {
            limit = rows.size() - 1; // adjust to print all data rows
        } else {
            limit = maxRows;
        }

        // Print header then data rows
        System.out.println(rows.get(0));
        for (int i = 1; i <= limit; i++) { 
            System.out.println(rows.get(i));
        }
    }

    // Project that returns a new table that consists of all the rows of the existing table but with only the columns listed.
    public Table project(String[] cols)  {
        
        Table result = new Table();
  
        return result;
    }


    // Accessors: Get the 0-based column index of field from the Header
    public int getFieldIndex(String field) {
        Row header = getHeader();
        if (header == null) {
            return 0;
        }

        String[] cols = header.getCols();

        for (int i = 0; i < cols.length; i++) {
            if (cols[i].trim().equals(field.trim())) {
                return i;
            }
        }

        return 0;
    }

    // Custom: Get the number of rows in the table, including the header row
    public int getRowCount() {
        return rows.size();
    }  

     // Custom: Get the header row (first row) of the table, or null if the table is empty
    public Row getHeader() {
        return rows.get(0);
    }

    @Override
    public String toString() {
        return "Table: " + rows.size() + " rows.";
    }   

}
