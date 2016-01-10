import static org.junit.Assert.*;
import org.junit.*;

/**
 * Implementation of the LinkedList test class.
 */
public class LinkedListTest {
    private Object[] objects;
    private LinkedList linkedList;
    
    public LinkedListTest() {
        objects = new Object[] { 0, "some object", 128, "xpto" };
    }

    @Before
    public void createLinkedList() {
        linkedList = new LinkedList();
        
        assertTrue(linkedList.isEmpty());
        assertEquals(linkedList.size(), 0);
    }

    @Test
    public void outOfBoundsIndexShouldFail() {
        ErrorMessage errorMessage = ErrorMessage.INDEX_OUT_OF_BOUNDS;
    
        for (int i = -1; i <= linkedList.size(); i++) {
            validateReturnObject(linkedList.get(i), errorMessage);
            validateReturnObject(linkedList.remove(i), errorMessage);
            validateReturnObject(linkedList.add(i, ""), errorMessage);
        }
    }
    
    @Test
    public void addingNullObjectShouldFail() {
        ErrorMessage errorMessage = ErrorMessage.INVALID_ARGUMENT;
    
        validateReturnObject(linkedList.add(null), errorMessage);
        
        validateReturnObject(linkedList.add(""));
        validateReturnObject(linkedList.add(0, null), errorMessage);
    }
    
    @Test
    public void addingAndRetrievingObjectsShouldNotFail() {
        validateReturnObject(linkedList.add(0));
        assertFalse(linkedList.isEmpty());
        assertEquals(linkedList.size(), 1);
        
        validateReturnObject(linkedList.add(0, 1));
        assertFalse(linkedList.isEmpty());
        assertEquals(linkedList.size(), 2);
        
        validateReturnObject(linkedList.add(2));
        assertFalse(linkedList.isEmpty());
        assertEquals(linkedList.size(), 3);
        
        validateReturnObject(linkedList.get(0), 1);
        validateReturnObject(linkedList.get(1), 0);
        validateReturnObject(linkedList.get(2), 2);
    }
    
    @Test
    public void removingObjectShouldNotFail() {
        validateReturnObject(linkedList.add(0));
        validateReturnObject(linkedList.add(1));
        validateReturnObject(linkedList.add(2));
        assertFalse(linkedList.isEmpty());
        assertEquals(linkedList.size(), 3);
        
        validateReturnObject(linkedList.remove(0), 0);
        assertFalse(linkedList.isEmpty());
        assertEquals(linkedList.size(), 2);
        
        validateReturnObject(linkedList.get(0), 1);
        validateReturnObject(linkedList.remove(1), 2);
        assertFalse(linkedList.isEmpty());
        assertEquals(linkedList.size(), 1);
        
        validateReturnObject(linkedList.get(0), 1);
        validateReturnObject(linkedList.remove(0), 1);
        assertTrue(linkedList.isEmpty());
        assertEquals(linkedList.size(), 0);
    }
    
    @Test
    public void addingAndRemoving2MillionObjectsToFromTailShouldNotFail() {
        int count = 2000000;
        for (int i = 0; i < count; i++)
            validateReturnObject(linkedList.add(objects[i % 4]));
        
        assertFalse(linkedList.isEmpty());
        assertEquals(linkedList.size(), count);
        
        for (int i = count - 1; i >= 0; i--)
            validateReturnObject(linkedList.remove(i), objects[i % 4]);
        
        assertTrue(linkedList.isEmpty());
        assertEquals(linkedList.size(), 0);
    }
    
    private void validateReturnObject(ReturnObject returnObject) {
        assertFalse(returnObject.hasError());
    }
    
    private void validateReturnObject(ReturnObject returnObject,
            Object object) {
        assertFalse(returnObject.hasError());
        assertSame(returnObject.getReturnValue(), object);
    }
    
    private void validateReturnObject(ReturnObject returnObject,
            ErrorMessage errorMessage) {
        
        assertTrue(returnObject.hasError());
        assertEquals(returnObject.getError(), errorMessage);
    }
}
