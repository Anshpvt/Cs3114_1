public class SeminarDB {

    private Node head;
    private MemoryManager memManager;

    // Inner class to represent a node in the linked list
    private class Node {
        Seminar seminar;
        Node next;

        Node(Seminar seminar) {
            this.seminar = seminar;
            this.next = null;
        }
    }

    public SeminarDB(int memorySize) {
        this.head = null;
        this.memManager = new MemoryManager(memorySize);
    }

    public void insert(int id, String title, String date, int length, short x, 
        short y, int cost, String[] keywords, String desc) {
        
        // Check if we have memory to allocate for the seminar
        int memoryNeeded = title.length() + date.length() + desc.length() + (keywords.length * 10);  // assuming an average of 10 characters per keyword
        int address = memManager.allocate(memoryNeeded);

        if (address != -1) {  // we have enough memory  
            Seminar seminar = new Seminar(id, title, date, length, x, y, cost, keywords, desc);
            Node newNode = new Node(seminar);
            newNode.next = head;
            head = newNode;
        } else {
            System.out.println("Not enough memory to store seminar with ID " + id);
        }
    }

    public void delete(int id) {
        if (head == null) return;

        if (head.seminar.id == id) {
            int memoryFreed = head.seminar.toString().length();
            memManager.deallocate(memoryFreed, memoryFreed);
            head = head.next;
            return;
        }

        Node current = head;
        Node prev = null;
        while (current != null && current.seminar.getId() != id) {
            prev = current;
            current = current.next;
        }

        if (current == null) return; // not found

        int memoryFreed = current.seminar.toString().length();
        memManager.deallocate(memoryFreed, memoryFreed);
        prev.next = current.next;  // unlink the node
    }

    public Seminar search(int id) {
        Node current = head;
        while (current != null) {
            if (current.seminar.id == id) {
                return current.seminar;
            }
            current = current.next;
        }
        return null;  // not found
    }

    public void print() {
        Node current = head;
        while (current != null) {
            System.out.println(current.seminar.toString());
            current = current.next;
        }
    }
}

