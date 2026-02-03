
# Assignment 1: Table and Row Classes

## Overview
Implement a two-dimensional table structure using `Row` objects stored in an `ArrayList`.

## Class: Row

### Instance Variables
- `id` (int)
- `text` (String)

### Methods
- Constructor: `Row(int id, String text)`
- Getters/Setters: `getId()`, `setId()`, `getText()`, `setText()`
- `toString()` - returns string representation
- `compareTo(Row other)` - implements Comparable interface

---

## Class: Table

### Instance Variables
- `rows` (ArrayList<Row>)

### Methods
- Constructor: `Table()`
- Getters/Setters for instance variables
- `addRow(String s)` - adds a new Row to the table
- `getRowCount()` - returns number of rows
- `printTable(int r)` - prints first r rows (0 prints all)
- `sort()` - sorts by natural ordering (Comparable)
- `sort(Comparator<Row> comparator)` - sorts by custom comparator
- `select(String s)` - returns new Table containing rows with string s
