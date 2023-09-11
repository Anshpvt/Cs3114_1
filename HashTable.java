import java.io.*;

/**
 * HashTable class represents a simple hash table data structure for storing and managing Record objects.
 */
public class HashTable {
    
    // An array of Record objects, serving as the underlying data storage for the hash table.
    private Record[] records;
    private int size;
    private int currSize;

    /**
     * Default constructor that initializes the hash table with a default size.
     */
    public HashTable(int size) {
        records = new Record[size];
        this.size = size;
        this.currSize = 0;
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
    public boolean insert(int key, Record rec) {
       
    	if(currSize * 2 == size)
    	{
    		HashTable newHash = new HashTable(2 * size);
    		for (int i = 0; i < size; i++)
    		{
    			if (this.records[i].getId() != -1)
    			{
    				newHash.records[i] = this.records[i];
    			}
    			
    		}
    	}
    		
    	int start; // Home position for record
    	int pos = start = h1(id);// Init probe sequence
    	
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
    public boolean delete(int id) {
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
    public String search(int id) {
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
    public String print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < records.length; i++) {
            if (records[i] != null) {
                sb.append(records[i].toString()).append("\n");
            }
        }
        return sb.toString();
    }
    
    // not sure how to implement but i was advised to use something like this
    int h(int k) {
        // Compute a hash code for key k
        int hashcode = 

        // Map the hash code to a valid index in the hash table
        int position = hashcode % size;

        return position;
    }
    



    
   
}
