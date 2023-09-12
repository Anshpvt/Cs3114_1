import java.io.File;
import java.util.Scanner;

public class CommandProcessor {

    private SeminarDB database;
    private Seminar sem;
    private Record rec;

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
                        String title = scanner.nextLine();
                        String date = scanner.next();
                        int length = scanner.nextInt();
                        short x = scanner.nextShort();
                        short y = scanner.nextShort();
                        int cost = scanner.nextInt();
                        scanner.nextLine();// Consume the rest of the line after reading integer
                        String[] keywords = scanner.nextLine().trim().split("\\s+");  
                        for (int i = 0; i < keywords.length; i++) {
                            keywords[i] = (keywords[i].trim());
                        }
                        String desc = scanner.nextLine().trim();
                        sem = new Seminar(id, title, date, length, x, y, cost, keywords, desc);
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
                    	if (printWhich.equals("hashtable"))
                    	{
                    		database.printHash();
                    	}
                    	else if (printWhich.equals("blocks"))
                    	{
                    		database.printBlock();
                    	}
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
