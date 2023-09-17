/**
 * Project 1
 */

/**
 * The HashTable class represents a hash table data structure 
 * which allows for insertion, search, and deletion of records.
 * This implementation uses open addressing with double hashing 
 * as a collision resolution technique.
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
public class HashTable {

    private int size;
    private Record[] hash;
    private int currSize; // number of current elements in the hash hash
    private static final Record TOMBSTONE = new Record(-1, null);

    /**
     * Constructor for the hash table.
     * @param size Initial size of the hash table
     */
    public HashTable(int size) {
        hash = new Record[size];
        currSize = 0;
        this.size = size;
    }

    /**
     * First hash function. Computes the position of a key.
     * @param key Key to be hashed
     * @return Hashed value
     */
    private int h1(int key) {
        return key % hash.length;
    }

    /**
     * Second hash function. Helps in double hashing.
     * @param key Key to be hashed
     * @return Hashed value
     */
    private int h2(int key) {
        return (key % (hash.length - 1));
    }

    /**
     * Checks if the hash table is approaching its capacity.
     * @param key Key of the record
     * @param record The record to be checked
     * @return true if hash table is almost full, false otherwise
     */
    public boolean isFull(int key, Record record) {
        if (currSize * 2 >= size) { // Check for load factor > 0.5 and
            // resize
            return true;
        }
        return false;
    }

    /**
     * Inserts a record into the hash table.
     *
     * @param key Key of the record
     * @param record The record to be inserted
     * @return true if insertion is successful, false otherwise
     */
    public boolean insert(int key, Record record) {

        int home = 0;;
        int pos = home;
        pos = h1(key);
        while ((hash[pos] != null)) {
            if (key == hash[pos].getKey()) {
                return false;
            }
            if (hash[pos].getKey() == -1) {
                hash[pos] = record;
                currSize++;
                return true;
            }
            pos = (pos + h2(key)) % size;
        }
        hash[pos] = record;
        currSize++;
        return true;
    }

    /**
     * Searches for a record in the hash table based on a key.
     * @param key Key of the record to be searched
     * @return The record if found, null otherwise
     */
    public Record search(int key) {
        int index = h1(key);
        int probeStep = h2(key);
        while (hash[index] != null) {
            if (hash[index].getKey() == key) {
                return hash[index];
            }
            index = (index + probeStep) % hash.length;
        }
        return null; // not found
    }

    /**
     * Deletes a record from the hash table.
     * @param key Key of the record to be deleted
     * @return true if deletion is successful, false otherwise
     */
    public boolean delete(int key) {
        int index = h1(key);
        int probeStep = h2(key);
        while (hash[index] != null) {
            if (hash[index].getKey() == key) {
                hash[index] = TOMBSTONE;
                currSize--;
                return true;
            }
            index = (index + probeStep) % hash.length;
        }
        return false;
    }

    /**
     * Resizes the hash table to accommodate more records.
     */
    public void resize() {
        Record[] oldTable = hash;
        hash = new Record[oldTable.length * 2];
        currSize = 0;
        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i] != null && oldTable[i] != TOMBSTONE) {
                this.insert(oldTable[i].getKey(), oldTable[i]);
            }
        }
        size = hash.length;
        System.out.println("Hash table expanded to " + hash.length
            + " records");
    }

    /**
     * Retrieves the capacity of the hash table.
     * @return Capacity of the hash table
     */
    public int getCapacity() {
        return size;
    }

    /**
     * Prints the contents of the hash table.
     */
    public void print() {
        int count = 0; // Counter for printed records

        for (int i = 0; i < hash.length; i++) {
            if (hash[i] != null && hash[i] != TOMBSTONE) {
                System.out.println(i + ": " + hash[i].getKey());
                count++;
            }
            else if (hash[i] == TOMBSTONE) {
                System.out.println(i + ": TOMBSTONE");
            }
        }
        System.out.println("total records: " + count);

    }
}
