import java.io.File;


import java.io.FileNotFoundException;
import java.util.Scanner;


public class SeminarDB {
    
    public SeminarDB() {
        // Possibly initialize the HashTable or other internal structures here.
    }

    public static void insert(int id, String title, String time, String length, String x, String y, String cost, String list, String des) {
        // Assuming HashTable.insert() returns true if the insertion is successful
        boolean wasInserted = HashTable.insert(id, title, time, length, x, y, cost, list, des);
        
        if (wasInserted) {
            System.out.println("Successfully inserted record with ID " + id);
        } else {
            // Modify this message if you have a more specific reason for insertion failure
            System.out.println("Insertion failed for record with ID " + id);
        }
    }
    
    public static void delete(int id) {
        // Assuming HashTable.delete() returns true if the deletion is successful
        boolean wasDeleted = HashTable.delete(id);
        
        if (wasDeleted) {
            System.out.println("Record with ID " + id + " successfully deleted from the database");
        } else {
            System.out.println("Delete FAILED -- There is no record with ID " + id);
        }
    }

    public static void search(int id) {
        // Assuming HashTable.search() returns the record if found, null otherwise
        String record = HashTable.search(id);

        if (record != null) {
            System.out.println("Found record with ID " + id + ": " + record);
        } else {
            System.out.println("Record with ID " + id + " not found");
        }
    }

    public static void print(String type) {
        // Assuming HashTable.print() returns the entire contents, and there's a method to print free blocks as well
        if ("hashtable".equalsIgnoreCase(type)) {
            String content = HashTable.print();
            System.out.println(content);
        } else if ("block".equalsIgnoreCase(type)) {
            // Print the free block list. You can modify this to use a method you have.
            System.out.println("Freeblock list");
        } else {
            System.out.println("unreconized input " + type);
        }
    }
}
