/**
 * The Handle class represents a unique identifier (handle) for records.
 * It not only stores the unique ID but also the start location and length
 * of the message in the memory manager. This ensures efficient and accurate
 * access to the data stored in memory.
 */
public class Handle {

    // Unique identifier for the handle.
    private int id;

    // Start location of the message in the memory manager.
    private int startLocation;

    // Length of the message in the memory manager.
    private int length;

    /**
     * Constructor to initialize a new Handle object with a given ID, start location, and length.
     *
     * @param id
     * Unique identifier for the handle.
     * 
     * @param startLocation
     * Start location of the message in the memory manager.
     * 
     * @param length
     * Length of the message in the memory manager.
     */
    public Handle(int id, int startLocation, int length) {
        this.id = id;
        this.startLocation = startLocation;
        this.length = length;
    }

    /**
     * Retrieves the unique identifier (ID) of the handle.
     * 
     * @return The ID of the handle.
     */
    public int getID() {
        return id;
    }

    /**
     * Retrieves the start location of the message in the memory manager.
     * 
     * @return The start location.
     */
    public int getStartLocation() {
        return startLocation;
    }

    /**
     * Retrieves the length of the message in the memory manager.
     * 
     * @return The length of the message.
     */
    public int getLength() {
        return length;
    }
}
