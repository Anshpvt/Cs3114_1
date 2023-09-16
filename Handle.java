/**
 * Project 1
 */

/**
 * The Handle class represents a data structure that provides 
 * an interface for managing messages within a memory manager. 
 * Each Handle object contains a unique identifier, a start location 
 * for the message within the memory manager, and the length of the message. 
 * Through this Handle class, one can retrieve and manage the 
 * information pertaining to these messages.
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
public class Handle {

    // Unique identifier for the handle.
    private int id;

    // Start location of the message in the memory manager.
    private int startLocation;

    // Length of the message in the memory manager.
    private int length;

    /**
     * Constructor to initialize a new Handle object with a given ID, start
     * location, and length.
     *
     * @param id Unique identifier for the handle.
     * @param startLocation Start location of the message in the memory manager.
     * @param length Length of the message in the memory manager.
     */
    public Handle(int id, int startLocation, int length) {
        this.id = id;
        this.startLocation = startLocation;
        this.length = length;
    }

    /**
     * Retrieves the unique identifier (ID) of the handle.
     * @return The ID of the handle.
     */
    public int getID() {
        return id;
    }

    /**
     * Retrieves the start location of the message in the memory manager.
     * @return The start location.
     */
    public int getStartLocation() {
        return startLocation;
    }

    /**
     * Retrieves the length of the message in the memory manager.
     * @return The length of the message.
     */
    public int getLength() {
        return length;
    }

    /**
     * Calculates and returns the end location of the message in the memory manager.
     * @return The end location.
     */
    public int getEnd() {
        return startLocation + length - 1;
    }

    /**
     * Generates and returns a string representation of the Handle, showing the start
     * and end locations of the message.
     * @return String representation of the Handle.
     */
    public String toString() {
        return getStartLocation() + " : " + (getStartLocation() + getLength());
    }
}
