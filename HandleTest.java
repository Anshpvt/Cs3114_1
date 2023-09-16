    
import student.TestCase;

public class HandleTest extends TestCase {

    private Handle handle;

    public void setUp() {
        // Setting up a sample handle
        handle = new Handle(1, 10, 100);
    }


    public void testGetID() {
        // Testing getID() method
        assertEquals(1, handle.getID());
    }


    public void testGetStartLocation() {
        // Testing getStartLocation() method
        assertEquals(10, handle.getStartLocation());
    }


    public void testGetLength() {
        // Testing getLength() method
        assertEquals(100, handle.getLength());
    }


    public void testGetEnd() {
        Handle handle = new Handle(1, 5, 10);
        int expectedEnd = 14; // 5 + 10 - 1
        assertEquals(expectedEnd, handle.getEnd());
    }


    public void testToString() {
        Handle handle = new Handle(1, 5, 10);
        String expectedString = "5 : 15";
        assertEquals(expectedString, handle.toString());
    }


    public void testGetEndWithZeroLength() {
        Handle handle = new Handle(1, 5, 0);
        int expectedEnd = 4;
        assertEquals(expectedEnd, handle.getEnd());
    }


    public void testToStringWithZeroLength() {
        Handle handle = new Handle(1, 5, 0);
        String expectedString = "5 : 5";
        assertEquals(expectedString, handle.toString());
    }
}
