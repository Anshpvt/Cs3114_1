import java.util.*;
/**
 * Project 1
 */

/**
 * describe function of class
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
public class MemManager {
    private int poolSize;
    private LinkedList freeBlocks;
    private LinkedList takenBlocks;
    private byte[] memoryPool;
    private int start;

    /**
     * Initializes the memory manager with the specified pool size.
     * @param poolSize Size of the memory pool to initialize.
     */
    public MemManager(int poolSize) {
        this.poolSize = poolSize;
        this.freeBlocks = new LinkedList();
        this.takenBlocks = new LinkedList();
        this.memoryPool = new byte[poolSize];
        Handle handle = new Handle(0, poolSize, poolSize - 1);

        freeBlocks.insert(handle);

    }

    /**
     * Resizes the memory pool to the specified new size.
     * @param newSize The new size for the memory pool.
     */
    public void resize(int newSize) {
        byte[] newPool = new byte[newSize];
        System.arraycopy(memoryPool, 0, newPool, 0, memoryPool.length);
        memoryPool = newPool;

        start = poolSize / 2;
        Handle notEmpty = new Handle(start, poolSize, poolSize - 1);
        Handle ifEmpty = new Handle(0, poolSize, poolSize - 1);
        if (!takenBlocks.isEmpty()) {
            freeBlocks.insert(notEmpty);
        }
        else {
            freeBlocks.insert(ifEmpty);
        }
    }

    /**
     * Inserts the given space into the memory pool.
     * @param space The byte array to be inserted.
     * @param size The size of the space to be inserted.
     * @return A Handle object pointing to the location of the inserted space or null if insertion failed.
     */
    public Handle insert(byte[] space, int size) {
        int blockSize = findBlockSize(size);
        Handle takenBlock = allocate(blockSize);

        int maxRetries = 5; // define a maximum number of retries
        int retryCount = 0;

        while (takenBlock == null && retryCount < maxRetries) {
            resize(poolSize * 2);
            takenBlock = allocate(blockSize);
            retryCount++;
        }

        if (takenBlock == null) {
            return null;
        }

        start = takenBlock.getStartLocation();
        System.arraycopy(space, 0, memoryPool, start, size);
        takenBlocks.insert(takenBlock);
        freeBlocks.remove(takenBlock);

        return takenBlock;
    }

    /**
     * Retrieves the length associated with the provided handle.
     * @param handle The Handle object whose length needs to be retrieved.
     * @return The length associated with the given handle.
     */
    public int getLength(Handle handle) {
        return handle.getLength();
    }

    /**
     * Returns the current memory pool.
     * @return The byte array representing the memory pool.
     */
    public byte[] getMemoryPool() {
        return memoryPool;
    }

    /**
     * Removes the block of memory associated with the provided handle.
     * @param handle The Handle object pointing to the block of memory to be removed.
     */
    public void remove(Handle handle) {
        int start = handle.getStartLocation();
        int blockSize = handle.getLength();

        // Set the memory pool's bytes within the handle's range to 0.
        Arrays.fill(memoryPool, start, start + blockSize, (byte)0);

        // Remove the handle from takenBlocks and insert into freeBlocks.
        takenBlocks.remove(handle);
        freeBlocks.insert(handle);

        // Merge if required (only if your logic needs it).
        merge(handle);
    }

    /**
     * Copies the memory block associated with the given handle into the provided byte array space.
     * @param space The byte array to copy memory into.
     * @param handle The Handle object pointing to the block of memory to copy.
     * @param size The size of memory to be copied.
     * @return The number of bytes copied.
     */
    public int get(byte[] space, Handle handle, int size) {
        start = handle.getStartLocation();
        int blockSize = handle.getLength();
        int copyBytes = Math.min(blockSize, size);

        System.arraycopy(memoryPool, start, space, 0, copyBytes);
        return copyBytes;
    }

    /**
     * Dumps the free blocks to the console.
     */
    public void dump() {
        freeBlocks.printList();
    }

    /**
     * Determines the block size needed based on the provided size.
     * @param size The size to determine block size for.
     * @return The block size required.
     */
    public int findBlockSize(int size) {
        int blockSize = 1;
        while (blockSize < size) {
            blockSize *= 2;

        }
        return blockSize;
    }

    /**
     * Allocates memory of the specified block size.
     * @param blockSize The size of memory block to allocate.
     * @return A Handle object pointing to the location of the allocated block or null if allocation failed.
     */
    public Handle allocate(int blockSize) {
        Node current = freeBlocks.getHead();
        while (current != null) {
            Handle block = current.handle;
            if (block.getLength() == blockSize && !isAllocated(block)) {
                freeBlocks.remove(block);
                return block;
            }
            else if (block.getLength() > blockSize) {
                int openSpace = block.getLength() - blockSize;
                if (openSpace >= blockSize && !isAllocated(block)) {
                    int isSplit = block.getStartLocation() + blockSize;
                    Handle b1 = new Handle(block.getStartLocation(), blockSize,
                        isSplit - 1);
                    Handle b2 = new Handle(isSplit, blockSize, block.getEnd());

                    freeBlocks.remove(block);
                    freeBlocks.insert(b1);
                    freeBlocks.insert(b2);
                    return b1;
                }
            }
            current = current.next;
        }

        if (freeBlocks.isEmpty() && poolSize * 2 <= Integer.MAX_VALUE) {
            int newSize = poolSize * 2;
            byte[] newPool = new byte[newSize];

            System.arraycopy(memoryPool, 0, newPool, 0, memoryPool.length);
            memoryPool = newPool;
            Handle newHan = new Handle(poolSize, newSize, newSize - 1);
            freeBlocks.insert(newHan);
            poolSize = newSize;
            return allocate(blockSize);
        }
        return null;

    }

    /**
     * Prints the free block list to the console.
     */
    public void printBlocks() {
        System.out.println("Freeblock List:");
        freeBlocks.printList();
    }

    /**
     * Prints the list of blocks in use to the console.
     */
    public void printList() {
        System.out.println("in use Block:");
        takenBlocks.printList();
    }

    /**
     * Checks if the block associated with the provided handle is allocated.
     * @param block The Handle object to check for allocation.
     * @return true if the block is allocated, false otherwise.
     */
    public boolean isAllocated(Handle block) {
        return takenBlocks.contains(block);
    }

    /**
     * Merges the block of memory associated with the provided handle with adjacent free blocks.
     * @param block The Handle object pointing to the block of memory to be merged.
     */
    public void merge(Handle block) {
        start = block.getStartLocation();
        int blockSize = block.getLength();

        while (true) {
            int buddyPos = (start % (2 * blockSize) == 0)
                ? (start + blockSize)
                : (start - blockSize);
            Handle buddy = new Handle(buddyPos, blockSize, buddyPos + blockSize
                - 1);
            if (!freeBlocks.contains(buddy)) {
                break;
            }
            Handle merged = (start < buddyPos)
                ? new Handle(start, blockSize * 2, buddy.getEnd())
                : new Handle(buddy.getStartLocation(), blockSize * 2, block
                    .getEnd());

            freeBlocks.remove(block);
            freeBlocks.remove(buddy);
            freeBlocks.insert(merged);

            block = merged;
            start = block.getStartLocation();
            blockSize *= 2;

            if (blockSize == poolSize) {
                break;
            }
        }
    }

    /**
     * Returns the list of free blocks.
     * @return A LinkedList containing free blocks.
     */
    public LinkedList getFreeBlocks() {
        return freeBlocks;
    }

    /**
     * Returns the list of blocks currently in use.
     * @return A LinkedList containing blocks in use.
     */
    public LinkedList getTakenBlocks() {
        return takenBlocks;
    }
}
