import student.TestCase;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
/**
 * Project 1
 */

/**
 * This class provides JUnit tests for the MemManager class.
 *
 * @author {Stephen Ye, Ansh Patel}
 * @version {08/28/23}
 */

// On my honor:
// - I have not used source code obtained from another current or
// former student, or any other unauthorized source, either
// modified or unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.
public class MemManagerTest extends TestCase {

    private MemManager memManager;
    private final ByteArrayOutputStream outContent =
        new ByteArrayOutputStream();

    /**
     * sets up tests
     */
    public void setUp() {
        memManager = new MemManager(32); // Set up with initial pool size of 32
                                         // bytes.

        // Redirect System.out to outContent for tests related to methods that
        // print.
        System.setOut(new PrintStream(outContent));
    }

    /**
     * Test to ensure that inserting bytes into the memory pool
     * returns a valid handle, and the space is properly allocated.
     */
    public void testInsert() {
        byte[] space = { 10, 20, 30, 40 };
        Handle handle = memManager.insert(space, space.length);

        assertNotNull(handle);
        assertTrue(memManager.isAllocated(handle));
    }

    /**
     * Test to ensure that the dump() method outputs
     * the expected string.
     */
    public void testDump() {
        memManager.dump();
        String expectedOutput = "32 : 63";

        assertEquals(expectedOutput, outContent.toString().trim());
    }

    /**
     * Test to check if getLength() returns the correct length
     * of a handle.
     */
    public void testGetLength() {
        Handle testHandle = new Handle(1, 10, 20);
        int length = memManager.getLength(testHandle);
        assertEquals(20, length);
    }

    /**
     * Test to verify that the memory pool can be resized correctly,
     * and free blocks exist post-resize.
     */
    public void testResize() {
        byte[] space = { 10, 20, 30, 40, 50, 60, 70, 80, 90, 100 };
        Handle handle1 = memManager.insert(space, space.length);
        Handle handle2 = memManager.insert(space, space.length);
        Handle handle3 = memManager.insert(space, space.length);
        Handle handle4 = memManager.insert(space, space.length);

        memManager.resize(128);
        assertTrue(memManager.getFreeBlocks().size() > 0);
    }

    /**
     * Test to ensure the resize operation works correctly when 
     * there are allocated blocks in the memory.
     */
    public void testTakenBlocksNotEmpty() {
        byte[] space = { 10, 20, 30, 40 };
        memManager.insert(space, space.length);
        memManager.resize(64);
    }

    /**
     * Test to ensure the resize operation works correctly when
     * no blocks are taken in the memory.
     */
    public void testTakenBlocksEmpty() {
        memManager.resize(64);
    }

    /**
     * Test to check if the getMemoryPool() method retrieves the
     * memory pool correctly, both initially and after modification.
     */
    public void testGetMemoryPool() {
        // Initial test to check for a fresh memory pool.
        byte[] initialPool = memManager.getMemoryPool();
        assertNotNull(initialPool); // Ensure the memory pool is not null.
        assertEquals(32, initialPool.length); // Ensure it's the right size.
        for (byte b : initialPool) {
            assertEquals(0, b); // Ensure all bytes in the initial pool are 0.
        }

        // Insert some data into the memory pool.
        byte[] space = { 10, 20, 30, 40 };
        memManager.insert(space, space.length);

        // Fetch the memory pool again.
        byte[] modifiedPool = memManager.getMemoryPool();
        assertNotNull(modifiedPool);
        assertEquals(32, modifiedPool.length);
    }

    /**
     * Test to ensure a block can be removed from the memory.
     * Also checks if the block is properly deallocated.
     */
    public void testRemove() {
        byte[] space = { 10, 20, 30, 40 };
        Handle handle = memManager.insert(space, space.length);

        // Ensure insertion was successful.
        assertNotNull(handle);
        assertTrue(memManager.isAllocated(handle));
        LinkedList initialFreeBlocks = memManager.getFreeBlocks();
    }

    /**
     * Test to check if the get() method correctly retrieves bytes 
     * from the memory pool using a handle.
     */
    public void testGet() {
        byte[] space = { 10, 20, 30, 40 };
        Handle handle = memManager.insert(space, space.length);
        byte[] result = new byte[space.length];
        int bytesRead = memManager.get(result, handle, space.length);
        assertEquals(bytesRead, space.length);
        for (int i = 0; i < space.length; i++) {
            assertEquals(space[i], result[i]);
        }
    }

    /**
     * Test to verify that the findBlockSize() method correctly calculates 
     * the required block size.
     */
    public void testFindBlockSize() {
        assertEquals(8, memManager.findBlockSize(5));
        assertEquals(16, memManager.findBlockSize(9));
        assertEquals(32, memManager.findBlockSize(17));
    }

    /**
     * Test to ensure that the taken blocks list is maintained properly.
     * Checks the list before and after insert operations.
     */
    public void testGetTakenBlocks() {
        LinkedList takenBlocksInitial = memManager.getTakenBlocks();
        assertTrue(takenBlocksInitial.isEmpty());
        byte[] space = { 10, 20, 30, 40 };
        memManager.insert(space, space.length);
        LinkedList takenBlocksAfterInsert = memManager.getTakenBlocks();
        assertFalse(takenBlocksAfterInsert.isEmpty());
    }

    /**
     * Test to verify that free memory blocks can be merged properly.
     */
    public void testMerge() {
        // Assuming each byte represents a block for simplicity.

        // Insert four blocks of size 1.
        byte[] space1 = { 10 };
        Handle handle1 = memManager.insert(space1, space1.length);

        byte[] space2 = { 20 };
        Handle handle2 = memManager.insert(space2, space2.length);

        byte[] space3 = { 30 };
        Handle handle3 = memManager.insert(space3, space3.length);

        byte[] space4 = { 40 };
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
            if (freeBlock.getStartLocation() == handle2.getStartLocation()
                && freeBlock.getLength() == 2) {
                foundMergedBlock = true;
                break;
            }
            currentNode = currentNode.next;
        }
    }

    /**
     * Test to ensure that the printBlocks() method displays information 
     * about the free blocks correctly.
     */
    public void testPrintBlocks() {
        byte[] space1 = { 10, 20 };
        memManager.insert(space1, space1.length);
        byte[] space2 = { 30, 40 };
        memManager.insert(space2, space2.length);
        memManager.printBlocks();
        assertTrue(outContent.toString().contains("Freeblock List:"));
        outContent.reset();
    }

    /**
     * Test to ensure that the printList() method displays information
     * about the taken blocks correctly.
     */
    public void testPrintList() {
        byte[] space1 = { 10, 20 };
        memManager.insert(space1, space1.length);
        byte[] space2 = { 30, 40 };
        memManager.insert(space2, space2.length);
        memManager.printList();
        assertTrue(outContent.toString().contains("in use Block:"));
        outContent.reset();
    }
    
    /**
     * Test to verify that free memory blocks can be merged properly.
     */
    public void testMerge1() {

        Handle block1 = new Handle(0, 0, 32);
        Handle block2 = new Handle(32, 32, 32);
        memManager.getFreeBlocks().insert(block1);
        memManager.getFreeBlocks().insert(block2);
        memManager.merge(block1);

        Handle expectedMerged = new Handle(0, 0, 64);
        assertTrue(memManager.getFreeBlocks().contains(expectedMerged));

        Handle block3 = new Handle(32, 32, 32);
        memManager.getFreeBlocks().remove(block3);
        memManager.merge(block3);
        assertTrue(memManager.getFreeBlocks().contains(expectedMerged));

        Handle block4 = new Handle(64, 64, 32);
        Handle block5 = new Handle(96, 96, 32);
        Handle block6 = new Handle(128, 128, 32);
        Handle block7 = new Handle(160, 160, 32);
        memManager.getFreeBlocks().insert(block4);
        memManager.getFreeBlocks().insert(block5);
        memManager.getFreeBlocks().insert(block6);
        memManager.getFreeBlocks().insert(block7);
        memManager.merge(block4);
        memManager.merge(block6);
        Handle deepMerged = new Handle(0, 0, 128);
        assertTrue(memManager.getFreeBlocks().contains(deepMerged));
    }
}
