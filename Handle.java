/**
 * The Handle class represents a unique identifier (handle) for records.
 * It provides a simple way to retrieve and manage the ID of records.
 */
public class Handle {
    
    // Instance variable to store the unique identifier
    private int id;

    /**
     * Constructor to initialize a new Handle object with a given ID.
     *
     * @param id Unique identifier for the handle.
     */
    public Handle(int id) {
        this.id = id;
    }

    /**
     * Retrieves the unique identifier (ID) of the handle.
     * 
     * @return The ID of the handle.
     */
    public int getID() {
        return id;
    }
}
