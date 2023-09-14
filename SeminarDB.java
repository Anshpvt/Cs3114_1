
public class SeminarDB {

    private HashTable hash;
    private MemoryManager memManager;

    public SeminarDB(int memorySize, int hashSize) {

        this.memManager = new MemoryManager(memorySize);
        this.hash = new HashTable(hashSize);
    }
    
    public HashTable getHash()
    {
    	return hash;
    }
    
    public boolean isInserted(int key, Record rec)
    {
    	return hash.insert(key, rec);
    }
    
    public void insert(Record rec)
    {
    	int start = hash.getCapacity();
    	int key = rec.getKey();
    	
    	if (hash.isFull(key, rec))
    	{
    		hash.resize();
    	}
    	if (!isInserted(key, rec))
    	{
    		System.out.println("Insert FAILED - There is already a record with ID " + key);
    	}
    	else
    	{
//    		if ( start != hash.getCapacity())
//    		{
//    			System.out.print("Hash Table expanded to " + hash.getCapacity() + " records");
//    		}
    		Seminar sem = rec.getSeminar();
    		System.out.println("Successfully inserted record with ID " + key);
    		System.out.println(sem.toString());
    		
    		byte[] byteArr;
    		try {
    			byteArr = sem.serialize();
    			System.out.println("Size: " + byteArr.length);
    		}
    		catch (Exception e) {
    			e.printStackTrace();
    		}
    		
    	}
//    	int memoryNeeded = rec.getSem().length() * 10;  // assuming an average of 10 characters per keyword
//        int address = memManager.allocate(memoryNeeded);
//        if (address == -1) {  // we dont have enough memory
//            
//            System.out.println("Not enough memory to store seminar with ID " + key);
        //}
    	
    }


    public boolean isDeleted(int key)
    {
    	return hash.delete(key);
    }
    
    public void delete(int key) {
        
    	if (!isDeleted(key))
    	{
    		System.out.println("Delete FAILED -- There is no record with ID " + key);
    	}
    	else
    	{
    		System.out.println("Record with ID " + key + " successfully deleted from the database");
    	}
    		
//        if (isDeleted(key)) {
//            int memoryFreed = getInfo.length();
//            memManager.deallocate(key, memoryFreed);  // deallocate memory used by this seminar
//        }
    }

    public void search(int key) {
        Record rec = hash.search(key);
        if (rec == null || rec.isTombstone())
        {
        	System.out.println("Search FAILED -- There is no record with ID " + key);
        }
        else
        {
        	System.out.println("Found record with ID " + key + ": ");
        	System.out.println(rec.getSeminar().toString());
        }
    }

    public void printHash() {
        System.out.println("Hashtable: ");
        hash.print();
    }
    
    public void printBlock() {
        System.out.println("Freeblock List: ");
        memManager.print();
    }
}
