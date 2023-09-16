public class LinkedList {
    private Node head;
    private Node tail;
    private int size; // Add a size variable

    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0; // Initialize size as 0

    }


    // Insert node to the end of the list
    public void insert(Handle handle) {
        Node newNode = new Node(handle);
        if (isEmpty()) {
            head = tail = newNode;
        }
        else {
            tail.next = newNode;
            tail = newNode;
        }
        size++; // Increment the size
    }


    // Remove node with the given handle
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
            if (current.next == null) { // adjusting tail
                tail = prev;
            }
            size--;
        }
    }


    // Check if the list contains the handle
    public boolean contains(Handle handle) {
        Node current = head;
        while (current != null) {
            if (current.handle.equals(handle))
                return true;
            current = current.next;
        }
        return false;
    }


    // Get the head of the list
    public Node getHead() {
        return head;
    }


    // Check if the list is empty
    public boolean isEmpty() {
        return head == null;
    }


    // Print the list
    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.println(current.handle);
            current = current.next;
        }
    }


    public int size() {
        return size;
    }
}




class Node {
    Handle handle;
    Node next;

    public Node(Handle handle) {
        this.handle = handle;
        this.next = null;
    }
}
