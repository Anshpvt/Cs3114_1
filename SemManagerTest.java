import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import student.TestCase;
import java.io.File;
import java.io.IOException;
/**
 * Project 1
 */

/**
 * Tests the SemManager class.
 * It ensures that command line arguments are processed correctly and that 
 * appropriate messages are displayed for invalid inputs. 
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
public class SemManagerTest extends TestCase {

    private SemManager semManager;
    private final ByteArrayOutputStream outContent =
        new ByteArrayOutputStream();

    /**
     * sets up for tests
     */
    public void setUp() {
        semManager = new SemManager();

        System.setOut(new PrintStream(outContent));
    }

    /**
     * Test that the SemManager displays the correct error message 
     * when fewer than 3 arguments are provided.
     */
    public void testInvalidArguments() {
        semManager.start(new String[] { "100", "200" });
        assertEquals("Please provide the path to the command file.", outContent
            .toString().trim());
    }

    /**
     * Test that the SemManager displays the correct error message 
     * when an invalid file path is provided.
     */
    public void testInvalidFilePath() {
        semManager.start(new String[] { "100", "200", "invalidFilePath" });
        assertEquals("Invalid command file path provided.", outContent
            .toString().trim());
    }

    /**
     * Test the initialization process of SemManager 
     * when a valid file path is provided.
     */
    public void testValidFileInitialization() {
        semManager.start(new String[] { "100", "200", "validFilePath" });
        assertFalse(outContent.toString().isEmpty());
    }

    /**
     * Test that the SemManager displays the correct error message 
     * when no arguments are provided.
     */
    public void testNoArguments() {
        semManager.start(new String[] {});
        assertEquals("Please provide the path to the command file.", outContent
            .toString().trim());
    }

    /**
     * Test that the SemManager displays the correct error message 
     * when only one argument is provided.
     */
    public void testSingleArgument() {
        semManager.start(new String[] { "100" });
        assertEquals("Please provide the path to the command file.", outContent
            .toString().trim());
    }

    /**
     * Test that the SemManager displays the correct error message 
     * when valid arguments are provided but the file path is invalid.
     */
    public void testValidArgsInvalidFilePath() {
        semManager.start(new String[] { "100", "200", "/non/existent/path" });
        assertEquals("Invalid command file path provided.", outContent
            .toString().trim());
    }

    /**
     * Test the response of the SemManager 
     * when non-integer arguments are provided for memory size and hash table size.
     */
    public void testNonIntegerArguments() {
        semManager.start(new String[] { "100A", "200B", "validFilePath" });
        assertFalse(outContent.toString().isEmpty());
    }

    /**
     * Test the main method of the SemManager class with fewer than 3 arguments.
     */
    public void testMainWithInvalidArguments() {
        SemManager.main(new String[] { "100", "200" });
        assertEquals("Please provide the path to the command file.", outContent
            .toString().trim());
    }

    /**
     * Test the main method of the SemManager class with an invalid file path.
     */
    public void testMainWithInvalidFilePath() {
        SemManager.main(new String[] { "100", "200", "invalidFilePath" });
        assertEquals("Invalid command file path provided.", outContent
            .toString().trim());
    }

    /**
     * Test the main method of the SemManager class with a valid file path.
     */
    public void testMainValidFileInitialization() {
        SemManager.main(new String[] { "100", "200", "validFilePath" });
        // Assuming there's another specific output you expect upon valid
        // initialization
        // Replace with the appropriate assertion
        assertFalse(outContent.toString().isEmpty());
    }

    /**
     * Test the main method of the SemManager class with no arguments.
     */
    public void testMainNoArguments() {
        SemManager.main(new String[] {});
        assertEquals("Please provide the path to the command file.", outContent
            .toString().trim());
    }

    /**
     * Test the main method of the SemManager class with a single argument.
     */
    public void testMainSingleArgument() {
        SemManager.main(new String[] { "100" });
        assertEquals("Please provide the path to the command file.", outContent
            .toString().trim());
    }

    /**
     * Test the main method of the SemManager class 
     * with valid arguments but an invalid file path.
     */
    public void testMainValidArgsInvalidFilePath() {
        SemManager.main(new String[] { "100", "200", "/non/existent/path" });
        assertEquals("Invalid command file path provided.", outContent
            .toString().trim());
    }

    /**
     * Test the main method of the SemManager class 
     * with non-integer arguments for memory size and hash table size.
     */
    public void testMainNonIntegerArguments() {
        SemManager.main(new String[] { "100A", "200B", "validFilePath" });
        // Assuming there's another specific output you expect for non-integer
        // arguments
        // Replace with the appropriate assertion
        assertFalse(outContent.toString().isEmpty());
    }


    /**
     * Test the validity of the file path using a valid temporary file.
     */
    public void testValidFilePath() {
        File tempFile = null;
        try {
            // Create a temporary file for testing purposes
            tempFile = File.createTempFile("testValidFilePath", ".txt");
            // Ensure the file will be deleted on JVM exit
            tempFile.deleteOnExit();

            semManager.start(new String[] { "100", "200", tempFile
                .getAbsolutePath() });

            // Since the file is valid, the output shouldn't contain the error
            // message.
            assertFalse("Invalid command file path provided.".equals(outContent
                .toString().trim()));

        }
        catch (IOException e) {
            fail("Failed to create a temporary file.");
        }
        finally {
            if (tempFile != null && tempFile.exists()) {
                tempFile.delete();
            }
        }
    }
}
