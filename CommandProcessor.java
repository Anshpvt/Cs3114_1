import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * {Project Description Here}
 *
 * The CommandProcessor class extends the SeminarDB class and is responsible for
 * reading commands from a file and executing the corresponding actions on the 
 * SeminarDB. The valid commands include insert, delete, search, and print.
 *
 * @author {Stephen Ye, Ansh Patel}
 * @version {08/28/23}
 */

// On my honor:
// ... [honor code details omitted for brevity]

public class CommandProcessor extends SeminarDB {
    
    /**
     * Constructor for the CommandProcessor. Reads commands from a file 
     * and executes them on the SeminarDB.
     *
     * @param argv The file path to read commands from.
     */
    public CommandProcessor(String argv) {
        try {
            Scanner sc = new Scanner(new File(argv));
            while(sc.hasNext()) {
                String cmd = sc.next();
                int x; 
                String a, b, c, d, e, f, g, h;
                
                switch(cmd) {
                   case "insert":
                      // Read arguments for the insert command
                      x = sc.nextInt();
                      a = sc.next();
                      b = sc.next();
                      c = sc.next();
                      d = sc.next();
                      e = sc.next();
                      f = sc.next();
                      g = sc.next();
                      h = sc.next();
                     
                      // Execute the insert command
                      HashTable.insert(x, a, b, c, d, e, f, g, h);
                      System.out.println("Successfully inserted record with ID " + x);
                   break;

                   case "delete":
                      // Read the argument for the delete command
                      x = sc.nextInt();
                      
                      // Execute the delete command
                      SeminarDB.delete(x);
                      // You may need to handle output for "delete" if required
                   break;

                   case "search":
                      // Read the argument for the search command
                      x = sc.nextInt();
                      
                      // Output for search command (consider updating for actual search functionality)
                      System.out.println("Search successful " + x);
                   break;

                   case "print":
                      // Read the argument for the print command
                      b = sc.next();
                      
                      // Output for print command
                      System.out.println(b);
                   break;
                   
                   default:
                      // Handle unrecognized commands
                      System.out.println("Unrecognized input " + cmd);
                   break;
                }
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Converts a string by replacing spaces with commas followed by a space.
     * 
     * @param a The input string.
     * @return The modified string with commas in place of spaces.
     */
    public String toString(String a) {
        String sol = a.replaceAll("\\s+", ", ");
        return sol;
    }
}
