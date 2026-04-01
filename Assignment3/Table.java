import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Table class
 * A two-dimensional relational data structure backed by an ArrayList of Rows.
 *
 * Supports:
 *   - loading data from a CSV file
 *   - storing rows in an ArrayList
 *   - sorting by natural order (Row id) and by a named column (Comparator)
 *   - Database relational-style project (column selection) and select (row filtering)
 *   - printing the table with custom column formating
 *
 * @author Andre Gardiner
 * @version 3.0
 * @see Row
 * @see BST
 * @see A3
 */
public class Table {

    // Instance
    private int id; // next auto-increment row id
    private String name; // table name (derived from filename)
    
    private ArrayList<Row> rows; // list of rows (index 0 is header)
    private ArrayList<String> indexColumns; // indexed column names
    private ArrayList<BST> indexTrees; // BST per indexed column

    // Constructors
    public Table() {
        id = 1;
        name = "";
        indexColumns = new ArrayList<>();        
        indexTrees = new ArrayList<>();    
        rows = new ArrayList<>();
    }

    // Construct a Table by reading every line from a CSV file.
    public Table(String fileName) throws FileNotFoundException {
        this(); // Default constructor
        File file = new File(fileName);
 
        this.name = file.getName();
        if (name.contains(".")) {
            name = name.substring(0, name.lastIndexOf('.'));
        }

        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();     // trim handles \r\n on Windows
            if (!line.isEmpty()) {
                addRow(id, line);
            }
        }
        sc.close();
    }

    // Get the internal list of rows (used for select, union, etc.)
    public ArrayList<Row> getRows() {
        return rows; 
    }

    // Set the internal list of rows (used for select, union, etc.)
    public void setRows(ArrayList<Row> rows) { 
        this.rows = rows; 
    }

    // Get the next auto-increment row id (used when adding new rows)
    public int getId() { 
        return id; 
    }

    // Set the next auto-increment row id (used when loading from a file)
    public void setId(int id) {
        this.id = id;
    }

    // Get the table name (e.g. from the file name, without extension)
    public String getName() {
        return name;
    }

    // Set the table name (e.g. from the file name, without extension)
    public void setName(String name) {
        this.name = name;
    }
 
    // Return the 0-based index of a column name, or 0 if not found 
    public int getIndex(String field) {
        Row header = getHeader();

        if (header == null)
            return 0;

        String[] cols = header.getCols();

        for (int i = 0; i < cols.length; i++) {
            if (cols[i].trim().equals(field.trim()))
                return i;
        }
        return 0;
    }

    // Return the position of an indexed column
    private int getIndexedColPos(String field) {
        String col;
        
        if (field == null)
            col = "";
        else
            col = field.trim(); 
        
        for (int i = 0; i < indexColumns.size(); i++) {
            if (indexColumns.get(i).trim().equals(col)) { // match
                return i;
            }
        }
        return -1; // not found
    }

    // Total row count including the header
    public int getRowCount() {
        return rows.size();
    }

    // Column count taken from the header row
    public int getColCount() {
        Row header = getHeader();
        return (header == null) ? 0 : header.getColCount();
    }

    // Return the header row (index 0), or null if the table is empty
    public Row getHeader() {
        return rows.isEmpty() ? null : rows.get(0);
    }

    // Add a row object (id counter is NOT updated)
    public void addRow(Row r) {
        rows.add(r);
    }

    // Parse a CSV line, wrap it in a Row with the given id, and append it
    public void addRow(int Id, String s) {
        rows.add(new Row(Id, s));
        id = Id + 1;
    }

    // Overload: join the column array into a CSV string and call the other addRow
    public void addRow(int Id, String[] cols) {
        addRow(Id, String.join(",", cols));
    }

    // Duplicate keys accumulate in the same BST node (ArrayList<Row>)  
    public void addIndex(String column) {

        int col = getIndex(column);
        BST bst = new BST();

        for (int i = 1; i < rows.size(); i++) { // skip header at i=0
            Row row = rows.get(i);
            bst.add(row.getColumn(col).trim(), row);
        }

        int idPos = getIndexedColPos(column); // check if an index already exists for the column

        if (idPos >= 0) {
            indexColumns.set(idPos, column);
            indexTrees.set(idPos, bst);
        } else {
            indexColumns.add(column);
            indexTrees.add(bst);
        }
    }

    // Sort data rows by natural order (Row id); header stays at index 0
    public void sort() {
        if (rows.size() <= 1)
            return;

        Row header = rows.remove(0);
        Collections.sort(rows);
        rows.add(0, header);
    }

    // Sort data rows alphabetically by the named column; header is preserved 
    public void sortByComparator(String field) {
        int index = getIndex(field);
        if (rows.size() <= 1)
            return;

        Row header = rows.remove(0);
        rows.sort((a, b) -> a.getColumn(index).trim().compareTo(b.getColumn(index).trim()));
        rows.add(0, header);
    }

    // Return all columns but only the rows where if an index exists for field, the BST is used → O(log n). Otherwise a linear scan is performed → O(n). 
    public Table select(String field, String value) {
        Table selectTable = new Table();
        selectTable.setName(this.name);

        // copy the header row first
        Row header = getHeader();
        if (header != null) {
            selectTable.addRow(selectTable.getId(), header.getCols());
        }

        int idPos = getIndexedColPos(field); // check if an index exists for the field

        if (idPos >= 0) {
            // O(log n) index
            ArrayList<Row> found = indexTrees.get(idPos).find(value.trim());
 
            for (Row row : found) {
                selectTable.addRow(selectTable.getId(), row.getCols());
            } 
        } else {
            // O(n) linear
            int colId = getIndex(field);
            for (int i = 1; i < rows.size(); i++) {
                Row row = rows.get(i); 
                selectTable.addRow(selectTable.getId(), row.getCols());
                
            }
        }
        return selectTable;
    }

    // Return a new table that is the union of this table with t. Returns null if the two tables have different column counts
     public Table union(Table t) {
        Table unionTable = new Table();
        unionTable.setName(this.name);

        if (this.getColCount() != t.getColCount()) {
            return null;
        }

        // Copy every row from this table (header + data)
        for (Row row : this.rows) {
            unionTable.addRow(unionTable.getId(), row.getCols());
        }
 

        return unionTable;
    }

    // cross — returns a new table that is the cross result of this table with t. The result has all columns from both tables. If a column name appears in both tables, every column in the result is prefixed with its source table's name 
    public Table cross(Table t) {
        Table crossTable = new Table();
        crossTable.setName(this.name);

        Row h1 = this.getHeader();
        Row h2 = t.getHeader();

        String[] cols1 = h1.getCols();
        String[] cols2 = h2.getCols();

        boolean conflict = false; // check for conflicting column names 
        
        // O(t1*t2) check for any matching column names
        for (String c2 : cols2) {
            for (String c1 : cols1) {
                if (c2.trim().equals(c1.trim())) {
                    conflict = true;
                }
            }
        }

        String[] mergedCols = new String[cols1.length + cols2.length];

        for (int i = 0; i < cols1.length; i++) {
            mergedCols[i] = conflict ? this.name + "." + cols1[i].trim() : cols1[i].trim();
        }

        // O(t1 * t2) cross product of data rows (skip headers at index 0)
        for (int i = 1; i < this.rows.size(); i++) {
            for (int j = 1; j < t.getRows().size(); j++) {

                Row r1 = this.getRows().get(i);
                Row r2 = t.getRows().get(j);

                String[] merged = new String[r1.getColCount() + r2.getColCount()];
    
                crossTable.addRow(crossTable.getId(), merged);
            }
        }
        return crossTable;
    }
 
    // minus — returns all rows in this table that are NOT in t. 
    public Table minus(Table t) {
        Table minusTable = new Table();
        minusTable.setName(this.name);

        if (this.getColCount() != t.getColCount()) {
            return null;
        }
        
        // include rows from this that are absent from t
        for (int i = 1; i < this.rows.size(); i++) {
            Row row = this.rows.get(i);
            boolean found = false;
 
            if (!found) {
                minusTable.addRow(minusTable.getId(), row.getCols());
            }  

        }

        return minusTable;
    }
 
    // project — returns a new table with only the specified columns.
    public Table project(String[] cols) {
        
        Table projectTable = new Table();
        projectTable.setName(this.name);

        int[] indices = new int[cols.length];

        for (int i = 0; i < cols.length; i++) {
            indices[i] = getIndex(cols[i]);
        }

        for (Row row : rows) {
            String[] projected = new String[cols.length];
            for (int i = 0; i < indices.length; i++) {
                projected[i] = row.getColumn(indices[i]);
            }
            projectTable.addRow(projectTable.getId(), projected);
        }

        return projectTable;
    }

    // print
    public void printTable(int r) {
        if(rows.isEmpty()) {
            return;
        }

        // If r is 0, the whole table is printed, otherwise the first r rows are printed
        int limit;
        if (r <= 0 ) {
            limit = rows.size() - 1; // adjust to print all data rows
        } else {
            limit = r;
        }

        int numCols = rows.get(0).getColCount();
        
        int[] widths = new int[numCols]; // max width of each column

        // Calculate the maximum width for each column
        for (int i = 0; i <= limit && i < rows.size(); i++) {
            Row row = rows.get(i);
            for (int j = 0; j < numCols && j < row.getColCount(); j++) {
                String val = row.getColumn(j);
                if (val != null && val.trim().length() > widths[j]) {
                    widths[j] = val.trim().length() + 2; // add padding
                }
            }
        }

        // Print header then rows
        System.out.println(rows.get(0).format(true, 2, widths));
        for (int i = 1; i <= limit; i++) { 
            System.out.println(rows.get(i).format(false, 2, widths));
        }
    }

    @Override
    public String toString() {
        return "Table[" + name + "]: " + (rows.size() - 1) + " rows.";
    }
}