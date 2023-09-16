import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import student.TestCase;

public class HashTableTest extends TestCase {

    private HashTable hashTable;

    public void setUp() {
        hashTable = new HashTable(10);
    }


    public void testInsert() {

        assertTrue(hashTable.insert(1, new Record(1, null)));
        assertFalse(hashTable.insert(1, new Record(1, null))); // Test for
                                                               // duplicate
    }


    public void testInsert1() {
        assertTrue(hashTable.insert(1, new Record(1, null)));
        assertTrue(hashTable.delete(1));
        assertTrue(hashTable.insert(1, new Record(1, null))); // Test for
                                                              // duplicate
    }


    public void testInsertWithDoubleHashing() {
        int initialSize = hashTable.getCapacity();

        int key1 = 5;
        assertTrue(hashTable.insert(key1, new Record(key1, null)));
        int key2 = key1 + initialSize;
        assertTrue(hashTable.insert(key2, new Record(key2, null)));
        assertNotNull(hashTable.search(key1));
        assertNotNull(hashTable.search(key2));
    }


    public void testIsFull() {
        for (int i = 0; i < 4; i++) {
            hashTable.insert(i, new Record(i, null));
        }
        assertFalse(hashTable.isFull(5, new Record(5, null)));
        hashTable.insert(5, new Record(5, null));
        assertTrue(hashTable.isFull(6, new Record(6, null)));
    }


    public void testSearch() {
        assertNull(hashTable.search(1));
        hashTable.insert(1, new Record(1, null));
        assertNotNull(hashTable.search(1));
    }


    public void testDelete() {
        assertFalse(hashTable.delete(1));
        hashTable.insert(1, new Record(1, null));
        assertTrue(hashTable.delete(1));
    }


    public void testDeleteWithDoubleHashing() {
        int initialSize = hashTable.getCapacity();

        int key1 = 5;
        assertTrue(hashTable.insert(key1, new Record(key1, null)));
        int key2 = key1 + initialSize;
        assertTrue(hashTable.insert(key2, new Record(key2, null)));
        assertTrue(hashTable.delete(key1));
        assertTrue(hashTable.delete(key2));
        assertNull(hashTable.search(key1));
        assertNull(hashTable.search(key2));
    }


    public void testResize() {
        for (int i = 0; i < 6; i++) {
            hashTable.insert(i, new Record(i, null));
        }
        int oldCapacity = hashTable.getCapacity();
        hashTable.resize();
        int newCapacity = hashTable.getCapacity();
    }


    public void testResizeBranches() {
        hashTable.insert(0, new Record(0, null));
        hashTable.delete(0);
        hashTable.insert(1, new Record(1, null));
        int oldCapacity = hashTable.getCapacity();
        hashTable.resize();
        int newCapacity = hashTable.getCapacity();
        assertNull(hashTable.search(0));

        assertNotNull(hashTable.search(1));
    }


    public void testGetCapacity() {
        assertEquals(10, hashTable.getCapacity());
        hashTable.resize();
        assertEquals(20, hashTable.getCapacity());
    }


    public void testPrint() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        hashTable.insert(1, new Record(1, null));
        hashTable.print();
        assertTrue(outContent.toString().contains("1: 1"));
        assertTrue(outContent.toString().contains("total records: 1"));

    }


    public void testPrint1() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Insert a record
        hashTable.insert(1, new Record(1, null));

        // Delete the record to simulate placing a TOMBSTONE
        hashTable.delete(1);

        // Call the print method
        hashTable.print();

        // Check the output
        assertTrue(outContent.toString().contains("1: TOMBSTONE"));
        assertTrue(outContent.toString().contains("total records: 0")); 
    }
}
