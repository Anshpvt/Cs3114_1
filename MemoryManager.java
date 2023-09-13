import java.util.*;

public class MemoryManager {

    private final LinkedList<Integer>[] freeLists;  // Each list represents blocks of a specific size (2^index)
    private final int maxBlockSize;  // Maximum memory size (a power of 2)
    private int currentSize;
    
    public MemoryManager(int maxBlockSize) {
        this.maxBlockSize = maxBlockSize;
        this.currentSize = maxBlockSize;

        int listsCount = (int) (Math.log(maxBlockSize) / Math.log(2)) + 1;
        freeLists = new LinkedList[listsCount];

        for (int i = 0; i < listsCount; i++) {
            freeLists[i] = new LinkedList<>();
        }

        // Initially, the whole memory is free
        freeLists[listsCount - 1].add(0);
    }
    
    public int getCurrentSize() {
        return currentSize;
    }
    
    public int allocate(int size) {
        int blockSize = 1;
        int index = 0;
        while (blockSize < size) {
            blockSize *= 2;
            index++;
        }

        for (int i = index; i < freeLists.length; i++) {
            if (!freeLists[i].isEmpty()) {
                int blockStart = freeLists[i].removeFirst();
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
        freeLists[index - 1].add(blockStart);
        freeLists[index - 1].add(buddyAddress);
    }

    public void deallocate(int address, int size) {
        int blockSize = 1;
        int index = 0;
        while (blockSize < size) {
            blockSize *= 2;
            index++;
        }

        freeLists[index].add(address);
        coalesce();
    }

    private void coalesce() {
        for (int i = 0; i < freeLists.length - 1; i++) {
            LinkedList<Integer> list = freeLists[i];

            Set<Integer> processed = new HashSet<>();
            for (int addr : list) {
                if (processed.contains(addr)) continue;

                int buddyAddress = addr ^ (1 << i);
                if (list.contains(buddyAddress)) {
                    processed.add(addr);
                    processed.add(buddyAddress);

                    list.remove(Integer.valueOf(addr));
                    list.remove(Integer.valueOf(buddyAddress));

                    freeLists[i + 1].add(Math.min(addr, buddyAddress));
                }
            }
        }
    }

    public void print() {
        boolean hasFreeBlocks = false;
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < freeLists.length; i++) {
            if (!freeLists[i].isEmpty()) {
                hasFreeBlocks = true;
                int blockSize = 1 << i;
                for (int address : freeLists[i]) {
                    // For a block size of maxBlockSize, the address itself is the block size
                    if (blockSize == maxBlockSize) {
                        output.append(blockSize + ": " + blockSize + "\n");
                    } else {
                        // Block's end address is the start address + block size - 1
                        int endAddress = address + blockSize - 1;
                        output.append(blockSize + ": " + endAddress + "\n");
                    }
                }
            }
        }

        if (!hasFreeBlocks) {
            System.out.println("There are no free blocks in the memory pool.");
        } else {
            System.out.print(output.toString());
        }
    }
    
}
}
