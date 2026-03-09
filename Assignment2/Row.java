/**
 * Row class
 * Represents a single row in a table data structure.
 * Supports:
 *   - storing an id and an array of String column values
 *   - comparing rows by their id (natural ordering)
 *
 * @author Andre Gardiner
 * @version 2.0
 *
 * @see Comparable
 * @see Table
 * @see A2
 */

public class Row implements Comparable<Row> {
    private int id;             // Unique ID for the row
    private String[] columns;   // Array of column values

    // Constructor: creates a Row with the given id and column values.
    public Row(int id, String[] columns) {
        this.id = id;
        this.columns = columns;
    }

    // Constructor: creates a Row from a CSV line, splitting it into columns.
    public Row(int id, String s) {
        this.id = id;
        this.columns = s.split(",", -1); // Split by comma, include empty strings for missing values
    }

    // Get the unique ID of the row
    public int getId() { 
        return id; 
    }
    
    // Set the unique ID for the row
    public void setId(int id) {
        this.id = id;
    }
    
    // Get the column values for the row
    public String[] getCols() {
        return columns; 
    }

    // Set the column values for the row
    public void setCols(String[] cols) { 
        this.columns = cols; 
    }

    // Get the value of a specific column by index
    public String getColumn(int index) {
        if (index < 0 || index >= columns.length) {
            return "";
        }
        return columns[index];
    }    
   
    // Set the value of a specific column by index
    public void setColumn(int index, String value) {
        if (index >= 0 && index < columns.length) {
            columns[index] = value;
        }
    }

    // Get the number of columns in the row
    public int getColCount() { 
        return columns.length; 
    }

    // Custom: String format the row as a string for printTable
    public String format(boolean isHeader, int hWidth, int[] cWidths) {
        String result = "";
        if (isHeader) {
            result += String.format("%" + hWidth + "s", "#");
        } else {
            result += String.format("%" + hWidth + "d:", id);
        }

        int spacing = Math.min(getColCount(), cWidths.length); // Limit spacing to the number of columns

        for (int i = 0; i < spacing; i++) {
            String val = columns[i];
            if (i < spacing - 1) {
                val += ",";
            }

            result += "\t" + String.format("%-" + cWidths[i] + "s", val);
        }
        return result;
    }

    @Override
    public int compareTo(Row other) {
        return Integer.compare(this.id, other.id);
    }    
}