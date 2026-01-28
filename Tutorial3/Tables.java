import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Tables class manages a collection of rows
 */
public class Tables {
    private ArrayList<Row> rows;
    
    /**
     * Constructor
     */
    public Tables() {
        this.rows = new ArrayList<>();
    }
    
    /**
     * Add a row to the table
     * @param row the row to add
     */
    public void addRow(Row row) {
        rows.add(row);
    }
    
    /**
     * Get the count of rows in the table
     * @return the number of rows
     */
    public int count() {
        return rows.size();
    }
    
    /**
     * Get all rows
     * @return the list of rows
     */
    public ArrayList<Row> getRows() {
        return rows;
    }
    
    /**
     * Sort rows using natural ordering (by row number)
     */
    public void sortRows() {
        Collections.sort(rows);
    }
    
    /**
     * Sort rows using a custom Comparator
     * @param comparator the comparator to use for sorting
     */
    public void sortRows(Comparator<Row> comparator) {
        Collections.sort(rows, comparator);
    }
    
    /**
     * Print the table in a formatted way
     */
    public void printTable() {

        // check if there are rows to print
        if (rows.isEmpty()) {
            System.out.println("Empty table");
            return;
        }
        
        // Enhanced loop: Determine column widths based on content
        int maxColumns = 0;
        for (Row row : rows) {
            if (row.getColumns().size() > maxColumns) {
                maxColumns = row.getColumns().size();
            }
        }
        
        // Calculate column widths
        int[] columnWidths = new int[maxColumns];
        for (int i = 0; i < maxColumns; i++) {
            columnWidths[i] = 0;
        }
        
        // Find maximum width for each column
        for (Row row : rows) {
            for (int i = 0; i < row.getColumns().size(); i++) {
                Column col = row.getColumn(i);
                if (col != null) {
                    int width = col.getText().length();
                    if (width > columnWidths[i]) {
                        columnWidths[i] = width;
                    }
                }
            }
        }
        
        // Ensure minimum width of 3 for each column
        for (int i = 0; i < columnWidths.length; i++) {
            if (columnWidths[i] < 3) {
                columnWidths[i] = 3;
            }
        }
        
        // Print header (assuming first row has column names or we use generic headers)
        // For this example, we'll use "id" and "text" as headers based on the output format
        if (maxColumns >= 2) {
            System.out.printf(" %-" + columnWidths[0] + "s | %-" + columnWidths[1] + "s%n", 
                            "id", "test");
            
            // Print separator line
            System.out.print(" ");
            for (int i = 0; i < columnWidths[0] + columnWidths[1] + 3; i++) {
                System.out.print("-");
            }
            System.out.println();
        }
        
        // Print rows
        for (Row row : rows) {
            System.out.print(" ");
            for (int i = 0; i < row.getColumns().size(); i++) {
                Column col = row.getColumn(i);
                if (col != null) {
                    if (i > 0) {
                        System.out.print(" | ");
                    }
                    System.out.printf("%-" + columnWidths[i] + "s", col.getText());
                }
            }
            System.out.println();
        }
    }
}