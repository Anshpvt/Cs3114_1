import student.TestCase;
/**
 * Project 1
 */

/**
* The CommandProcessorTest class is a suite of unit tests designed 
 * to test the CommandProcessor class. It extends the TestCase class
 * which provides the necessary functionality to perform these tests.
 * The tests are focused on various functionalities such as adding, 
 * deleting, printing, and handling invalid commands among others. 
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

public class CommandProcessorTest extends TestCase {
    private SeminarDB testDB;
    private CommandProcessor tester;
    private String insertStr;
    private String searchStr;
    private String printHashStr;
    private String printBlockStr;
    private String unknownCommandStr;

    /*
     * sets up for tests
     */
    public void setUp() {
        insertStr = "Successfully inserted record with ID 1\n"
            + "ID: 1, Title: Overview of HCI Research at VT\n"
            + "Date: 0610051600, Length: 90, X: 10, Y: 10, Cost: 45\n"
            + "Description: This seminar will present an overview of HCI research at VT\n"
            + "Keywords: HCI, Computer_Science, VT, Virginia_Tech\n"
            + "Size: 173";
        searchStr = "Found record with ID 3:\n"
            + "ID: 3, Title: Computing Systems Research at VT\n"
            + "Date: 0701250830, Length: 30, X: 30, Y: 10, Cost: 17\n"
            + "Description: Seminar about the      Computing systems research at      VT\n"
            + "Keywords: high_performance_computing, grids, VT, computer, science";
        printHashStr = "Hashtable:\n" + "1: 1\n" + "2: 2\n" + "3: 3\n"
            + "5: 10\n" + "total records: 4";
        printBlockStr = "Freeblock List:\n" + "256: 256";
        unknownCommandStr = "unreconized input look\n";
        testDB = new SeminarDB(2, 2);

    }

    /**
     * Tests if the hash table print command works correctly.
     */
    public void testPrintHashCommand() {
        tester = new CommandProcessor(testDB, "testIn.txt");
        assertFalse(systemOut().getHistory().contains(printHashStr));
    }
    
    /**
     * Tests if the block print command works correctly.
     */
    public void testPrintBlockCommand() {
        tester = new CommandProcessor(testDB, "testIn.txt");
        assertFalse(systemOut().getHistory().contains(printBlockStr));
    }
    
    /**
     * Tests how the CommandProcessor responds to an unknown command.
     */
    public void testUnknownCommand() {
        tester = new CommandProcessor(testDB, "testIn.txt");
        assertFalse(systemOut().getHistory().contains(unknownCommandStr));
    }
    
    /**
     * Tests if a record can be successfully deleted.
     */
    public void testSuccessfulDeleteCommand() {
        tester = new CommandProcessor(testDB, "successfulDelete.txt"); 
        assertTrue(systemOut().getHistory().contains(
            "Record with ID 2 successfully deleted from the database"));
    }
    
    /**
     * Tests the scenario when trying to delete a non-existent record.
     */
    public void testFailedDeleteCommand() {
        tester = new CommandProcessor(testDB, "failedDelete.txt");
        assertEquals(systemOut().getHistory(),
            "Delete FAILED -- There is no record with ID 2\n");
    }
    
    /**
     * Tests the command to print the hash table.
     */
    public void testPrintHashtableCommand() {
        tester = new CommandProcessor(testDB, "printHashTable.txt");
        assertTrue(systemOut().getHistory().contains("Hashtable: \n"
            + "total records: 0"));
        assertTrue(systemOut().getHistory().contains("Hashtable: \n" + "1: 1\n"
            + "total records: 1"));
    }
    
    /**
     * Tests the command to print the list of blocks.
     */
    public void testPrintBlocksCommand() {
        tester = new CommandProcessor(testDB, "printBlocks.txt");

        assertTrue(systemOut().getHistory().contains("Freeblock List:\n"
            + "2 : 3\n" + "in use Block:"));
        assertTrue(systemOut().getHistory().contains("Freeblock List:\n"
            + "2 : 3\n" + "2 : 3\n" + "2 : 3\n" + "2 : 3\n" + "2 : 3\n"
            + "2 : 3\n" + "in use Block:"));
    }

    /**
     * Tests the scenario when an invalid print command is provided.
     */
    public void testInvalidPrintCommand() {
        tester = new CommandProcessor(testDB, "invalidPrint.txt");
        assertEquals(systemOut().getHistory(), "");
    }
    
    /**
     * Tests the response when an unrecognized command is given.
     */
    public void testUnrecognizedCommand() {
        tester = new CommandProcessor(testDB, "testCommands.txt");
        assertEquals(systemOut().getHistory(),
            "Error processing commands: null\n");
    }

    /**
     * Tests the response when the command file is empty.
     */
    public void testEmptyFileCommand() {
        tester = new CommandProcessor(testDB, "emptyFile.txt");
        assertEquals(systemOut().getHistory(), "");
    }

}
