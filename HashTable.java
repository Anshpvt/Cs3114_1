import java.io.*;
public class HashTable {
    
    private static Record[] records;
    private static final int INITIAL_SIZE = 100; // Choose an appropriate initial size

    public HashTable() {
        records = new Record[INITIAL_SIZE];
    }

    public static boolean insert(int id, String title, String time, String length, String x, String y, String cost, String list, String des) {
        // Check if the record with given id already exists
        for (int i = 0; i < records.length; i++) {
            if (records[i] != null && records[i].getHandle().getID() == id) {
                System.out.println("Insert FAILED - There is already a record with ID " + id);
                return false;
            }
        }
        // Insert the new record
        for (int i = 0; i < records.length; i++) {
            if (records[i] == null) {
                records[i] = new Record(id, title, time, length, x, y, cost, list, des);
                System.out.println("Successfully inserted record with ID " + id);
                return true;
            }
        }
        // Consider resizing the hash table if it's full
        return false;
    }

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

    public static String search(int id) {
        for (int i = 0; i < records.length; i++) {
            if (records[i] != null && records[i].getHandle().getID() == id) {
                return records[i].toString();
            }
        }
        return null;
    }

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
