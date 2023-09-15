
import student.TestCase;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MemManagerTest extends TestCase{

    private MemManager memManager;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    public void setUp() {
        memManager = new MemManager(32);  // Set up with initial pool size of 32 bytes.
        
        // Redirect System.out to outContent for tests related to methods that print.
        System.setOut(new PrintStream(outContent));
    }

    public void testInsert() {
        byte[] space = {10, 20, 30, 40};
        Handle handle = memManager.insert(space, space.length);
        
        assertNotNull(handle);
        assertTrue(memManager.isAllocated(handle));
    }


    public void testDump() {
        memManager.dump();

        // Here you'll need to specify the expected initial state of the memory pool for a size of 32 bytes.
        String expectedOutput = "32 : 63";

        assertEquals(expectedOutput, outContent.toString().trim());
    }
    
}
