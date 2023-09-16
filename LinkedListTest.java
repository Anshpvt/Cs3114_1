import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import student.TestCase;
/**
 * Project 1
 */

/**
 * Test class for LinkedList.
 * This class provides JUnit test cases to ensure the functionality of LinkedList.
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
public class LinkedListTest extends TestCase {

    private LinkedList linkedList;
    private Handle handle1;
    private Handle handle2;
    private Handle handle3;
    private final ByteArrayOutputStream outContent =
        new ByteArrayOutputStream();

    /**
     * sets up for tests
     */
    public void setUp() {
        linkedList = new LinkedList();
        handle1 = new Handle(1, 0, 10);
        handle2 = new Handle(2, 10, 10);
        handle3 = new Handle(3, 20, 10);
        System.setOut(new PrintStream(outContent));
    }

    /**
     * Test the functionality of inserting an element into an empty LinkedList
     * and ensuring the list isn't empty afterwards.
     */
    public void testInsertAndIsEmpty() {
        assertTrue(linkedList.isEmpty());
        linkedList.insert(handle1);
        assertFalse(linkedList.isEmpty());
    }

    /**
     * Test the remove method by inserting two handles and then removing one.
     * Verify that the removed handle doesn't exist and the non-removed handle still exists.
     */
    public void testRemove() {
        linkedList.insert(handle1);
        linkedList.insert(handle2);
        linkedList.remove(handle1);
        assertFalse(linkedList.contains(handle1));
        assertTrue(linkedList.contains(handle2));
    }

    /**
     * Test removing the tail of the list.
     * Insert two handles and remove the second one (tail).
     */
    public void testRemoveTail() {
        linkedList.insert(handle1);
        linkedList.insert(handle2);
        linkedList.remove(handle2);
        assertFalse(linkedList.contains(handle2));
    }

    /**
     * Test removing a handle from an empty list.
     * Ensure that the removal operation doesn't throw errors.
     */
    public void testRemoveFromEmptyList() {
        assertFalse(linkedList.contains(handle1));
        linkedList.remove(handle1);
        assertFalse(linkedList.contains(handle1));
    }

    /**
     * Test the contains method by inserting a handle and verifying its presence.
     */
    public void testContains() {
        linkedList.insert(handle1);
        assertTrue(linkedList.contains(handle1));
        assertFalse(linkedList.contains(handle3));
    }

    /**
     * Test retrieving the head of the list.
     * Insert two handles and ensure the first inserted handle is the head.
     */
    public void testGetHead() {
        linkedList.insert(handle1);
        linkedList.insert(handle2);
        assertEquals(handle1, linkedList.getHead().handle);
    }

    /**
     * Test the print method of the list.
     * Verify the correct output by comparing it to expected output.
     */
    public void testPrintList() {
        linkedList.insert(handle1);
        linkedList.insert(handle2);
        linkedList.printList();

        String expectedOutput = handle1.toString() + "\n" + handle2.toString()
            + "\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    /**
     * Test removing a non-existent handle from the list.
     * Ensure the list remains unchanged after the operation.
     */
    public void testRemoveNonExistentHandle() {
        linkedList.insert(handle1);
        linkedList.insert(handle2);
        assertFalse(linkedList.contains(handle3));
        linkedList.remove(handle3);
        assertTrue(linkedList.contains(handle1));
        assertTrue(linkedList.contains(handle2));
    }

    /**
     * Test removing a handle that's before the tail.
     * Then add another handle and ensure the list structure is maintained correctly.
     */
    public void testRemoveNodeBeforeTail() {
        linkedList.insert(handle1);
        linkedList.insert(handle2);
        linkedList.insert(handle3);
        linkedList.remove(handle2);
        assertFalse(linkedList.contains(handle2));
        Handle handle4 = new Handle(4, 30, 10);
        linkedList.insert(handle4);

        linkedList.printList();

        String expectedOutput = handle1.toString() + "\n" + handle3.toString()
            + "\n" + handle4.toString() + "\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    /**
     * Test the size method of the list.
     * Ensure the size updates correctly on insert and remove operations.
     */
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
