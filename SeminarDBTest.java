import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SeminarDBTest {

    private Record testRecord;
    private HashTable hashTable;

    @Before
    public void setup() {
        testRecord = new Record(1, "Test Title", "10:00", "1hr", "5", "6", "$100", "test,example", "Test description.");
        hashTable = new HashTable();  // Instantiate a new HashTable for each test
    }

    @Test
    public void testInsert() {
        boolean wasInserted = HashTable.insert(
            testRecord.getHandle().getID(),
            testRecord.getTitle(),
            testRecord.getTime(),
            testRecord.getLength(),
            testRecord.getX(),
            testRecord.getY(),
            testRecord.getCost(),
            testRecord.getList(),
            testRecord.getDescription()
        );

        assertTrue(wasInserted);
        assertNotNull(HashTable.search(testRecord.getHandle().getID()));
    }

    @Test
    public void testDelete() {
        HashTable.insert(
            testRecord.getHandle().getID(),
            testRecord.getTitle(),
            testRecord.getTime(),
            testRecord.getLength(),
            testRecord.getX(),
            testRecord.getY(),
            testRecord.getCost(),
            testRecord.getList(),
            testRecord.getDescription()
        );

        boolean wasDeleted = HashTable.delete(testRecord.getHandle().getID());

        assertTrue(wasDeleted);
        assertNull(HashTable.search(testRecord.getHandle().getID()));
    }

    @Test
    public void testSearchFound() {
        HashTable.insert(
            testRecord.getHandle().getID(),
            testRecord.getTitle(),
            testRecord.getTime(),
            testRecord.getLength(),
            testRecord.getX(),
            testRecord.getY(),
            testRecord.getCost(),
            testRecord.getList(),
            testRecord.getDescription()
        );

        String result = HashTable.search(testRecord.getHandle().getID());

        assertNotNull(result);
        assertTrue(result.contains(testRecord.getTitle()));
    }

    @Test
    public void testSearchNotFound() {
        String result = HashTable.search(999);  // Assuming the record with ID 999 does not exist

        assertNull(result);
    }

    @Test
    public void testPrintHashTable() {
        // Insert the test record
        HashTable.insert(
            testRecord.getHandle().getID(),
            testRecord.getTitle(),
            testRecord.getTime(),
            testRecord.getLength(),
            testRecord.getX(),
            testRecord.getY(),
            testRecord.getCost(),
            testRecord.getList(),
            testRecord.getDescription()
        );

        // Call the print method for hashtable
        String result = HashTable.print();
        
        // Check if the result string contains our inserted record's details
        assertTrue(result.contains(testRecord.toString()));
    }
}
