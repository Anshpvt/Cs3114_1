import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SeminarDBTest {

    private Record testRecord;

    @Before
    public void setup() {
        testRecord = new Record(1, "Test Title", "10:00", "1hr", "5", "6", "$100", "test,example", "Test description.");
        // Possibly reset or clear the HashTable here, if needed.
    }

    @Test
    public void testInsert() {
        // Insert using the Record's details
        SeminarDB.insert(
            testRecord.getHandle().getId(),
            testRecord.getTitle(),
            testRecord.getTime(),
            testRecord.getLength(),
            testRecord.getX(),
            testRecord.getY(),
            testRecord.getCost(),
            testRecord.getList(),
            testRecord.getDescription()
        );
        
        assertTrue(HashTable.hasRecord(testRecord.getHandle().getId()));
    }

    @Test
    public void testDelete() {
        // First insert a record to delete
        SeminarDB.insert(
            testRecord.getHandle().getId(),
            testRecord.getTitle(),
            testRecord.getTime(),
            testRecord.getLength(),
            testRecord.getX(),
            testRecord.getY(),
            testRecord.getCost(),
            testRecord.getList(),
            testRecord.getDescription()
        );
        
        // Now delete it
        SeminarDB.delete(testRecord.getHandle().getId());
        
        assertFalse(HashTable.hasRecord(testRecord.getHandle().getId()));
    }

    @Test
    public void testSearchFound() {
        // Insert a record to search for
        SeminarDB.insert(
            testRecord.getHandle().getId(),
            testRecord.getTitle(),
            testRecord.getTime(),
            testRecord.getLength(),
            testRecord.getX(),
            testRecord.getY(),
            testRecord.getCost(),
            testRecord.getList(),
            testRecord.getDescription()
        );
        
        SeminarDB.search(testRecord.getHandle().getId());
        
        assertTrue(HashTable.hasRecord(testRecord.getHandle().getId()));
    }

    @Test
    public void testSearchNotFound() {
        // Assuming the record with ID 999 does not exist
        SeminarDB.search(999);
        
        assertFalse(HashTable.hasRecord(999));
    }

    @Test
    public void testPrintHashTable() {
        // Call the print method for hashtable
        SeminarDB.print("hashtable");
        
        // This method would print the contents of the hash table to the console.
        // Without capturing the console output, you'd instead verify some other state of the HashTable.
        assertTrue(HashTable.size() > 0);
    }

    @Test
    public void testPrintUnknownType() {
        SeminarDB.print("unknown");
    }
}

