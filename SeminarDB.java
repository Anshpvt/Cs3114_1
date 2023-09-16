/**
 * Project 1
 */

/**
* The SeminarDB class provides functionalities to manage and operate on 
 * a database of seminars. It utilizes a hash table to efficiently manage 
 * records, and a memory manager to handle storage of large data.
 * The class allows for the insertion, deletion, and search of seminars 
 * based on a unique key.
 *
 * @author {Stephen Ye, Ansh Patel}
 * @version {08/28/23}
 */

// On my honor:
// - I have not used source code obtained from another current or
// former student, or any other unauthorized source, either
// modified or unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.
public class SeminarDB {

    private HashTable hash;
    private MemManager memManager;

    /**
     * Constructs a new SeminarDB with specified memory size and hash size.
     * @param memorySize The size of memory allocated for the memory manager.
     * @param hashSize The size of the hash table.
     */
    public SeminarDB(int memorySize, int hashSize) {

        this.memManager = new MemManager(memorySize);
        this.hash = new HashTable(hashSize);
    }

    /**
     * Retrieves the hash table used in this SeminarDB.
     * @return The hash table.
     */
    public HashTable getHash() {
        return hash;
    }

    /**
     * Checks if a record with the given key is successfully inserted into the hash table.
     * @param key The unique key of the record.
     * @param rec The record to be inserted.
     * @return true if the insertion is successful; false otherwise.
     */
    public boolean isInserted(int key, Record rec) {
        return hash.insert(key, rec);
    }

    /**
     * Inserts a record into the database. Resizes the hash table if it's full.
     * Serializes the seminar data and manages memory storage using the memory manager.
     * @param rec The record to be inserted.
     */
    public void insert(Record rec) {
        int start = hash.getCapacity();
        int key = rec.getKey();

        if (hash.isFull(key, rec)) {
            hash.resize();
        }
        if (!isInserted(key, rec)) {
            System.out.println(
                "Insert FAILED - There is already a record with ID " + key);
        }
        else {
            Seminar sem = rec.getSeminar();
            System.out.println("Successfully inserted record with ID " + key);
            System.out.println(sem.toString());

            byte[] byteArr;
            try {
                byteArr = sem.serialize();
                System.out.println("Size: " + byteArr.length);
                int memoryNeeded = byteArr.length; // assuming an average of 10
                                                   // characters per keyword
                Handle address = memManager.insert(byteArr, memoryNeeded);
                if (address == null) { // we dont have enough memory

                    System.out.println(
                        "Not enough memory to store seminar with ID " + key);
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Checks if a record with the given key is successfully deleted from the hash table.
     * @param key The unique key of the record to be deleted.
     * @return true if the deletion is successful; false otherwise.
     */
    public boolean isDeleted(int key) {
        return hash.delete(key);
    }

    /**
     * Deletes a record with the given key from the database.
     * @param key The unique key of the record to be deleted.
     */
    public void delete(int key) {

        if (!isDeleted(key)) {
            System.out.println("Delete FAILED -- There is no record with ID "
                + key);
        }
        else {
            System.out.println("Record with ID " + key
                + " successfully deleted from the database");

        }

    }

    /**
     * Searches for a record with the given key in the database and prints its details.
     * @param key The unique key of the record to be searched.
     */
    public void search(int key) {
        Record rec = hash.search(key);
        if (rec == null || rec.isTombstone()) {
            System.out.println("Search FAILED -- There is no record with ID "
                + key);
        }
        else {
            System.out.println("Found record with ID " + key + ": ");
            System.out.println(rec.getSeminar().toString());
        }
    }

    /**
     * Prints the current state of the hash table.
     */
    public void printHash() {
        System.out.println("Hashtable: ");
        hash.print();
    }

    /**
     * Prints the current state of memory blocks managed by the memory manager.
     */
    public void printBlock() {
        memManager.printBlocks();
        memManager.printList();
    }
}
