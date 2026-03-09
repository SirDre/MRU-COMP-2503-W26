import java.io.FileNotFoundException;

/**
 * Assignment 2: A2.java
 * 
 * Usage:
 *  java A2 <filename>
 * 
 * Description:
 *  1. Loads CSV file into a Table and prints out in rows and columns.
 *  2. Sorts alphabetically by color and print results.
 *  3. Selects only rows where colour = black
 *  4. Projects to columns (Species, count, notes) and print results.
 * 
 * Author: Andre Gardiner
 * @version 2.0
 * @param arg
 * @throws FileNotFoundException
 */

public class A2 {

    public static void main(String[] args) throws FileNotFoundException {

        if(args.length < 1) {
            System.out.println("Usage: java A2 <filename>");
            System.exit(1);
        }

        String fileName = args[0];
        Table table = new Table(fileName);

        //0. File Name
        System.out.println("0 " + fileName);

        int rowCount = table.getRowCount() - 1; // exclude header row
        int rows = 10; //first 10 rows


        //1. First Table - first 10 rows
        System.out.println();
        System.out.println("1. First Table (first 10)");
        System.out.println("----------------------------");
        table.sort(); // sort by natural ordering (id)
        System.out.println(rows); 
        System.out.println("--------------------------");
        table.printTable(rows);  
        System.out.println("--------------------------");

        //2. Sort By Colour = first 10
        System.out.println();
        System.out.println("2. Sort By Colour (first 10)");
        System.out.println("----------------------------");
 
        String sort = "colour";

        table.sortByComparator(sort);
        System.out.println(rows); 
        System.out.println("--------------------------");
        table.printTable(rows);
        System.out.println("--------------------------");


        //3. Select colour = black
        System.out.println();
        System.out.println("3. Select colour = black");
        System.out.println("----------------------------");
        Table query = table.select(sort, "black");
        System.out.println(query.getRowCount() - 1); 
        System.out.println("--------------------------");
        query.printTable(0);
        System.out.println("--------------------------");


        //4. Project species, count, notes - the first 10 rows
        

        System.out.println();
        System.out.println("4. Project species, count, notes (first 10)");
        System.out.println("-------------------------------");
        Table projected = table.project(new String[]{"species", "count", "notes"});
        System.out.println(((projected.getRowCount() - 1) < rows) ? (projected.getRowCount() - 1) : rows); 
        System.out.println("--------------------------");
        projected.printTable(rows);
        System.out.println("--------------------------");

    }

}