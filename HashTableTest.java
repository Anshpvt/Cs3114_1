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
        assertFalse(hashTable.insert(1, new Record(1, null))); // Test for duplicate
    }
    
    public void testIsFull() {
        for (int i = 0; i < 5; i++) {
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

    public void testResize() {
        for (int i = 0; i < 6; i++) {
            hashTable.insert(i, new Record(i, null));
        }
        int oldCapacity = hashTable.getCapacity();
        hashTable.resize();
        int newCapacity = hashTable.getCapacity();

        assertTrue(newCapacity > oldCapacity);
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
        assertTrue(outContent.toString().contains("4: 4"));
        assertTrue(outContent.toString().contains("total records: 3"));
    }
}
