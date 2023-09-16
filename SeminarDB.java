
public class SeminarDB {

    private HashTable hash;
    private MemManager memManager;

    public SeminarDB(int memorySize, int hashSize) {

        this.memManager = new MemManager(memorySize);
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
            Seminar sem = rec.getSeminar();
            System.out.println("Successfully inserted record with ID " + key);
            System.out.println(sem.toString());
            
            byte[] byteArr;
            try {
                byteArr = sem.serialize();
                System.out.println("Size: " + byteArr.length);
                int memoryNeeded = byteArr.length;  // assuming an average of 10 characters per keyword
                Handle address = memManager.insert(byteArr, memoryNeeded);
                    if (address == null) {  // we dont have enough memory
                        
                        System.out.println("Not enough memory to store seminar with ID " + key);
                    }  
                }
                catch (Exception e) {
                    e.printStackTrace();
                }          
        } 
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
        memManager.printBlocks();
        memManager.printList();
    }
}
