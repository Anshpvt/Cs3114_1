/**
 * Represents a single record entry with a unique identifier (key) 
 * and associated data (value) along with various other attributes.
 */
public class Record {

    // The unique identifier for the record
    private int key;            

    // The value/payload/data associated with the key
    private String value;       

    // Additional attributes for the record
    private String title, time, length, x, y, cost, list, des;

    /**
     * Constructor to initialize a new Record object with given parameters.
     *
     * @param key Unique identifier for the record.
     * @param value The value/data associated with the key.
     * @param title Title of the record.
     * @param time Time associated with the record.
     * @param length Duration or length of the record.
     * @param x X-coordinate or other relevant data.
     * @param y Y-coordinate or other relevant data.
     * @param cost Cost associated with the record.
     * @param list A list of keywords or other data.
     * @param des Description of the record.
     */
    public Record(int key, String value, String title, String time, String length,
                  String x, String y, String cost, String list, String des) {
        this.key = key;
        this.value = value;
        this.title = title;
        this.time = time;
        this.length = length;
        this.x = x;
        this.y = y;
        this.cost = cost;
        this.list = list;
        this.des = des;
    }

    // Getter methods

    /**
     * @return The unique key of the record.
     */
    public int getKey() {
        return key;
    }

    /**
     * @return The value/data associated with the key.
     */
    public String getValue() {
        return value;
    }

    // ... Similarly for other getters ...

    /**
     * Calculates a hash code based on the key.
     * @return An integer hash code.
     */
    @Override
    public int hashCode() {
        return Integer.hashCode(key);  
    }

    /**
     * Checks for equality between this record and another object.
     * Two records are considered equal if their keys are the same.
     * @param obj The object to compare with.
     * @return True if the objects are the same or have the same key, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Record other = (Record) obj;
        return key == other.key;       
    }

    /**
     * Provides a detailed string representation of the record.
     * @return A string detailing all attributes of the record.
     */
    @Override
    public String toString() {
        return "Key: " + key + ", Value: " + value + ", Title: " + title 
            + ", Time: " + time + ", Length: " + length + ", X: " + x 
            + ", Y: " + y + ", Cost: " + cost + ", List: " + list 
            + ", Description: " + des;
    }
}
