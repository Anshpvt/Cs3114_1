import java.io.*;

/**
 * HashTable class represents a simple hash table data structure for storing and
 * managing Record objects.
 */
public class HashTable {


    private Record[] table;
    private int currSize; // number of current elements in the hash table
    private static final Record TOMBSTONE = new Record(-1, null);

    public HashTable(int size) {
        table = new Record[size];
        currSize = 0;
    }


    private int h1(int key) {
        return key % table.length;
    }


    private int h2(int key) {
        return 1 + (key % (table.length - 1));
    }


    public void insert(Record record) {
        if (currSize + 1 >= table.length / 2) { // Check for load factor > 0.5 and
                                            // resize
            resize();
        }
        int index = h1(record.getKey());
        int probeStep = h2(record.getKey());
        while (table[index] != null && table[index] != TOMBSTONE) {
            index = (index + probeStep) % table.length;
        }
        if (table[index] == null || table[index] == TOMBSTONE) {
            table[index] = record;
            currSize++;
        }
    }


    public Record find(int key) {
        int index = h1(key);
        int probeStep = h2(key);
        while (table[index] != null) {
            if (table[index].getKey() == key) {
                return table[index];
            }
            index = (index + probeStep) % table.length;
        }
        return null; // not found
    }


    public void remove(int key) {
        int index = h1(key);
        int probeStep = h2(key);
        while (table[index] != null) {
            if (table[index].getKey() == key) {
                table[index] = TOMBSTONE;
                currSize--;
                return;
            }
            index = (index + probeStep) % table.length;
        }
    }


    private void resize() {
        Record[] oldTable = table;
        table = new Record[oldTable.length * 2];
        currSize = 0;
        for (Record record : oldTable) {
            if (record != null && record != TOMBSTONE) {
                insert(record);
            }
        }
    }
}
