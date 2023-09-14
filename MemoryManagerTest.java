import static org.junit.Assert.*;

import student.TestCase;

public class MemoryManagerTest {

    public static void main(String[] args) {
        testInitialization();
        testAllocation();
        testDeallocation();
        testCoalescing();
        testOverAllocation();
    }

    private static void testInitialization() {
        System.out.println("---- Testing Initialization ----");
        MemoryManager manager = new MemoryManager(256);
        System.out.println("Expected: 256: 256");
        manager.print();
        System.out.println();
    }

    private static void testAllocation() {
        System.out.println("---- Testing Allocation ----");
        MemoryManager manager = new MemoryManager(256);

        int address1 = manager.allocate(16);
        System.out.println("Allocating 16 bytes; Expected address: 0");
        System.out.println("Received address: " + address1);
        System.out.println("Memory State:");
        manager.print();
        System.out.println();

        int address2 = manager.allocate(8);
        System.out.println("Allocating 8 bytes; Expected address: 32");
        System.out.println("Received address: " + address2);
        System.out.println("Memory State:");
        manager.print();
        System.out.println();

        int address3 = manager.allocate(128);
        System.out.println("Allocating 128 bytes; Expected address: 128");
        System.out.println("Received address: " + address3);
        System.out.println("Memory State:");
        manager.print();
        System.out.println();
    }

    private static void testDeallocation() {
        System.out.println("---- Testing Deallocation ----");
        MemoryManager manager = new MemoryManager(256);
        int address1 = manager.allocate(16);
        int address2 = manager.allocate(8);

        manager.deallocate(address1, 16);
        System.out.println("After deallocating 16 bytes from address: " + address1);
        manager.print();
        System.out.println();

        manager.deallocate(address2, 8);
        System.out.println("After deallocating 8 bytes from address: " + address2);
        manager.print();
        System.out.println();
    }

    private static void testCoalescing() {
        System.out.println("---- Testing Coalescing ----");
        MemoryManager manager = new MemoryManager(256);

        int address1 = manager.allocate(16);
        int address2 = manager.allocate(16);
        int address3 = manager.allocate(16);
        int address4 = manager.allocate(16);

        System.out.println("Memory State after 4 allocations of 16 bytes:");
        manager.print();

        manager.deallocate(address1, 16);
        manager.deallocate(address2, 16);
        System.out.println("Memory State after deallocating two blocks of 16 bytes:");
        manager.print();

        manager.deallocate(address3, 16);
        manager.deallocate(address4, 16);
        System.out.println("Memory State after deallocating remaining blocks of 16 bytes:");
        manager.print();
        System.out.println();
    }

    private static void testOverAllocation() {
        System.out.println("---- Testing Over-Allocation ----");
        MemoryManager manager = new MemoryManager(256);
        int address = manager.allocate(512);
        System.out.println("Attempt to allocate 512 bytes; Expected address: -1");
        System.out.println("Received address: " + address);
        System.out.println();
    }
}
