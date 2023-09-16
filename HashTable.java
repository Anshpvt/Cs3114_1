
/**
 * HashTable class represents a simple hash hash data structure for storing and
 * managing Record objects.
 */
public class HashTable {

    private int size;
    private Record[] hash;
    private int currSize;// number of current elements in the hash hash
    private static final Record TOMBSTONE = new Record(-1, null);

    public HashTable(int size) {
        hash = new Record[size];
        currSize = 0;
        this.size = size;
    }


    private int h1(int key) {
        return key % hash.length;
    }


    private int h2(int key) {
        return (key % (hash.length - 1));
    }


    public boolean isFull(int key, Record record) {
        if (currSize * 2 >= size) { // Check for load factor > 0.5 and
            // resize
            return true;
        }
        return false;
    }


    public boolean insert(int key, Record record) {

        int home;
        int pos = home = h1(key);
        while ((hash[pos] != null)) {
            if (key == hash[pos].getKey()) {
                return false;
            }
            if (hash[pos].getKey() == -1) {
                hash[pos] = record;
                currSize++;
                return true;
            }
            pos = (pos + h2(key)) % size;
        }
        hash[pos] = record;
        currSize++;
        return true;
    }


    public Record search(int key) {
        int index = h1(key);
        int probeStep = h2(key);
        while (hash[index] != null) {
            if (hash[index].getKey() == key) {
                return hash[index];
            }
            index = (index + probeStep) % hash.length;
        }
        return null; // not found
    }


    public boolean delete(int key) {
        int index = h1(key);
        int probeStep = h2(key);
        while (hash[index] != null) {
            if (hash[index].getKey() == key) {
                hash[index] = TOMBSTONE;
                currSize--;
                return true;
            }
            index = (index + probeStep) % hash.length;
        }
        return false;
    }


    public void resize() {
        Record[] oldTable = hash;
        hash = new Record[oldTable.length * 2];
        currSize = 0;
        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i] != null && oldTable[i] != TOMBSTONE) {
                this.insert(oldTable[i].getKey(), oldTable[i]);
            }
        }
        size = hash.length;
        System.out.println("Hash table expanded to " + hash.length
            + " records");
    }


    public int getCapacity() {
        return size;
    }


    public void print() {
        int count = 0; // Counter for printed records

        for (int i = 0; i < hash.length; i++) {
            if (hash[i] != null && hash[i] != TOMBSTONE) {
                System.out.println(i + ": " + hash[i].getKey());
                count++;
            }
            else if (hash[i] == TOMBSTONE) {
                System.out.println(i + ": TOMBSTONE");
            }
        }
        System.out.println("total records: " + count);

    }
}
