import java.util.Comparator;
import java.util.Scanner;

/**
 * A1 is the main driver class for Assignment 1,
 * 
 * A1 class creates a Table object:
 * - Counting rows
 * - Sorting rows alphabetically
 * - Filtering rows based on the word "the" (case-insensitive)
 * - Printing the results to standard output
 * 
 * @author Assignment A1
 * @version 1.0
 * 
 * @see Table
 * @see Row
 * @see Scanner
 */
public class A1 {

      /**
     * Main method - entry point for the application.
     * 
     * Execution flow:
     * 1. Table object
     * 2. Read input from standard input (System.in) 
     * 3. Read line of input as a row in the table
     * 4. Perform required operations:
     *    - Show row count
     *    - Sort rows
     *    - Filter rows containing "the" and print results
     * 
     * @param args command line arguments 
     */
    public static void main(String[] args) {
        Table table = new Table(); // create a new Table object

        Scanner scanner = new Scanner(System.in); // create a Scanner to read from standard input

        // Read lines of input until there are no more lines
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine(); // read the next line of input
            table.addRow(line); // add the line as a new row in the table
        }

        int rows = table.getRowCount(); // get the number of rows in the table

        System.out.println("Row count: " + rows); // print the number of rows in the table

        System.out.println("Sorting table rows by natural order:");
        table.sortByNaturalRows(); // sort the rows in natural order (alphabetically)

        table.printTable(rows); // print all rows
        System.out.println(" "); // print a blank line for separation
        
        System.out.println("Sorting table rows by custom comparator:");
        //Comparator<Row> comparator = (r1, r2) -> r1.getText().compareToIgnoreCase(r2.getText()); // create a Comparator to sort rows alphabetically (case-insensitive)
        Comparator<Row> comparator = (r1, r2) -> r2.getText().compareTo(r1.getText()); // create a Comparator to sort rows in reverse alphabetical order (case-insensitive)
        table.sortByComparator(comparator);
 
        table.printTable(rows); // print all rows

        System.out.println(" "); // print a blank line for separation
        System.out.println("Rows containing 'the':");
        Table filteredTable = table.search("the"); // print only rows containing "the" (case-insensitive)
        filteredTable.printTable(filteredTable.getRowCount()); // print the filtered rows    

        scanner.close(); // close the scanner
    }  

}
