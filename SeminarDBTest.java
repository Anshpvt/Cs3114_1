
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import student.TestCase;

public class SeminarDBTest extends TestCase {

    private SeminarDB db;

    public void setUp() {
        db = new SeminarDB(1000, 10); // Initialize with arbitrary values
    }

    // Test: Insertion of records
    public void testInsert() throws Exception {
        String[] keywords1 = {"AI", "ML"};
        Record r1 = new Record(1, new Seminar(1, "Intro to AI", "2023-10-01", 60, (short)100, (short)50, 200, keywords1, "An introduction to AI"));
        
        // Capture the System.out output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        db.insert(r1);
        assertTrue(outContent.toString().contains("Successfully inserted record with ID 1"));

        // Attempting to insert a duplicate should produce an error message
        db.insert(r1);
        assertTrue(outContent.toString().contains("Insert FAILED - There is already a record with ID 1"));
    }

    // Test: Deletion of records
    public void testDelete() throws Exception {
        String[] keywords2 = {"Data Science", "Stats"};
        Record r2 = new Record(2, new Seminar(2, "Data Analysis", "2023-11-10", 120, (short)150, (short)60, 250, keywords2, "An in-depth session on Data Analysis"));
        db.insert(r2);

        // Capture the System.out output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        db.delete(2);
        assertTrue(outContent.toString().contains("Record with ID 2 successfully deleted from the database"));

        // Trying to delete a non-existent record should produce an error message
        db.delete(100);
        assertTrue(outContent.toString().contains("Delete FAILED -- There is no record with ID 100"));
    }

    // Test: Searching for records
    public void testSearch() throws Exception {
        String[] keywords3 = {"Networking", "Security"};
        Record r3 = new Record(3, new Seminar(3, "Network Security", "2023-12-15", 90, (short)200, (short)70, 300, keywords3, "A workshop on Network Security"));
        db.insert(r3);

        // Capture the System.out output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        db.search(3);
        assertTrue(outContent.toString().contains("Found record with ID 3"));

        db.search(100);
        assertTrue(outContent.toString().contains("Search FAILED -- There is no record with ID 100"));
    }

    // Test: Printing HashTable
    public void testPrintHash() throws Exception {
        // Insert a record for testing purposes
        String[] keywords = {"Databases", "SQL"};
        Record r = new Record(4, new Seminar(4, "SQL Basics", "2023-11-11", 60, (short)100, (short)60, 200, keywords, "Introduction to SQL"));
        db.insert(r);

        // Capture the System.out output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        db.printHash();
        assertTrue(outContent.toString().contains("Hashtable:"));
    }

    // Test: Printing Block
    public void testPrintBlock() throws Exception {
        // Capture the System.out output
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        db.printBlock();
        assertTrue(outContent.toString().contains("Freeblock List:"));
    }
    
    public void testGetHash() {
        HashTable hashTable = db.getHash();


        assertNotNull(hashTable);

        
        assertEquals(10, hashTable.getCapacity());

    }
    
    
}
