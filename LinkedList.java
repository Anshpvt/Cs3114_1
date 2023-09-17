/**
 * Project 1
 */

/**
 * This class implements a singly linked 
 * list data structure with basic operations 
 * to insert, remove, and search for nodes. The 
 * list maintains a head and a tail 
 * pointer, as well as a size count for easy 
 * access to the length of the list.
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

public class LinkedList {
    private Node head;
    private Node tail;
    private int size; // Add a size variable

    /**
     * Constructor initializes an empty linked list.
     */
    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0; // Initialize size as 0

    }

    /**
     * Inserts a new node with the given handle to the end of the list.
     * @param handle The data to be stored in the new node.
     */
    public void insert(Handle handle) {
        Node newNode = new Node(handle);
        if (isEmpty()) {
            tail = newNode;
            head = newNode;
        }
        else {
            tail.next = newNode;
            tail = newNode;
        }
        size++; // Increment the size
    }
    
    /**
     * Update size the 
     */
    public void updateSize() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        this.size = count;
    }

    /**
     * Removes the node with the specified handle from the list.
     * @param handle The data of the node to be removed.
     */
    public void remove(Handle handle) {
        if (isEmpty())
            return;

        if (head.handle.equals(handle)) {
            head = head.next;
            size--;
            return;
        }

        Node prev = null;
        Node current = head;
        while (current != null && !current.handle.equals(handle)) {
            prev = current;
            current = current.next;
        }

        if (current != null) {
            prev.next = current.next;
            if (current.next == null) { 
                tail = prev;
            }
            updateSize();
        }
    }

    /**
     * Checks if the list contains a node with the specified handle.
     * @param handle The data to be searched for in the list.
     * @return true if the list contains the handle, false otherwise.
     */
    public boolean contains(Handle handle) {
        Node current = head;
        while (current != null) {
            if (current.handle.equals(handle))
                return true;
            current = current.next;
        }
        return false;
    }

    /**
     * Retrieves the head node of the list.
     * @return The head node of the list.
     */
    public Node getHead() {
        return head;
    }

    /**
     * Determines if the list is empty.
     * @return true if the list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Prints the list from head to tail.
     */
    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.println(current.handle);
            current = current.next;
        }
    }

    /**
     * Retrieves the current size (length) of the list.
     * @return The number of nodes in the list.
     */
    public int size() {
        return size;
    }
}

/**
 * Node is a helper class representing a single node in the LinkedList.
 * Each node contains a handle and a reference to the next node.
 */
class Node {
    Handle handle;
    Node next;

    /**
     * Constructs a node with the specified handle.
     * @param handle The data to be stored in the node.
     */
    public Node(Handle handle) {
        this.handle = handle;
        this.next = null;
    }
}
