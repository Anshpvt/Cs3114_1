import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class RecordTest {

    private Record record1;
    private Record record2;
    private Record record3;

    @Before
    public void setUp() {
        record1 = new Record(1, "Value1", "Title1", "Time1", "Length1", "X1", "Y1", "Cost1", "List1", "Description1");
        record2 = new Record(2, "Value2", "Title2", "Time2", "Length2", "X2", "Y2", "Cost2", "List2", "Description2");
        record3 = new Record(1, "Value3", "Title3", "Time3", "Length3", "X3", "Y3", "Cost3", "List3", "Description3");
    }

    @Test
    public void testGetKey() {
        assertEquals(1, record1.getKey());
        assertEquals(2, record2.getKey());
    }

    @Test
    public void testGetValue() {
        assertEquals("Value1", record1.getValue());
        assertEquals("Value2", record2.getValue());
    }

    @Test
    public void testEquals() {
        assertFalse(record1.equals(record2));
        assertTrue(record1.equals(record3)); // Because they have the same key
    }

    @Test
    public void testHashCode() {
        assertNotEquals(record1.hashCode(), record2.hashCode());
        assertEquals(record1.hashCode(), record3.hashCode());
    }

    @Test
    public void testToString() {
        String expectedString1 = "Key: 1, Value: Value1, Title: Title1, Time: Time1, Length: Length1, X: X1, Y: Y1, Cost: Cost1, List: List1, Description: Description1";
        assertEquals(expectedString1, record1.toString());
    }

    @Test
    public void testEqualityWithDifferentObject() {
        assertFalse(record1.equals(new String("Test")));
    }

    @Test
    public void testEqualityWithNull() {
        assertFalse(record1.equals(null));
    }

    // ... You can add more test cases as needed ...
}
