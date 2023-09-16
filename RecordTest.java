import student.TestCase;
/**
 * Project 1
 */

/**
 * Tests Record class to ensure that each of its 
 * methods works as expected. The tests cover functionalities like fetching 
 * the record's key, accessing the associated seminar data, and checking 
 * if the record represents a tombstone.
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
public class RecordTest extends TestCase {

    private Record record;
    private Seminar seminar;

    /**
     * sets up for tests
     */
    public void setUp() {
        // Setting up a sample seminar and record
        seminar = new Seminar(1, "Test Seminar", "2023-09-13", 120, (short)50,
            (short)60, 100, new String[] { "Java", "OOP" },
            "A seminar about Java OOP.");
        record = new Record(1, seminar);
    }

    /**
     * Tests the getKey() method of the Record class.
     */
    public void testGetKey() {
        // Testing getKey() method
        assertEquals(1, record.getKey());
    }

    /**
     * Tests the getSeminar() method of the Record class.
     */
    public void testGetSeminar() {
        // Testing getSeminar() method
        assertEquals(seminar, record.getSeminar());
    }

    /**
     * Tests the isTombstone() method of the Record class for both
     * normal records and tombstone records.
     */
    public void testIsTombstone() {
        // Testing isTombstone() method for normal record
        assertFalse(record.isTombstone());

        // Testing isTombstone() method for a tombstone record
        Record tombstone = new Record(-1, null);
        assertTrue(tombstone.isTombstone());
    }
}
