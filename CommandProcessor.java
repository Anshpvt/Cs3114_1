import java.io.File;
import java.util.Scanner;
/**
 * Project 1
 */

/**
 * The CommandProcessor class handles the reading and execution of commands from a given file. 
 * It interacts with the SeminarDB to insert, delete, search, or print seminar records.
 * Each command corresponds to a specific operation to be executed on the SeminarDB.
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


public class CommandProcessor {

    private SeminarDB database;
    private Seminar sem;
    private Record rec;

    /**
     * Constructs a new CommandProcessor that uses the provided database and
     * processes commands from the given file path.
     *
     * @param db The seminar database to use.
     * @param filePath Path to the file containing commands.
     */
    public CommandProcessor(SeminarDB db, String filePath) {
        this.database = db;
        processCommands(filePath);
    }


    /**
     * Reads and processes commands from the provided file path. Supported
     * commands include "insert", "delete", "search", and "print". 
     * Each command results in a specific operation being executed on the SeminarDB.
     *
     * @param filePath Path to the file containing commands.
     */
    void processCommands(String filePath) {
        try (Scanner scanner = new Scanner(new File(filePath))) {

            while (scanner.hasNext()) {
                String command = scanner.next();

                switch (command) {
                    case "insert":
                        int id = scanner.nextInt();
                        scanner.nextLine(); // Consume the rest of the line
                                            // after reading integer
                        String title = scanner.nextLine();
                        String date = scanner.next();
                        int length = scanner.nextInt();
                        short x = scanner.nextShort();
                        short y = scanner.nextShort();
                        int cost = scanner.nextInt();
                        scanner.nextLine();// Consume the rest of the line after
                                           // reading integer
                        String[] keywords = scanner.nextLine().trim().split(
                            "\\s+");
                        for (int i = 0; i < keywords.length; i++) {
                            keywords[i] = (keywords[i].trim());
                        }
                        String desc = scanner.nextLine().trim();
                        sem = new Seminar(id, title, date, length, x, y, cost,
                            keywords, desc);
                        rec = new Record(id, sem);
                        database.insert(rec);
                        break;

                    case "delete":
                        id = scanner.nextInt();
                        database.delete(id);
                        break;

                    case "search":
                        id = scanner.nextInt();
                        database.search(id);
                        break;

                    case "print":
                        String printWhich = scanner.next();
                        if (printWhich.equals("hashtable")) {
                            database.printHash();
                        }
                        else if (printWhich.equals("blocks")) {
                            database.printBlock();
                        }
                        break;

                    default:
                        System.out.println("Unrecognized command: " + command);
                        break;
                }
            }
        }
        catch (Exception e) {
            System.out.println("Error processing commands: " + e.getMessage());
        }
    }
}
