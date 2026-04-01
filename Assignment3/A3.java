import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Assignment 2: A2.java
 * 
 * Usage:
 * java A2 <filename>
 * 
 * Description:
 * Loads each CSV file into a Table, then performs:
 * 1. Print name and row count for every table.
 * 2. Build a BST index on "colour" for a3_d1; select colour = black.
 * 3. Cross product of a3_d2 x a3_d3; print result.
 * 4. Union of a3_d2 u a3_d4.
 * 5. Print result.
 * 
 * Author: Andre Gardiner
 * 
 * @version 3.0
 */

public class A3 {

    public static void main(String[] args) throws FileNotFoundException {

        if (args.length < 1) {
            System.out.println("Usage: java A3 <a3_d1.csv> [a3_d2.csv] [a3_d3.csv] [a3_d4.csv]");
            System.exit(1);
        }

        // 1. Load all CSV files into an ArrayList of Tables
        System.out.println("1. Load all CSV files");
        System.out.println("----------------------------");
        ArrayList<Table> tables = new ArrayList<>();

        String[] tableNames = new String[args.length];
        int index = 0;

        for (String fileName : args) {
            Table t = new Table(fileName);
            tables.add(t);
            System.out.println("- " + fileName + " (" + (t.getRowCount() - 1) + " data rows)");

            tableNames[index++] = fileName.split("\\.")[0];

        }
        System.out.println();

        // variables for queries
        int rowLimit = 0; // 0 for no limit
        String columnName = "colour";
        String colorString = "Puce";
        String tableName0 = tableNames[0];
        String tableName1 = tableNames[1];
        String tableName2 = tableNames[2];
        String tableName3 = tableNames[3];

        // 2. BST index on "colour"; select colour = black (a3_d1)
        System.out.println("2. Select colour = " + colorString + " (a3_d1)");
        System.out.println("----------------------------");
        Table d1 = findTable(tables, tableName0);

        if (d1 != null) {
            d1.addIndex("colour");

            Table colourTable = d1.select(columnName, colorString);
            System.out.println("Rows matching " + columnName + " = " + colorString + ": " + (colourTable.getRowCount() - 1));
            System.out.println();
            colourTable.printTable(rowLimit);
        }
        System.out.println();

        // 3. Cross product: a3_d2 x a3_d3
        System.out.println("3. Cross product: "+ tableName1 +" x "+ tableName2);
        System.out.println("----------------------------");
        Table d2 = findTable(tables, tableName1);
        Table d3 = findTable(tables, tableName2);

        if (d2 != null && d3 != null) {
            Table cross = d2.cross(d3);
            System.out.println("CROSS rows: " + (cross.getRowCount() - 1) + "  (" + (d2.getRowCount() - 1) + " x " + (d3.getRowCount() - 1) + ")");
            System.out.println();
            cross.printTable(rowLimit);
        }
        System.out.println();

        // 4. Union: a3_d2 ∪ a3_d4
        System.out.println("4. Union: "+ tableName1 +" u "+ tableName3);
        System.out.println("----------------------------");
        Table d4 = findTable(tables, tableName3);

        if (d2 != null && d4 != null) {
            Table union = d2.union(d4);
            if (union == null) {
                System.out.println("Not possible:" + " column counts (" + d2.getColCount() + " vs " + d4.getColCount() + ").");
            } else {
                System.out.println("Result rows: " + (union.getRowCount() - 1));
                System.out.println();
                union.printTable(rowLimit);
            }
        }
        System.out.println();

        // 5. Minus: a3_d2 - a3_d4
        System.out.println("5. Minus: "+ tableName1 +" - "+ tableName3);
        System.out.println("----------------------------");

        if (d2 != null && d4 != null) {
            Table union = d2.minus(d4);
            if (union == null) {
                System.out.println("Not possible:" + " column counts (" + d2.getColCount() + " vs " + d4.getColCount() + ").");
            } else {
                System.out.println("Result rows: " + (union.getRowCount() - 1));
                System.out.println();
                union.printTable(rowLimit);
            }
        }
        System.out.println();        

    }

    /**
     * findTable
     * Find the first table in the list whose name matches the given name.
     */
    private static Table findTable(ArrayList<Table> tables, String name) {
        for (Table t : tables) {
            if (t.getName().equals(name))
                return t;
        }
        System.out.println("Unknown table: " + name);

        return null;
    }
}