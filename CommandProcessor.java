import java.io.File;


import java.io.FileNotFoundException;
import java.util.Scanner;

/**
* {Project Description Here}
 */

/**
 * 
 *
 * @author {Stephen Ye, Ansh Patel}
 * @version {08/28/23}
 */

// On my honor:
// - I have not used source code obtained from another current or
//   former student, or any other unauthorized source, either
//   modified or unmodified.
//
// - All source code and documentation used in my program is
//   either my original work, or was derived by me from the
//   source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
//   anyone other than my partner (in the case of a joint
//   submission), instructor, ACM/UPE tutors or the TAs assigned
//   to this course. I understand that I may discuss the concepts
//   of this program with other students, and that another student
//   may help me debug my program so long as neither of us writes
//   anything during the discussion or modifies any computer file
//   during the discussion. I have violated neither the spirit nor
//   letter of this restriction.

public class CommandProcessor extends SeminarDB{
    
    public CommandProcessor(String argv)
    {
        try {
            Scanner sc = new Scanner(new File(argv));
            while(sc.hasNext()) {
                String cmd = sc.next();
                int x; 
                String a; String b; String c; String d; String e;
                String f; String g; String h;
                switch(cmd) {
                   case "insert" :
                      x = sc.nextInt();
                      a = sc.next();
                      b = sc.next();
                      c = sc.next();
                      d = sc.next();
                      e = sc.next();
                      f = sc.next();
                      g = sc.next();
                      h = sc.next();
                     
                      HashTable.insert(x, a, b, c, d, e, f, g, h);
                      System.out.println("Successfully inserted record with ID " + x);
                   break;

                   case "delete" :
                      x = sc.nextInt();
                      SeminarDB.delete(x);
                      // You may need to handle output for "delete" if required
                   break;

                   case "search" :
                      x = sc.nextInt();
                      System.out.println("search successful " + x);
                   break;

                   case "print" :
                      b = sc.next();
                      System.out.println(b);
                   break;
                   
                   default:
                      System.out.println("unreconized input " + cmd);
                   break;
                }
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public String toString(String a)
    {
        String sol = a.replaceAll("\\s+", ", ");
        return sol;
    }
}