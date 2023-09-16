
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import student.TestCase;

public class LinkedListTest extends TestCase{

    private LinkedList linkedList;
    private Handle handle1;
    private Handle handle2;
    private Handle handle3;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    public void setUp() {
        linkedList = new LinkedList();
        handle1 = new Handle(1, 0, 10);
        handle2 = new Handle(2, 10, 10);
        handle3 = new Handle(3, 20, 10);
        System.setOut(new PrintStream(outContent));
    }

    public void testInsertAndIsEmpty() {
        assertTrue(linkedList.isEmpty());
        linkedList.insert(handle1);
        assertFalse(linkedList.isEmpty());
    }

    public void testRemove() {
        linkedList.insert(handle1);
        linkedList.insert(handle2);
        linkedList.remove(handle1);
        assertFalse(linkedList.contains(handle1));
        assertTrue(linkedList.contains(handle2));
    }

    public void testRemoveTail() {
        linkedList.insert(handle1);
        linkedList.insert(handle2);
        linkedList.remove(handle2);
        assertFalse(linkedList.contains(handle2));
    }

    public void testRemoveFromEmptyList() {
        assertFalse(linkedList.contains(handle1));
        linkedList.remove(handle1);
        assertFalse(linkedList.contains(handle1));
    }

    public void testContains() {
        linkedList.insert(handle1);
        assertTrue(linkedList.contains(handle1));
        assertFalse(linkedList.contains(handle3));
    }

    public void testGetHead() {
        linkedList.insert(handle1);
        linkedList.insert(handle2);
        assertEquals(handle1, linkedList.getHead().handle);
    }
    
    public void testPrintList() {
        linkedList.insert(handle1);
        linkedList.insert(handle2);
        linkedList.printList();

        String expectedOutput = handle1.toString() + "\n" + handle2.toString() + "\n";
        assertEquals(expectedOutput, outContent.toString());
    }
    
    public void testRemoveNonExistentHandle() {
        linkedList.insert(handle1);
        linkedList.insert(handle2);
        assertFalse(linkedList.contains(handle3));
        linkedList.remove(handle3); 
        assertTrue(linkedList.contains(handle1)); 
        assertTrue(linkedList.contains(handle2)); 
    }
    
    public void testRemoveNodeBeforeTail() {
        linkedList.insert(handle1); 
        linkedList.insert(handle2); 
        linkedList.insert(handle3); 
        linkedList.remove(handle2);
        assertFalse(linkedList.contains(handle2)); 
        Handle handle4 = new Handle(4, 30, 10);
        linkedList.insert(handle4);
        
        linkedList.printList();

        String expectedOutput = handle1.toString() + "\n" + 
                                handle3.toString() + "\n" + 
                                handle4.toString() + "\n";
        assertEquals(expectedOutput, outContent.toString());
    }
    
    public void testSize() {
        // Start with empty list
        assertEquals(0, linkedList.size());
        linkedList.insert(handle1);
        assertEquals(1, linkedList.size());
        linkedList.insert(handle2);
        assertEquals(2, linkedList.size());
        linkedList.insert(handle3);
        assertEquals(3, linkedList.size());
        linkedList.remove(handle1);
        assertEquals(2, linkedList.size());
        linkedList.remove(handle2);
        assertEquals(1, linkedList.size());
        linkedList.remove(handle3);
        assertEquals(0, linkedList.size());
    }
}
