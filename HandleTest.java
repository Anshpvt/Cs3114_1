import static org.junit.Assert.*;

public class HandleTest {

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
}
