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

    private Seminar sem;

    /**
     * Constructor to initialize a record with a given key and associated value.
     * 
     * @param key   The unique identifier for the record.
     * @param value The associated data of the record.
     */
    public Record(int key, Seminar sem) {
        this.key = key;
        this.sem = sem;
    }

    /**
     * Retrieves the key of this record.
     * 
     * @return The key of the record.
     */
    public int getKey() {
        return key;
    }

    public Seminar getSeminar()
    {
    	return sem;
    }
    
    public boolean isTombstone()
    {
    	return key == -1;
    }
}
