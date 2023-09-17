import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import student.TestCase;
/**
 * Project 1
 */

/**
 * This class contains tests for the HashTable class, ensuring all methods
 * operate as expected under various scenarios.
 *
 * @author {Stephen Ye, Ansh Patel}
 * @version {08/28/23}
 */

// On my honor:
// - I have not used source code obtained from another current or
// former student, or any other unauthorized source, either
// modified or unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.
public class HashTableTest extends TestCase {

    private HashTable hashTable;

    /**
     * sets up for tests
     */
    public void setUp() {
        hashTable = new HashTable(10);
    }

    /**
     * Tests the insert method for basic insertion and
     *  handling of duplicate keys.
     */
    public void testInsert() {

        assertTrue(hashTable.insert(1, new Record(1, null)));
        assertFalse(hashTable.insert(1, new Record(1, null)));
    }

    /**
     * Tests inserting a key after it has been deleted.
     */
    public void testInsert1() {
        assertTrue(hashTable.insert(1, new Record(1, null)));
        assertTrue(hashTable.delete(1));
        assertTrue(hashTable.insert(1, new Record(1, null))); // Test for
                                                              // duplicate
    }

    /**
     * Tests the insert method when using double hashing to resolve collisions.
     */
    public void testInsertWithDoubleHashing() {
        int initialSize = hashTable.getCapacity();

        int key1 = 5;
        assertTrue(hashTable.insert(key1, new Record(key1, null)));
        int key2 = key1 + initialSize;
        assertTrue(hashTable.insert(key2, new Record(key2, null)));
        assertNotNull(hashTable.search(key1));
        assertNotNull(hashTable.search(key2));
    }

    /**
     * Tests the isFull method for correct identification of table capacity.
     */
    public void testIsFull() {
        for (int i = 0; i < 4; i++) {
            hashTable.insert(i, new Record(i, null));
        }
        assertFalse(hashTable.isFull(5, new Record(5, null)));
        hashTable.insert(5, new Record(5, null));
        assertTrue(hashTable.isFull(6, new Record(6, null)));
    }

    /**
     * Tests the search method to check if inserted records can be found.
     */
    public void testSearch() {
        assertNull(hashTable.search(1));
        hashTable.insert(1, new Record(1, null));
        assertNotNull(hashTable.search(1));
    }

    /**
     * Tests the delete method for successfully removing records.
     */
    public void testDelete() {
        assertFalse(hashTable.delete(1));
        hashTable.insert(1, new Record(1, null));
        assertTrue(hashTable.delete(1));
    }

    /**
     * Tests the delete method when using double hashing to resolve collisions.
     */
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

    /**
     * Tests the resize method to ensure the table capacity doubles.
     */
    public void testResize() {
        for (int i = 0; i < 6; i++) {
            hashTable.insert(i, new Record(i, null));
        }
        int oldCapacity = hashTable.getCapacity();
        hashTable.resize();
        int newCapacity = hashTable.getCapacity();
    }

    /**
     * Tests the resize method's behavior with certain 
     * branches, ensuring tombstones
     * and valid records are maintained correctly.
     */
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

    /**
     * Tests the getCapacity method to ensure it reports 
     * the correct table capacity.
     */
    public void testGetCapacity() {
        assertEquals(10, hashTable.getCapacity());
        hashTable.resize();
        assertEquals(20, hashTable.getCapacity());
    }

    /**
     * Tests the print method to ensure the table's content 
     * is displayed correctly.
     */
    public void testPrint() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        hashTable.insert(1, new Record(1, null));
        hashTable.print();
        assertTrue(outContent.toString().contains("1: 1"));
        assertTrue(outContent.toString().contains("total records: 1"));

    }

    /**
     * Tests the print method for proper display of tombstones.
     */
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
