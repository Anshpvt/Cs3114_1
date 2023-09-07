import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import student.TestCase;

public class CommandProcessorTest extends TestCase{
	private CommandProcessor tester;
	private String insertStr;
	private String searchStr;
	private String printStr;
	private String unknownCommandStr;
	

	public void setUp()
	{
		insertStr = "Successfully inserted record with ID 1\n";
		searchStr = "search successful 1\n";
		printStr = "blocks\n";
		unknownCommandStr = "unreconized input look\n";
		tester = new CommandProcessor("testIn.txt");
		
	}
	
//	// Test to check the "insert" command processing
//    public void testInsertCommand() {
//        assertTrue(systemOut().getHistory().contains(insertStr));
//    }
//
//    // Test to check the "search" command processing
//    public void testSearchCommand() {
//        assertTrue(systemOut().getHistory().contains(searchStr));
//    }
//
//    // Test to check the "print" command processing
//    public void testPrintCommand() {
//        assertTrue(systemOut().getHistory().contains(printStr));
//    }
//
//    // Test to check how unrecognized commands are handled
//    public void testUnknownCommand() {
//        assertTrue(systemOut().getHistory().contains(unknownCommandStr));
//    }
}
