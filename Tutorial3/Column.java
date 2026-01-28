
/**
 * Column class represents a cell in a table row
 * Implements Comparable for natural ordering by column number
 */

public class Column implements Comparable<Column> {
    private int colNumber;
    private String text;
    
    /**
     * Constructor
     * @param colNumber the column number
     * @param text the text content of the column
     */
    public Column(int colNumber, String text) {
        this.colNumber = colNumber;
        this.text = text;
    }
    
    // Getters
    public int getColNumber() {
        return colNumber;
    }
    
    public String getText() {
        return text;
    }
    
    // Setters
    public void setColNumber(int colNumber) {
        this.colNumber = colNumber;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    /**
     * Compare columns by column number
     * @param other the column to compare to
     * @return negative if this < other, 0 if equal, positive if this > other
     */
    @Override
    public int compareTo(Column other) {
        return Integer.compare(this.colNumber, other.colNumber);
    }
    
    @Override
    public String toString() {
        return "Column{" +
               "colNumber=" + colNumber +
               ", text='" + text + '\'' +
               '}';
    }
}