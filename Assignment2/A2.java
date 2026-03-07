import java.io.FileNotFoundException;

/**
 * Assignment 2: A2.java
 * 
 * Usage:
 *   java A2 a2_data.csv
 * 
 * Description:
 *  1. Loads CSV file into a Table and prints all rows.
 *  2. Sorts alphabetically by the second column and prints all rows.
 *  3. Selects only rows where colour = black and prints all rows.
 *  4. Projects to columns (species, count, notes) and prints all rows.
 * 
 * Author: Andre Gardiner
 * @version 2.0
 * @see Table
 * @see Row
 */
public class A2 {

    public static void main(String[] args) throws FileNotFoundException {

        if (args.length < 1) {
            System.out.println("Usage: java A2 a2_data.csv");
            System.exit(1);
        }

        String fileName = args[0];
        Table table = new Table(fileName); 

        //0. File Name
        System.out.println("0 " + fileName);

        int rowCount = table.getRowCount() - 1; // exclude header row

        //1. First Table
        System.out.println();
        System.out.println("1. First Table");
        System.out.println("----------------------------");
        System.out.println(rowCount); 
        System.out.println("--------------------------");
        table.printTable(0);  
        System.out.println("--------------------------");

        //2. Sort By Colour
        System.out.println();
        System.out.println("2. Sort By Colour");
        System.out.println("----------------------------");
 
        String sort = "colour";

        table.sortByComparator(sort);
        System.out.println(rowCount); 
        System.out.println("----------------------------");
        table.printTable(0);
        System.out.println("----------------------------");
    }
}
