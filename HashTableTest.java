import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class HashTableTest {

    private HashTable hashTable;

    public void setUp() {
        hashTable = new HashTable();
    }


    public void testInsert() {
        // Test insertion of a new record
        assertTrue(hashTable.insert(1, "title1", "10:00", "2 hours", "1.2",
            "2.3", "50", "list1", "description1"));

        // Test that inserting a duplicate ID fails
        assertFalse(hashTable.insert(1, "title2", "11:00", "3 hours", "2.3",
            "3.4", "60", "list2", "description2"));
    }

    public void testDelete() {
        // Set up for delete
        hashTable.insert(2, "title2", "11:00", "3 hours", "2.3", "3.4", "60",
            "list2", "description2");

        // Test that deleting an existing record works
        assertTrue(hashTable.delete(2));

        // Test that deleting a non-existent record fails
        assertFalse(hashTable.delete(2));
    }


    public void testSearch() {
        // Set up for search
        hashTable.insert(3, "title3", "12:00", "4 hours", "3.4", "4.5", "70",
            "list3", "description3");

        // Test that searching for an existing record returns the correct record
        String expected =
            "ID: 3, Title: title3, Time: 12:00, Length: 4 hours, X: 3.4, Y: 4.5, Cost: 70, List: list3, Description: description3";
        assertEquals(expected, hashTable.search(3));

        // Test that searching for a non-existent record returns null
        assertNull(hashTable.search(4));
    }


    public void testPrint() {
        // Set up for print
        hashTable.insert(4, "title4", "01:00", "5 hours", "4.5", "5.6", "80",
            "list4", "description4");
        hashTable.insert(5, "title5", "02:00", "6 hours", "5.6", "6.7", "90",
            "list5", "description5");

        String expected =
            "ID: 4, Title: title4, Time: 01:00, Length: 5 hours, X: 4.5, Y: 5.6, Cost: 80, List: list4, Description: description4\n"
                + "ID: 5, Title: title5, Time: 02:00, Length: 6 hours, X: 5.6, Y: 6.7, Cost: 90, List: list5, Description: description5\n";

        assertEquals(expected, hashTable.print());
    }
}

