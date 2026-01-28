import java.util.ArrayList;
import java.util.Collections;

/**
 * Row class represents a row in a table
 * Implements Comparable for natural ordering by row number
 */
public class Row implements Comparable<Row> {
    private int rowNumber;
    private ArrayList<Column> columns;
    
    /**
     * Constructor
     * @param rowNumber the row number
     */
    public Row(int rowNumber) {
        this.rowNumber = rowNumber;
        this.columns = new ArrayList<>();
    }
    
    // Getters
    public int getRowNumber() {
        return rowNumber;
    }
    
    public ArrayList<Column> getColumns() {
        return columns;
    }
    
    // Setters
    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }
    
    public void setColumns(ArrayList<Column> columns) {
        this.columns = columns;
    }
    
    /**
     * Add a column to this row
     * @param column the column to add
     */
    public void addColumn(Column column) {
        columns.add(column);
    }
    
    /**
     * Get column by index
     * @param index the index of the column
     * @return the column at the specified index
     */
    public Column getColumn(int index) {
        if (index >= 0 && index < columns.size()) {
            return columns.get(index);
        }
        return null;
    }
    
    /**
     * Sort columns by column number
     */
    public void sortColumns() {
        Collections.sort(columns);
    }
    
    /**
     * Compare rows by row number
     * @param other the row to compare to
     * @return negative if this < other, 0 if equal, positive if this > other
     */
    @Override
    public int compareTo(Row other) {
        return Integer.compare(this.rowNumber, other.rowNumber);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Row{rowNumber=").append(rowNumber);
        sb.append(", columns=[");
        for (int i = 0; i < columns.size(); i++) {
            if (i > 0) sb.append(", ");
            sb.append(columns.get(i).getText());
        }
        sb.append("]}");
        return sb.toString();
    }
}