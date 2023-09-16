
import student.TestCase;

public class CommandProcessorTest extends TestCase {
    private SeminarDB testDB;
    private CommandProcessor tester;
    private String insertStr;
    private String searchStr;
    private String printHashStr;
    private String printBlockStr;
    private String unknownCommandStr;

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


    // Test to check the "print" command processing
    public void testPrintHashCommand() {
        tester = new CommandProcessor(testDB, "testIn.txt");
        assertFalse(systemOut().getHistory().contains(printHashStr));
    }


    // Test to check the "print" command processing
    public void testPrintBlockCommand() {
        tester = new CommandProcessor(testDB, "testIn.txt");
        assertFalse(systemOut().getHistory().contains(printBlockStr));
    }


    // Test to check how unrecognized commands are handled
    public void testUnknownCommand() {
        tester = new CommandProcessor(testDB, "testIn.txt");
        assertFalse(systemOut().getHistory().contains(unknownCommandStr));
    }


    // Test to check the "delete" command processing for a successful delete
    public void testSuccessfulDeleteCommand() {
        tester = new CommandProcessor(testDB, "successfulDelete.txt"); // assuming
                                                                       // you
                                                                       // have
                                                                       // this
                                                                       // file
                                                                       // with
                                                                       // the
                                                                       // right
                                                                       // commands
        assertTrue(systemOut().getHistory().contains(
            "Record with ID 2 successfully deleted from the database"));
    }


    // Test to check the "delete" command processing for an unsuccessful delete
    public void testFailedDeleteCommand() {
        tester = new CommandProcessor(testDB, "failedDelete.txt");
        assertEquals(systemOut().getHistory(),
            "Delete FAILED -- There is no record with ID 2\n");
    }


    // Test to check the "print" command for hashtable
    public void testPrintHashtableCommand() {
        tester = new CommandProcessor(testDB, "printHashTable.txt");
        assertTrue(systemOut().getHistory().contains("Hashtable: \n"
            + "total records: 0"));
        assertTrue(systemOut().getHistory().contains("Hashtable: \n" + "1: 1\n"
            + "total records: 1"));
    }


    // Test to check the "print" command for blocks
    public void testPrintBlocksCommand() {
        tester = new CommandProcessor(testDB, "printBlocks.txt");

        assertTrue(systemOut().getHistory().contains("Freeblock List:\n"
            + "2 : 3\n" + "in use Block:"));
        assertTrue(systemOut().getHistory().contains("Freeblock List:\n"
            + "2 : 3\n" + "2 : 3\n" + "2 : 3\n" + "2 : 3\n" + "2 : 3\n"
            + "2 : 3\n" + "in use Block:"));
    }


    // Test to check the "print" command for an invalid type
    public void testInvalidPrintCommand() {
        tester = new CommandProcessor(testDB, "invalidPrint.txt");
        assertEquals(systemOut().getHistory(), "");
    }


    // Test for unrecognized commands
    public void testUnrecognizedCommand() {
        tester = new CommandProcessor(testDB, "testCommands.txt");
        assertEquals(systemOut().getHistory(),
            "Error processing commands: null\n");
    }


    // Test for an edge case - an empty file
    public void testEmptyFileCommand() {
        tester = new CommandProcessor(testDB, "emptyFile.txt");
        assertEquals(systemOut().getHistory(), "");
    }

}
