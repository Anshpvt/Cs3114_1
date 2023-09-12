import static org.junit.Assert.*;

public class HashTableTest {

    private HashTable hashTable;

    public void setUp() {
        hashTable = new HashTable(10); // Initializing with capacity 10 for the test
    }


    public void testInsert() {
        Record r1 = new Record(1, null); // Using null for the Seminar value just for testing
        assertTrue(hashTable.insert(1, r1));

        // Attempting to insert a duplicate key should return false
        assertFalse(hashTable.insert(1, r1));
    }

    public void testSearch() {
        Record r1 = new Record(2, null);
        hashTable.insert(2, r1);

        // Searching for a record that exists should return that record
        assertEquals(r1, hashTable.search(2));

        // Searching for a record that doesn't exist should return null
        assertNull(hashTable.search(100));
    }

    public void testDelete() {
        Record r2 = new Record(3, null);
        hashTable.insert(3, r2);

        // Deleting an existing record should return true
        assertTrue(hashTable.delete(3));

        // Once deleted, searching for the record should return null
        assertNull(hashTable.search(3));

        // Deleting a record that doesn't exist should return false
        assertFalse(hashTable.delete(100));
    }

    public void testResize() {
        for (int i = 0; i < 11; i++) {
            hashTable.insert(i, new Record(i, null));
        }
        // After inserting 11 records, the hash table should resize (since it would exceed a 0.5 load factor)
        assertEquals(20, hashTable.getCapacity());
    }
    
    public void testGetCapacity() {
        // Initial capacity should be 10 as set in setUp
        assertEquals(10, hashTable.getCapacity());
    }

    // Note: For the print method, it would be better to check the output by redirecting the console output
    // and comparing, but here it's not covered. Testing such methods typically requires different testing strategies.
}
