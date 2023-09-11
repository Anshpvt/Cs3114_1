import java.util.HashMap;
import java.util.Map;

public class SeminarDB {

    private Map<Integer, Seminar> seminars;
    private MemoryManager memManager;

    public SeminarDB(int memorySize) {
        this.seminars = new HashMap<>();
        this.memManager = new MemoryManager(memorySize);
    }

    public void insert(int id, String title, String date, int length, short x, 
        short y, int cost, String[] keywords, String desc) {
        
        // Check if we have memory to allocate for the seminar
        int memoryNeeded = title.length() + date.length() + desc.length() + (keywords.length * 10);  // assuming an average of 10 characters per keyword
        int address = memManager.allocate(memoryNeeded);

        if (address != -1) {  // we have enough memory
            Seminar seminar = new Seminar(id, title, date, length, x, y, cost, keywords, desc);
            seminars.put(id, seminar);
        } else {
            System.out.println("Not enough memory to store seminar with ID " + id);
        }
    }

    public void delete(int id) {
        Seminar seminar = seminars.remove(id);
        if (seminar != null) {
            int memoryFreed = seminar.toString().length();
            memManager.deallocate(memoryFreed, memoryFreed);  // deallocate memory used by this seminar
        }
    }

    public Seminar search(int id) {
        return seminars.get(id);
    }

    public void print() {
        for (Seminar seminar : seminars.values()) {
            System.out.println(seminar.toString());
        }
    }
}
