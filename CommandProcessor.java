import java.io.File;
import java.util.Scanner;

public class CommandProcessor {

    private SeminarDB database;

    public CommandProcessor(SeminarDB db, String filePath) {
        this.database = db;
        processCommands(filePath);
    }

    private void processCommands(String filePath) {
        try (Scanner scanner = new Scanner(new File(filePath))) {

            while (scanner.hasNext()) {
                String command = scanner.next();

                switch (command) {
                    case "insert":
                        int id = scanner.nextInt();
                        scanner.nextLine();  // Consume the rest of the line after reading integer
                        String title = scanner.nextLine().trim();
                        String date = scanner.nextLine().trim();
                        int length = scanner.nextInt();
                        short x = scanner.nextShort();
                        short y = scanner.nextShort();
                        int cost = scanner.nextInt();
                        scanner.nextLine();  // Consume the rest of the line after reading integer
                        String[] keywords = scanner.nextLine().split(",");  // Assuming keywords are comma-separated on one line
                        for (int i = 0; i < keywords.length; i++) {
                            keywords[i] = keywords[i].trim();
                        }
                        String desc = scanner.nextLine().trim();
                        
                        database.insert(id, title, date, length, x, y, cost, keywords, desc);
                        break;

                    case "delete":
                        id = scanner.nextInt();
                        database.delete(id);
                        break;

                    case "search":
                        id = scanner.nextInt();
                        Seminar result = database.search(id);
                        if (result != null) {
                            System.out.println(result);
                        } else {
                            System.out.println("Seminar with ID " + id + " not found.");
                        }
                        break;

                    case "print":
                        database.print();
                        break;

                    default:
                        System.out.println("Unrecognized command: " + command);
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error processing commands: " + e.getMessage());
        }
    }
}
