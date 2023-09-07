import java.io.*;

/**
 * HashTable class represents a simple hash table data structure for storing and managing Record objects.
 */
public class HashTable {
    
    // An array of Record objects, serving as the underlying data storage for the hash table.
    private static Record[] records;

    // A constant representing the initial size of the hash table.
    private static final int INITIAL_SIZE = 100;

    /**
     * Default constructor that initializes the hash table with a default size.
     */
    public HashTable() {
        records = new Record[INITIAL_SIZE];
    }

    /**
     * Inserts a new record into the hash table.
     *
     * @param id The ID of the record.
     * @param title The title of the record.
     * @param time The time of the record.
     * @param length The length of the record.
     * @param x The x coordinate of the record.
     * @param y The y coordinate of the record.
     * @param cost The cost associated with the record.
     * @param list A list of keywords associated with the record.
     * @param des A description of the record.
     * @return boolean value indicating success (true) or failure (false) of the insertion.
     */
    public static boolean insert(int id, String title, String time, String length, String x, String y, String cost, String list, String des) {
        // Check if a record with the given ID already exists in the hash table.
        for (int i = 0; i < records.length; i++) {
            if (records[i] != null && records[i].getHandle().getID() == id) {
                System.out.println("Insert FAILED - There is already a record with ID " + id);
                return false;
            }
        }
        
        // Insert the new record into the first available slot.
        for (int i = 0; i < records.length; i++) {
            if (records[i] == null) {
                records[i] = new Record(id, title, time, length, x, y, cost, list, des);
                System.out.println("Successfully inserted record with ID " + id);
                return true;
            }
        }

        // Consider implementing resizing logic if the hash table is full.
        return false;
    }

    /**
     * Deletes a record with the given ID from the hash table.
     *
     * @param id The ID of the record to be deleted.
     * @return boolean value indicating success (true) or failure (false) of the deletion.
     */
    public static boolean delete(int id) {
        for (int i = 0; i < records.length; i++) {
            if (records[i] != null && records[i].getHandle().getID() == id) {
                records[i] = null;
                System.out.println("Record with ID " + id + " successfully deleted from the database");
                return true;
            }
        }
        System.out.println("Delete FAILED -- There is no record with ID " + id);
        return false;
    }

    /**
     * Searches for a record with the given ID in the hash table.
     *
     * @param id The ID of the record to search for.
     * @return A string representation of the found record, or null if the record is not found.
     */
    public static String search(int id) {
        for (int i = 0; i < records.length; i++) {
            if (records[i] != null && records[i].getHandle().getID() == id) {
                return records[i].toString();
            }
        }
        return null;
    }

    /**
     * Prints all the records currently stored in the hash table.
     *
     * @return A string representation of all the records in the hash table.
     */
    public static String print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < records.length; i++) {
            if (records[i] != null) {
                sb.append(records[i].toString()).append("\n");
            }
        }
        return sb.toString();
    }
}

