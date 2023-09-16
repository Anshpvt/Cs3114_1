
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
        String expectedOutput = "32 : 63";

        assertEquals(expectedOutput, outContent.toString().trim());
    }
    
    public void testGetLength() {
        Handle testHandle = new Handle(1, 10, 20);
        int length = memManager.getLength(testHandle);
        assertEquals(20, length);
    }
    
    public void testResize() {
        byte[] space = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        Handle handle1 = memManager.insert(space, space.length);
        Handle handle2 = memManager.insert(space, space.length);
        Handle handle3 = memManager.insert(space, space.length);
        Handle handle4 = memManager.insert(space, space.length);
        
        memManager.resize(128);
        assertTrue(memManager.getFreeBlocks().size() > 0);
    }
    
    public void testTakenBlocksNotEmpty() {
        byte[] space = {10, 20, 30, 40};
        memManager.insert(space, space.length);
        memManager.resize(64);
    }
    
    public void testTakenBlocksEmpty() {
        memManager.resize(64);
    }
    
    public void testGetMemoryPool() {
        // Initial test to check for a fresh memory pool.
        byte[] initialPool = memManager.getMemoryPool();
        assertNotNull(initialPool); // Ensure the memory pool is not null.
        assertEquals(32, initialPool.length); // Ensure it's the right size.
        for (byte b : initialPool) {
            assertEquals(0, b); // Ensure all bytes in the initial pool are 0.
        }

        // Insert some data into the memory pool.
        byte[] space = {10, 20, 30, 40};
        memManager.insert(space, space.length);
        
        // Fetch the memory pool again.
        byte[] modifiedPool = memManager.getMemoryPool();
        assertNotNull(modifiedPool);
        assertEquals(32, modifiedPool.length);
    }

    public void testRemove() {
        byte[] space = {10, 20, 30, 40};
        Handle handle = memManager.insert(space, space.length);

        // Ensure insertion was successful.
        assertNotNull(handle);
        assertTrue(memManager.isAllocated(handle));
        LinkedList initialFreeBlocks = memManager.getFreeBlocks();
    }
    

    public void testGet() {
        byte[] space = {10, 20, 30, 40};
        Handle handle = memManager.insert(space, space.length);
        byte[] result = new byte[space.length];
        int bytesRead = memManager.get(result, handle, space.length);
        assertEquals(bytesRead, space.length);
        for (int i = 0; i < space.length; i++) {
            assertEquals(space[i], result[i]);
        }
    }

    public void testFindBlockSize() {
        assertEquals(8, memManager.findBlockSize(5));
        assertEquals(16, memManager.findBlockSize(9));
        assertEquals(32, memManager.findBlockSize(17));
    }
    
    public void testGetTakenBlocks() {
        LinkedList takenBlocksInitial = memManager.getTakenBlocks();
        assertTrue(takenBlocksInitial.isEmpty());
        byte[] space = {10, 20, 30, 40};
        memManager.insert(space, space.length);
        LinkedList takenBlocksAfterInsert = memManager.getTakenBlocks();
        assertFalse(takenBlocksAfterInsert.isEmpty());
    }
    
    public void testMerge() {
        // Assuming each byte represents a block for simplicity.

        // Insert four blocks of size 1.
        byte[] space1 = {10};
        Handle handle1 = memManager.insert(space1, space1.length);

        byte[] space2 = {20};
        Handle handle2 = memManager.insert(space2, space2.length);

        byte[] space3 = {30};
        Handle handle3 = memManager.insert(space3, space3.length);

        byte[] space4 = {40};
        Handle handle4 = memManager.insert(space4, space4.length);

        // Now we remove two adjacent blocks.
        memManager.remove(handle2);
        memManager.remove(handle3);

        // At this point, the two blocks handle2 and handle3 were adjacent 
        // and of the same size. They should be merged.

        LinkedList freeBlocksAfterMerge = memManager.getFreeBlocks();

        // Ensure merged block exists and is of size 2.
        boolean foundMergedBlock = false;
        Node currentNode = freeBlocksAfterMerge.getHead();
        while (currentNode != null) {
            Handle freeBlock = currentNode.handle;
            if (freeBlock.getStartLocation() == handle2.getStartLocation() &&
                freeBlock.getLength() == 2) {
                foundMergedBlock = true;
                break;
            }
            currentNode = currentNode.next;
        }
    }
    
    public void testPrintBlocks() {
        byte[] space1 = {10, 20};
        memManager.insert(space1, space1.length);
        byte[] space2 = {30, 40};
        memManager.insert(space2, space2.length);
        memManager.printBlocks();
        assertTrue(outContent.toString().contains("Freeblock List:"));
        outContent.reset();
    }
    
    public void testPrintList() {
        byte[] space1 = {10, 20};
        memManager.insert(space1, space1.length);
        byte[] space2 = {30, 40};
        memManager.insert(space2, space2.length);
        memManager.printList();
        assertTrue(outContent.toString().contains("in use Block:"));
        outContent.reset();
    }
}

