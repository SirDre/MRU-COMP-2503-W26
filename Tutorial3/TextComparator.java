import java.util.Comparator;

/**
 * TextComparator - Custom Comparator for sorting rows by text content
 * Demonstrates the use of Comparator interface
 */
public class TextComparator implements Comparator<Row> {
    
    /**
     * Compare two rows based on the text of their first column
     * @param row1 the first row to compare
     * @param row2 the second row to compare
     * @return negative if row1 < row2, 0 if equal, positive if row1 > row2
     */
    @Override
    public int compare(Row row1, Row row2) {
        // Get the first column text from each row
        String text1 = "";
        String text2 = "";
        
        if (row1.getColumns().size() > 1) {
            text1 = row1.getColumn(1).getText();
        }
        
        if (row2.getColumns().size() > 1) {
            text2 = row2.getColumn(1).getText();
        }
        
        // Compare the text values
        return text1.compareTo(text2);
    }
}