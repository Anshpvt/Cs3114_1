import student.TestCase;

/**   
 * Project 1  
 */      
/**   
 * HandleTest class is responsible for testing the 
 * functionality of the Handle class.   
 * This class tests various methods provided in the 
 * Handle class to ensure their
 * correctness and reliability.
 * @author {Stephen Ye, Ansh Patel}  
 * @version {08/28/23} 
 */     
//On my honor:  
// - I have not used source code obtained from another current or   
// former student, or any other unauthorized source, either  
// modified or unmodified.
// - All source code and documentation used in my program is 
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint  
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student   
// may help me debug my program so long as neither of us writes    
// anything during the discussion or modifies any computer file    
// during the discussion. I have violated neither the spirit nor    
// letter of this restriction.
public class HandleTest extends TestCase {     
    private Handle handle; 
     
    /**
     * sets up for tests
     */
    public void setUp() { 
        handle = new Handle(1, 10, 100);
    }
     
    /**
     * Tests the getID() method of Handle class.
     */
    public void testGetID() { 
        assertEquals(1, handle.getID());
    }
     
    /**
     * Tests the getStartLocation() method of Handle class.
     */
    public void testGetStartLocation() {
        assertEquals(10, handle.getStartLocation()); 
    }
 
    /**
     * Tests the getLength() method of Handle class.
     */ 
    public void testGetLength() {
        assertEquals(100, handle.getLength());  
    }
 
    /**
     * Tests the getEnd() method of Handle class.
     */
    public void testGetEnd() {
        Handle handle1 = new Handle(1, 5, 10);
        int expectedEnd = 14; // 5 + 10 - 1
        assertEquals(expectedEnd, handle1.getEnd());
    }
 
    /**
     * Tests the toString() method of Handle class.
     */
    public void testToString() {
        Handle handle1 = new Handle(1, 5, 10);
        String expectedString = "5 : 15";
        assertEquals(expectedString, handle1.toString());
    }
 
    /**
     * Tests the getEnd() method of Handle class for 
     * a handle with zero length.
     */
    public void testGetEndWithZeroLength() {
        Handle handle1 = new Handle(1, 5, 0);
        int expectedEnd = 4;
        assertEquals(expectedEnd, handle1.getEnd());  
    }
 
    /**
     * Tests the toString() method of Handle class for a handle with zero length.
     */
    public void testToStringWithZeroLength() {  
        Handle handle = new Handle(1, 5, 0); 
        String expectedString = "5 : 5";
        assertEquals(expectedString, handle.toString());   
    }    
    
    /**
     * Tests the hashCode method for identical handles.
     */
    public void testHashCodeSameForIdenticalHandles() {    
        Handle handle1 = new Handle(1, 0, 32); 
        Handle handle2 = new Handle(1, 0, 32);   
        assertEquals(handle1.hashCode(), handle2.hashCode());
    }    
 
    /**
     * Tests if equal objects have the same hash code.
     */    
    public void testHashCodeEqualObjectsHaveSameHashCode() { 
        Handle handle1 = new Handle(1, 0, 32);      
        Handle handle2 = new Handle(1, 0, 32);
        assertTrue(handle1.equals(handle2));  
    }

    /**
     * Tests equals method with same object reference.
     */
    public void testEqualsSameObjectReference() {     
        Handle handle1 = new Handle(1, 0, 32);       
        assertTrue(handle1.equals(handle1));     
    }

    /**
     * Tests equals method with a null object.
     */
    public void testEqualsNullObject() {   
        Handle handle1 = new Handle(1, 0, 32);   
        assertFalse(handle1.equals(null));  
    }

    /**
     * Tests equals method with a different class object.
     */
    public void testEqualsDifferentClassObject() {  
        Handle handle1 = new Handle(1, 0, 32);     
        String notAHandle = "I'm not a Handle!";       
        assertFalse(handle1.equals(notAHandle));    
    }

    /**
     * Tests equals method for identical handles.
     */
    public void testEqualsIdenticalHandles() {      
        Handle handle1 = new Handle(1, 0, 32);     
        Handle handle2 = new Handle(1, 0, 32);
        assertTrue(handle1.equals(handle2));       
    }
    
    /**
     * Tests equals method for handles with different IDs.
     */
    public void testEqualsDifferentIds() {       
        Handle handle1 = new Handle(1, 0, 32);       
        Handle handle2 = new Handle(2, 0, 32);      
        assertFalse(handle1.equals(handle2));     
    }

    /**
     * Tests equals method for handles with different start locations.
     */
    public void testEqualsDifferentStartLocations() {     
        Handle handle1 = new Handle(1, 0, 32);      
        Handle handle2 = new Handle(1, 1, 32);      
        assertFalse(handle1.equals(handle2));     
    }

    /**
     * Tests equals method for handles with different lengths.
     */       
    public void testEqualsDifferentLengths() {   
        Handle handle1 = new Handle(1, 0, 32);    
        Handle handle2 = new Handle(1, 0, 33);     
        assertFalse(handle1.equals(handle2));
    }    
}
