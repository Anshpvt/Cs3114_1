public class MemoryManagerTest {

    public static void main(String[] args) {
        // Test Initialization
        MemoryManager manager = new MemoryManager(256);
        System.out.println("After initialization:");
        manager.print();

        // Test Allocation
        int address1 = manager.allocate(16);
        System.out.println("\nAfter allocating 16 bytes at address: " + address1);
        manager.print();

        int address2 = manager.allocate(8);
        System.out.println("\nAfter allocating 8 bytes at address: " + address2);
        manager.print();

        int address3 = manager.allocate(128);
        System.out.println("\nAfter allocating 128 bytes at address: " + address3);
        manager.print();

        // Test Deallocation
        manager.deallocate(address1, 16);
        System.out.println("\nAfter deallocating 16 bytes from address: " + address1);
        manager.print();

        manager.deallocate(address2, 8);
        System.out.println("\nAfter deallocating 8 bytes from address: " + address2);
        manager.print();

        manager.deallocate(address3, 128);
        System.out.println("\nAfter deallocating 128 bytes from address: " + address3);
        manager.print();

        // Test Allocation after Deallocation
        int address4 = manager.allocate(32);
        System.out.println("\nAfter allocating 32 bytes at address: " + address4);
        manager.print();

        // Test over-allocation
        int address5 = manager.allocate(512);
        System.out.println("\nAttempt to allocate 512 bytes. Address returned: " + address5);
        if(address5 == -1) {
            System.out.println("Memory not available.");
        } else {
            manager.print();
        }
    }
}

