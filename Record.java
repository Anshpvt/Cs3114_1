/**
 * Represents a single record entry in a database or data structure.
 * This record includes various attributes such as ID, title, time,
 * length, coordinates, cost, a list of keywords, and a description.
 * For simplicity, this version uses an integer key and a general object for value, 
 * but in a more comprehensive version, those attributes mentioned would be individually represented.
 */
public class Record {

    // The unique identifier for this record. Could represent the ID.
    private int key;

    // Represents the data associated with this record. Could be any type of object
    // encapsulating attributes like title, time, length, coordinates, cost, keywords, etc.
    private Object value;

    /**
     * Constructor to initialize a record with a given key and associated value.
     * 
     * @param key   The unique identifier for the record.
     * @param value The associated data of the record.
     */
    public Record(int key, Object value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Retrieves the key of this record.
     * 
     * @return The key of the record.
     */
    public int getKey() {
        return key;
    }

    /**
     * Retrieves the associated data of this record.
     * 
     * @return The value of the record.
     */
    public Object getValue() {
        return value;
    }

    /**
     * Provides a string representation of the record.
     * 
     * @return A string showing the key and value of the record.
     */
    @Override
    public String toString() {
        return "Record{" + "key=" + key + ", value=" + value + '}';
    }
}
