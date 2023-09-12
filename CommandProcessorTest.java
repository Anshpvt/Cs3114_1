
import java.io.PrintWriter;
import java.io.File;

public class CommandProcessorTest {
    
    private static final String TEST_FILE_PATH = "testCommands.txt";
    private SeminarDB seminarDB;

    public void setup() {
        seminarDB = new SeminarDB(10, 10); // You might need to adjust the memory and hash sizes as per your requirement.
    }

    public void testInsertCommand() {
        try {
            PrintWriter writer = new PrintWriter(TEST_FILE_PATH, "UTF-8");
            writer.println("insert 1 Seminar1 12/12/2022 5 50 50 500 keyword1 keyword2 Description1");
            writer.close();

            CommandProcessor cp = new CommandProcessor(seminarDB, TEST_FILE_PATH);
            assertNotNull(seminarDB.getHash().search(1));

        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    public void testDeleteCommand() {
        try {
            PrintWriter writer = new PrintWriter(TEST_FILE_PATH, "UTF-8");
            writer.println("insert 1 Seminar1 12/12/2022 5 50 50 500 keyword1 keyword2 Description1");
            writer.println("delete 1");
            writer.close();

            CommandProcessor cp = new CommandProcessor(seminarDB, TEST_FILE_PATH);
            assertNull(seminarDB.getHash().search(1));

        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }
    
    public void testSearchCommand() {
        try {
            PrintWriter writer = new PrintWriter(TEST_FILE_PATH, "UTF-8");
            writer.println("insert 1 Seminar1 12/12/2022 5 50 50 500 keyword1 keyword2 Description1");
            writer.println("search 1");
            writer.close();

            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
            
            CommandProcessor cp = new CommandProcessor(seminarDB, TEST_FILE_PATH);

            String expectedOutput = "Found record with ID 1:";
            assertTrue(outContent.toString().contains(expectedOutput));
            
            System.setOut(System.out); // Resetting the output stream to console

        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    public void testPrintCommand() {
        try {
            PrintWriter writer = new PrintWriter(TEST_FILE_PATH, "UTF-8");
            writer.println("insert 1 Seminar1 12/12/2022 5 50 50 500 keyword1 keyword2 Description1");
            writer.println("print hashtable");
            writer.close();

            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
            
            CommandProcessor cp = new CommandProcessor(seminarDB, TEST_FILE_PATH);

            String expectedOutput = "Hashtable:";
            assertTrue(outContent.toString().contains(expectedOutput));
            
            System.setOut(System.out); // Resetting the output stream to console

        } catch (Exception e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }
}
