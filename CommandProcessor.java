import java.io.File;
import java.util.Scanner;

/**
 * CommandProcessor processes a sequence of commands related to seminars from a file.
 * The commands are to insert, delete, search, and print seminars in a seminar database.
 */
public class CommandProcessor {

    // The SeminarDB instance where the commands will be applied.
    private SeminarDB database;

    /**
     * Constructor initializes the CommandProcessor with a seminar database and a file path containing commands.
     * @param db        The seminar database to be used.
     * @param filePath  The file containing the list of commands.
     */
    public CommandProcessor(SeminarDB db, String filePath) {
        this.database = db;
        processCommands(filePath); // Process commands immediately upon object creation.
    }

    /**
     * Processes commands from a specified file.
     * Each command has a specific structure and invokes a corresponding method on the seminar database.
     * @param filePath  The file containing the list of commands.
     */
    private void processCommands(String filePath) {
        // Using a try-with-resources block to ensure the Scanner is closed after use.
        try (Scanner scanner = new Scanner(new File(filePath))) {

            // Continue reading as long as there are commands in the file.
            while (scanner.hasNext()) {
                String command = scanner.next();

                switch (command) {
                    case "insert":
                        // Read parameters for the seminar.
                        int id = scanner.nextInt();
                        scanner.nextLine();  // Consume the rest of the line after reading the integer to move to the next line.
                        String title = scanner.nextLine().trim();
                        String date = scanner.nextLine().trim();
                        int length = scanner.nextInt();
                        short x = scanner.nextShort();
                        short y = scanner.nextShort();
                        int cost = scanner.nextInt();
                        scanner.nextLine();  // Consume the rest of the line after reading the integer.
                        // Assuming keywords are comma-separated on one line.
                        String[] keywords = scanner.nextLine().split(",");  
                        for (int i = 0; i < keywords.length; i++) {
                            keywords[i] = keywords[i].trim();
                        }
                        String desc = scanner.nextLine().trim();

                        // Invoke the insert method on the database.
                        database.insert(id, title, date, length, x, y, cost, keywords, desc);
                        break;

                    case "delete":
                        id = scanner.nextInt();
                        // Invoke the delete method on the database.
                        database.delete(id);
                        break;

                    case "search":
                        id = scanner.nextInt();
                        // Search for a seminar by its ID.
                        Seminar result = database.search(id);
                        // Print the result if found; otherwise, display a message.
                        if (result != null) {
                            System.out.println(result);
                        } else {
                            System.out.println("Seminar with ID " + id + " not found.");
                        }
                        break;

                    case "print":
                        // Print all seminars in the database.
                        database.print();
                        break;

                    default:
                        // Any other unrecognized command will print a message.
                        System.out.println("Unrecognized command: " + command);
                        break;
                }
            }
        } catch (Exception e) {
            // Handle any exceptions that might arise during processing.
            System.out.println("Error processing commands: " + e.getMessage());
        }
    }
}

