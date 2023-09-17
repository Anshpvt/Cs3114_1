import student.TestCase;

/**   
 * Project 1  
 */      
/**   
 * HandleTest class is responsible for testing the functionality of the Handle class.   
 * This class tests various methods provided in the Handle class to ensure their
 * correctness and reliability.
 * @author {Stephen Ye, Ansh Patel}  
 * @version {08/28/23} 
 */     
// On my honor: ...
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
        Handle handle = new Handle(1, 5, 10);
        int expectedEnd = 14; // 5 + 10 - 1
        assertEquals(expectedEnd, handle.getEnd());
    }
 
    /**
     * Tests the toString() method of Handle class.
     */
    public void testToString() {
        Handle handle = new Handle(1, 5, 10);
        String expectedString = "5 : 15";
        assertEquals(expectedString, handle.toString());
    }
 
    /**
     * Tests the getEnd() method of Handle class for a handle with zero length.
     */
    public void testGetEndWithZeroLength() {
        Handle handle = new Handle(1, 5, 0);
        int expectedEnd = 4;
        assertEquals(expectedEnd, handle.getEnd());  
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
    public void testHashCode_SameForIdenticalHandles() {    
        Handle handle1 = new Handle(1, 0, 32); 
        Handle handle2 = new Handle(1, 0, 32);   
        assertEquals(handle1.hashCode(), handle2.hashCode());
    }    
 
    /**
     * Tests if equal objects have the same hash code.
     */    
    public void testHashCode_EqualObjectsHaveSameHashCode() { 
        Handle handle1 = new Handle(1, 0, 32);      
        Handle handle2 = new Handle(1, 0, 32);
        assertTrue(handle1.equals(handle2));  
    }

    /**
     * Tests equals method with same object reference.
     */
    public void testEquals_SameObjectReference() {     
        Handle handle1 = new Handle(1, 0, 32);       
        assertTrue(handle1.equals(handle1));     
    }

    /**
     * Tests equals method with a null object.
     */
    public void testEquals_NullObject() {   
        Handle handle1 = new Handle(1, 0, 32);   
        assertFalse(handle1.equals(null));  
    }

    /**
     * Tests equals method with a different class object.
     */
    public void testEquals_DifferentClassObject() {  
        Handle handle1 = new Handle(1, 0, 32);     
        String notAHandle = "I'm not a Handle!";       
        assertFalse(handle1.equals(notAHandle));    
    }

    /**
     * Tests equals method for identical handles.
     */
    public void testEquals_IdenticalHandles() {      
        Handle handle1 = new Handle(1, 0, 32);     
        Handle handle2 = new Handle(1, 0, 32);
        assertTrue(handle1.equals(handle2));       
    }
    
    /**
     * Tests equals method for handles with different IDs.
     */
    public void testEquals_DifferentIds() {       
        Handle handle1 = new Handle(1, 0, 32);       
        Handle handle2 = new Handle(2, 0, 32);      
        assertFalse(handle1.equals(handle2));     
    }

    /**
     * Tests equals method for handles with different start locations.
     */
    public void testEquals_DifferentStartLocations() {     
        Handle handle1 = new Handle(1, 0, 32);      
        Handle handle2 = new Handle(1, 1, 32);      
        assertFalse(handle1.equals(handle2));     
    }

    /**
     * Tests equals method for handles with different lengths.
     */       
    public void testEquals_DifferentLengths() {   
        Handle handle1 = new Handle(1, 0, 32);    
        Handle handle2 = new Handle(1, 0, 33);     
        assertFalse(handle1.equals(handle2));
    }    
}
