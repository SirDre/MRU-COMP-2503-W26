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
    private String[] headers;    // Array of column headers
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

    /**
     * Prints the header row followed by up to {@code maxRows} data rows,
     * using auto-computed column widths for alignment.
     *
     * @param maxRows max data rows to print; 0 (or larger than available) prints all.
     */
    public void printTable(int maxRows) {
        if (rows.isEmpty()) {
            return;
        }

        int cols = rows.get(0).getColCount();
        int[] cWidths = new int[cols];
        int hWidths = 1;

        // Calculate column widths and max id digit count
        for (int i = 0; i < rows.size(); i++) {
            Row row = rows.get(i);
            if (i > 0) { // skip header 
                hWidths = Math.max(hWidths, String.valueOf(row.getId()).length());
            }
            for (int j = 0; j < cols; j++) {
                String val = row.getColumn(j);
                 
                int len = val.length() + 1;
                cWidths[j] = Math.max(cWidths[j], len);
            }
        }
 
        // Print header then data rows
        System.out.println(rows.get(0).format(true, hWidths, cWidths));
        
        for (int i = 1; i <= rows.size() - 1; i++) {
            System.out.println(rows.get(i).format(false, hWidths, cWidths));
        }
    }

    @Override
    public String toString() {
        return "Table: " + rows.size() + " rows.";
    }   

}
