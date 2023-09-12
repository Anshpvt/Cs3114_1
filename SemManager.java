import java.io.File;
/**
 * {Project Description Here}
 */

/**
 * The class containing the main method.
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

/**
 * The SemManager class serves as the initial entry point for a program designed to manage a seminar database.
 * The class handles various tasks, including command-line argument handling, input validation, file management,
 * database initialization, and command processing.
 */
public class SemManager {

    private SeminarDB seminarDB;
    private CommandProcessor commandProcessor;

    /**
     * Main entry point for the seminar management program.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        SemManager manager = new SemManager();
        manager.start(args);
    }

    /**
     * Initializes and starts the seminar manager.
     *
     * @param args Command line arguments.
     */
    public void start(String[] args) {
        // Handle command-line arguments
        if (args.length < 3) {
            System.out.println("Please provide the path to the command file.");
            return;
        }

        String commandFilePath = args[2];

        // Validate file input
        if (!isValidFile(commandFilePath)) {
            System.out.println("Invalid command file path provided.");
            return;
        }

        // Initialize database
        seminarDB = new SeminarDB(Integer.parseInt(args[0]), Integer.parseInt(args[1])); // Assuming SeminarDB has a default constructor

        // Process commands from the command file
        commandProcessor = new CommandProcessor(seminarDB, commandFilePath);
    }

    /**
     * Validates if the provided file path exists and is readable.
     *
     * @param filePath Path to the file to validate.
     * @return True if the file is valid, otherwise false.
     */
    private boolean isValidFile(String filePath) {
        File file = new File(filePath);
        return file.exists() && file.isFile() && file.canRead();
    }
}
