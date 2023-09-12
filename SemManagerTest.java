import static org.junit.Assert.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class SeminarDBTest {

    private SeminarDB db;

    public void setUp() {
        db = new SeminarDB(1000, 10); // Initialize with arbitrary values
    }

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

    // Note: The printHash() and printBlock() methods would require a more complicated testing strategy
    // to check their exact outputs. A simple way to test them would be to redirect the output to a stream
    // and check if the output contains expected strings, similar to the above tests.
    // However, this is not done here for the sake of brevity.
}

