import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

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
        printHashStr = "Hashtable:\n"
        		+ "1: 1\n"
        		+ "2: 2\n"
        		+ "3: 3\n"
        		+ "5: 10\n"
        		+ "total records: 4";
        printBlockStr = "Freeblock List:\n"
        		+ "256: 256";
        unknownCommandStr = "unreconized input look\n";
        testDB = new SeminarDB (2, 2);
        tester = new CommandProcessor(testDB, "testIn.txt");

    }


    // Test to check the "insert" command processing
    public void testInsertCommand() {
        assertTrue(systemOut().getHistory().contains(insertStr));
        assertTrue(systemOut().getHistory().contains("Insert FAILED - There is already a record with ID 3"));
    }


    // Test to check the "search" command processing
    public void testSearchCommand() {
    	assertFalse(systemOut().getHistory().contains(searchStr));
        assertTrue(systemOut().getHistory().contains("Search FAILED -- There is no record with ID 2"));
    }


    // Test to check the "print" command processing
    public void testPrintHashCommand() {
    	assertFalse(systemOut().getHistory().contains(printHashStr));
    }
    
 // Test to check the "print" command processing
    public void testPrintBlockCommand() {
    	assertFalse(systemOut().getHistory().contains(printBlockStr));
    }


    // Test to check how unrecognized commands are handled
    public void testUnknownCommand() {
        assertFalse(systemOut().getHistory().contains(unknownCommandStr));
    }
}
