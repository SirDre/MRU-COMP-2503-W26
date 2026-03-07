import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Table class
 * A two-dimensional data structure.
 * Supports:
 *   - loading data from a CSV file
 *   - storing rows in an ArrayList
 *   - sorting by natural order (Row id) and by a named column (Comparator)
 *   - relational-style project (column selection) and select (row filtering)
 *   - printing the table with custom column format alignment
 *
 * @author Andre Gardiner
 * @version 2.0
 * @see Row
 * @see A2
 */
public class Table {
    
    private ArrayList<Row> rows;  // List of rows in the table
    private int id;             // Unique ID for each row
 
    //Constructor: Creates an empty table. Row IDs will start at 1. */
    public Table() {
        this.rows = new ArrayList<>();
        this.id = 1; // IDs start at 1 so header=1, data rows=2..n
    }

    // Constructor: Loads a CSV file; first line becomes the header row.
    public Table(String fileName) throws FileNotFoundException {
        this(); // default constructor
        Scanner sc = new Scanner(new File(fileName));
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            addRow(id, line.trim().isEmpty() ? "" : line);
        }
        sc.close();
    }

    // Accessors: Get the 0-based column index of colName from the Header
    public int getColumnIndex(String colName) {
        Row header = getHeader();
        if (header == null) {
            return -1;
        }

        String[] cols = header.getCols();
        for (int i = 0; i < cols.length; i++) {
            if (cols[i].trim().equals(colName.trim())) {
                return i;
            }
        }
        return -1;
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
    public void setId(int nextId) { 
        this.id = nextId; 
    }

    // Get the number of rows in the table, including the header row
    public int getRowCount() {
        return rows.size();
    }
    
    // Get the header row (first row) of the table, or null if the table is empty
    public Row getHeader() {
        return rows.isEmpty() ? null : rows.get(0);
    }
    
    // Add a new row to the table with the given CSV line, assigning it a unique ID
    public void addRow(int nextId, String csv) {
        rows.add(new Row(nextId, csv));
        id = nextId + 1;
    }

    // Add a new row to the table, assigning it a unique ID based on the current max ID
    public void addRow(Row r) {
        rows.add(r);
        if (r.getId() >= id) {
            id = r.getId() + 1;
        }
    }

    // Print the table with formatted columns. 
    public void printTable(int maxRows) {
        if (rows.isEmpty()) {
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
        System.out.println(rows.get(0).format(true, 2, new int[]{9,9,5,14,18}));
        
        for (int i = 1; i <= limit; i++) { 
            System.out.println(rows.get(i).format(false, 2, new int[]{9,9,5,14,18}));
        }
    }

    // Sorts the table by the specified column name.
    public void sortByComparator(String colName) {
        int colIndex = getColumnIndex(colName);
 
        if (rows.size() <= 1) {
            return;
        }

        Row header = rows.remove(0);
       
        rows.sort((a, b) -> a.getColumn(colIndex).trim().compareTo(b.getColumn(colIndex).trim())); // sort by column value
        rows.add(0, header); 
    }


    @Override
    public String toString() {
        return "Table: " + rows.size() + " rows.";
    }   

}
