import java.util.*;

public class MemoryManager {

    private final List<LinkedList<Integer>> freeLists;  // Each list represents blocks of a specific size (2^index)
    private final int maxBlockSize;  // Maximum memory size (a power of 2)

    public MemoryManager(int maxBlockSize) {
        this.maxBlockSize = maxBlockSize;

        int listsCount = (int) (Math.log(maxBlockSize) / Math.log(2)) + 1;
        freeLists = new ArrayList<>(listsCount);

        for (int i = 0; i < listsCount; i++) {
            freeLists.add(new LinkedList<>());
        }

        // Initially, the whole memory is free
        freeLists.get(listsCount - 1).add(0);
    }

    public int allocate(int size) {
        int blockSize = 1;
        int index = 0;
        while (blockSize < size) {
            blockSize *= 2;
            index++;
        }

        for (int i = index; i < freeLists.size(); i++) {
            if (!freeLists.get(i).isEmpty()) {
                int blockStart = freeLists.get(i).removeFirst();
                split(i, blockStart);
                return blockStart;
            }
        }

        // Memory not available
        return -1;
    }

    private void split(int index, int blockStart) {
        if (index == 0) return;

        int buddyAddress = blockStart ^ (1 << (index - 1));  // XOR to find the buddy address
        freeLists.get(index - 1).add(blockStart);
        freeLists.get(index - 1).add(buddyAddress);
    }

    public void deallocate(int address, int size) {
        int blockSize = 1;
        int index = 0;
        while (blockSize < size) {
            blockSize *= 2;
            index++;
        }

        freeLists.get(index).add(address);
        coalesce();
    }

    private void coalesce() {
        for (int i = 0; i < freeLists.size() - 1; i++) {
            LinkedList<Integer> list = freeLists.get(i);

            Set<Integer> processed = new HashSet<>();
            for (int addr : list) {
                if (processed.contains(addr)) continue;

                int buddyAddress = addr ^ (1 << i);
                if (list.contains(buddyAddress)) {
                    processed.add(addr);
                    processed.add(buddyAddress);

                    list.remove(Integer.valueOf(addr));
                    list.remove(Integer.valueOf(buddyAddress));

                    freeLists.get(i + 1).add(Math.min(addr, buddyAddress));
                }
            }
        }
    }

    public void printFreeBlocks() {
        for (int i = 0; i < freeLists.size(); i++) {
            int blockSize = 1 << i;
            System.out.print("Block size " + blockSize + ": ");
            for (int address : freeLists.get(i)) {
                System.out.print(address + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        MemoryManager manager = new MemoryManager(16);
        manager.printFreeBlocks();
        int addr1 = manager.allocate(5);
        int addr2 = manager.allocate(3);
        manager.printFreeBlocks();
        manager.deallocate(addr1, 5);
        manager.printFreeBlocks();
    }
}
