import java.io.*;
import java.util.Scanner;

/**
 * Main class to demonstrate the Tables functionality
 * Reads data from a text file and displays it in table format
 */
public class Main {
    
    public static void main(String[] args) throws FileNotFoundException {
        Tables table = new Tables();
        
        // Read data from file
        String filename = "data.txt";
        readDataFromFile(filename, table);
        
        System.out.println("Table loaded from file: " + filename);
        System.out.println("Number of rows: " + table.count());
        System.out.println();
        
        // Print table without sorting (as read from file)
        System.out.println("Table as read from file:");
        table.printTable();
        System.out.println();
        
        // Sort by row number (using Comparable - natural ordering)
        System.out.println("Table sorted by ID (using Comparable):");
        table.sortRows();
        table.printTable();
        System.out.println();
        
        // Sort by text (using Comparator)
        System.out.println("Table sorted by text (using Comparator):");
        table.sortRows(new TextComparator());
        table.printTable();
        System.out.println();
        
        // Demonstrate adding a new row programmatically
        Row newRow = new Row(4);
        newRow.addColumn(new Column(0, "4"));
        newRow.addColumn(new Column(1, "books"));
        table.addRow(newRow);
        
        System.out.println("Table after adding a new row:");
        table.sortRows();  // Sort by ID
        table.printTable();
    }
    
    /**
     * Read data from a text file and populate the table
     * File format: id,text (one entry per line)
     * 
     * @param filename the name of the file to read
     * @param table the table to populate
     */
    private static void readDataFromFile(String filename, Tables table) throws FileNotFoundException {
        
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            
            // Skip empty lines

            
            // Parse the line (format: id,text)
            String[] parts = line.split(",");
            if (parts.length >= 2) {
                int id = Integer.parseInt(parts[0].trim());
                String text = parts[1].trim();
                
                // Create a new row
                Row row = new Row(id);
                
                // Add columns (id and text)
                row.addColumn(new Column(0, String.valueOf(id)));
                row.addColumn(new Column(1, text));
                
                // Add row to table
                table.addRow(row);
            }
        }
        
        scanner.close();
    }
}