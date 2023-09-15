import java.util.*;



public class MemManager {
	private int poolSize;
	private LinkedList freeBlocks;
	private LinkedList takenBlocks;
	private byte[] memoryPool;
	private int start;

    
    public MemManager(int poolSize) {
    	this.poolSize = poolSize;
    	this.freeBlocks = new LinkedList();
    	this.takenBlocks = new LinkedList();
    	this.memoryPool = new byte[poolSize];
    	Handle handle = new Handle(0, poolSize, poolSize - 1);
    	
    	freeBlocks.insert(handle);
    	
    }
    
    public void resize(int newSize)
    {
    	byte[] newPool = new byte[newSize];
    	System.arraycopy(memoryPool,  0,  newPool,  0,  memoryPool.length);
    	memoryPool = newPool;
    	
    	start = poolSize / 2;
    	Handle notEmpty = new Handle(start, poolSize, poolSize - 1);
    	Handle ifEmpty = new Handle(0, poolSize, poolSize - 1);
    	if (!takenBlocks.isEmpty())
    	{
    		freeBlocks.insert(notEmpty);
    	}
    	else
    	{
    		freeBlocks.insert(ifEmpty);
    	}
    }
    
    public Handle insert(byte[] space, int size)
    {
    	int blockSize = findBlockSize(size);
    	Handle takenBlock = allocate(blockSize);
    	
    	while(takenBlock == null)
    	{
    		resize(poolSize * 2);
    		takenBlock = allocate(blockSize);
    		
    		if (takenBlock != null)
    		{
    			start = takenBlock.getStartLocation();
    			System.arraycopy(space, 0, memoryPool, start, size);
    			takenBlocks.insert(takenBlock);
    			freeBlocks.remove(takenBlock);
    			return takenBlock;
    		}
    		
    	}
    	
    	start = takenBlock.getStartLocation();
    	if (start == poolSize)
    	{
    		resize(poolSize * 2);
    		takenBlock = allocate(blockSize);
    	}
    	System.arraycopy(space, 0, memoryPool, start, size);
    	takenBlocks.insert(takenBlock);
    	freeBlocks.remove(takenBlock);
    	return takenBlock;
    }
    
    public int getLength(Handle handle)
    {
    	return handle.getLength();
    }
    
    public void remove(Handle handle)
    {
    	start = handle.getStartLocation();
    	int blockSize = handle.getLength();
    	
    	Arrays.fill(memoryPool, start, start + blockSize, (byte) 0);
    	
    	takenBlocks.remove(handle);
    	freeBlocks.insert(handle);
    	merge(handle);
    }
    
    public int get(byte[] space, Handle handle, int size)
    {
    	start = handle.getStartLocation();
    	int blockSize = handle.getLength();
    	int copyBytes = Math.min(blockSize,  size);
    	
    	System.arraycopy(memoryPool, start, space, 0, copyBytes);
    	return copyBytes;
    }
    
    public void dump()
    {
    	freeBlocks.printList();
    }
    
    public int findBlockSize(int size)
    {
    	int blockSize = 1;
    	while (blockSize < size)
    	{
    		blockSize*= 2;
    		
    	}
    	return blockSize;
    }
    
    public Handle allocate(int blockSize)
    {
    	Node current = freeBlocks.getHead();
    	while (current != null)
    	{
    		Handle block = current.handle;
    		if (block.getLength() == blockSize && !isAllocated(block))
    		{
    			freeBlocks.remove(block);
    			return block;
    		}
    		else if (block.getLength() > blockSize)
    		{
    			int openSpace = block.getLength() - blockSize;
    			if (openSpace >= blockSize && !isAllocated(block))
    			{
    				int isSplit = block.getStartLocation() + blockSize;
    				Handle b1 = new Handle(block.getStartLocation(), blockSize, isSplit - 1);
    				Handle b2 = new Handle(isSplit, blockSize, block.getEnd());
    				
    				freeBlocks.remove(block);
    				freeBlocks.insert(b1);
    				freeBlocks.insert(b2);
    				return b1;
    			}
    		}
    		current = current.next;
    	}
    	
    	if (freeBlocks.isEmpty() && poolSize * 2 <= Integer.MAX_VALUE)
    	{
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
    
    public void printBlocks()
    {
    	System.out.println("Freeblock List:");
    	freeBlocks.printList();
    }
    
    public void printList()
    {
    	System.out.println("in use Block:");
    	takenBlocks.printList();
    }
    
    public boolean isAllocated(Handle block)
    {
    	return takenBlocks.contains(block);
    }
    
    public void merge(Handle block)
    {
    	start = block.getStartLocation();
    	int blockSize = block.getLength();
    	
    	while(true)
    	{
    		int buddyPos = (start % (2 * blockSize ) == 0) ? (start + blockSize) : (start - blockSize);
    		Handle buddy = new Handle(buddyPos, blockSize, buddyPos + blockSize - 1);
    		if (!freeBlocks.contains(buddy))
    		{
    			break;
    		}
    		Handle merged = (start < buddyPos) ? new Handle(start, blockSize * 2, buddy.getEnd()):
    			new Handle(buddy.getStartLocation(), blockSize * 2, block.getEnd());
    		
    		freeBlocks.remove(block);
    		freeBlocks.remove(buddy);
    		freeBlocks.insert(merged);
    		
    		block = merged;
    		start = block.getStartLocation();
    		blockSize *= 2;
    		
    		if (blockSize == poolSize)
    		{
    			break;
    		}
    	}
    }
        
}

