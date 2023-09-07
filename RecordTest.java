import static org.junit.Assert.*;


public class RecordTest {

    private Record record;

    public void setUp() {
        record = new Record(1, "TestTitle", "12:30", "1hr", "5", "10", "$100", "test1,test2", "This is a test description.");
    }
    
    public void testGetHandle() {
        Handle handle = record.getHandle();
        assertNotNull(handle);
        assertEquals(1, handle.getID());
    }

    public void testToString() {
        String expected = "ID: 1, Title: TestTitle, Time: 12:30, Length: 1hr, X: 5, Y: 10, Cost: $100, List: test1,test2, Description: This is a test description.";
        assertEquals(expected, record.toString());
    }
}
