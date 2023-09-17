/**
 * Project 1
 */

/**
 * Represents a Record that contains a unique identifier (key) 
 * and associated Seminar data. This class provides methods to 
 * access the key, seminar data, and check if the record is a 
 * tombstone.
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
public class Record {

    // The unique identifier for this record. Could represent the ID.
    private int key;

    private Seminar sem;

    /**
     * Constructor to initialize a record with a given key and associated value.
     * @param key The unique identifier for the record.
     * @param sem The associated data of the record.
     */
    public Record(int key, Seminar sem) {
        this.key = key;
        this.sem = sem;
    }


    /**
     * Retrieves the key of this record.
     * @return The key of the record.
     */
    public int getKey() {
        return key;
    }

    /**
     * Retrieves the Seminar object associated with this record.
     * @return The Seminar object of the record.
     */
    public Seminar getSeminar() {
        return sem;
    }

    /**
     * Determines if the record represents a tombstone.
     * A tombstone is a marker indicating that a record has been deleted 
     * but the space is yet to be reclaimed. In this implementation, a 
     * tombstone is represented by a record with a key value of -1.
     * @return true if the record is a tombstone; false otherwise.
     */
    public boolean isTombstone() {
        return key == -1;
    }
}
