
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


public class SemManager {
    /**
     * @param args
     *     Command line parameters
     */
    public static void main(String[] args) {
        // This is the main file for the program.
        Seminar sem = new Seminar();
        if (args.length < 3)//checks if corrent amount if inputs
        {
            System.out.println("invalid inputs");
            
        }
        if ((Integer.parseInt(args[0]) < 0) && ((Integer.parseInt(args[0]) & (Integer.parseInt(args[0]) - 1)) == 0))//checks if first digit is a power of 2
        {
            //set memory size
        }
        if ((Integer.parseInt(args[1]) < 0) && ((Integer.parseInt(args[1]) & (Integer.parseInt(args[1]) - 1)) == 0))// checks if third digit is a power of 2
        {
            SeminarDB db = new SeminarDB(Integer.parseInt(args[0]),Integer.parseInt(args[1]));//calls HashTable class
        }
        CommandProcessor com = new CommandProcessor(args[2]);//calls CommandProcesser class
    }
    
    /**
     * 
     * @param in
     */
    public static void insert(int in)
    {
        //if insert doesnt exist in hashtable print that is it is sucessful and the information of the ID
        //and inserts ID into hashtable
        //else print that it was not successful
    
    }
    
    /**
     * delete method
     * prints message if sucessful(Record with ID "" successfully deleted from the database)
     *  or not (Delete FAILED -- There is no record with ID '')
     *  deletes from hashtable
     */
    
    /**
     * search method
     * if found print(Found record with ID "":) along with all info on the ID
     * or not 
     */
    
    /**
     * print method
     * if print hashtable then print contents
     * if print block then print Freeblock list
     */
            
}
