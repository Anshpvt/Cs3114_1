import static org.junit.Assert.*;

public class HandleTest {

    private Handle handle;

    public void setUp() {
        handle = new Handle(1234);  // Initializing with a test ID of 1234.
    }

    public void testGetID() {
        int expectedID = 1234;
        assertEquals(expectedID, handle.getID());
    }

    public void testHandleObjectNotNull() {
        assertNotNull(handle);
    }

    public void testDifferentHandle() {
        Handle anotherHandle = new Handle(5678);
        assertNotEquals(anotherHandle.getID(), handle.getID());
    }
}
