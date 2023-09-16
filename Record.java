import static org.junit.Assert.*;
import student.TestCase;

public class RecordTest extends TestCase{

    private Record record;
    private Seminar seminar;

    public void setUp() {
        // Setting up a sample seminar and record
        seminar = new Seminar(1, "Test Seminar", "2023-09-13", 120, (short)50, (short)60, 100, 
                              new String[] {"Java", "OOP"}, "A seminar about Java OOP.");
        record = new Record(1, seminar);
    }
    
    public void testGetKey() {
        // Testing getKey() method
        assertEquals(1, record.getKey());
    }
    
    public void testGetSeminar() {
        // Testing getSeminar() method
        assertEquals(seminar, record.getSeminar());
    }

    public void testIsTombstone() {
        // Testing isTombstone() method for normal record
        assertFalse(record.isTombstone());

        // Testing isTombstone() method for a tombstone record
        Record tombstone = new Record(-1, null);
        assertTrue(tombstone.isTombstone());
    }
}
