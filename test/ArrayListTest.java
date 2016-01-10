import static org.junit.Assert.*;
import org.junit.*;

/**
 * Implementation of the ArrayList test class.
 */
public class ArrayListTest {
    private ArrayList arrayList;

    @Before
    public void createArrayList() {
        arrayList = new ArrayList();
        
        assertEquals(arrayList.capacity(), 0);
        assertTrue(arrayList.isEmpty());
        assertEquals(arrayList.size(), 0);
    }

    @Test
    public void outOfBoundsIndexShouldFail() {
        ErrorMessage errorMessage = ErrorMessage.INDEX_OUT_OF_BOUNDS;
    
        for (int i = -1; i <= arrayList.size(); i++) {
            validateReturnObject(arrayList.get(i), errorMessage);
            validateReturnObject(arrayList.remove(i), errorMessage);
            validateReturnObject(arrayList.add(i, ""), errorMessage);
        }
    }
    
    @Test
    public void addingNullObjectShouldFail() {
        ErrorMessage errorMessage = ErrorMessage.INVALID_ARGUMENT;
    
        validateReturnObject(arrayList.add(null), errorMessage);
        
        validateReturnObject(arrayList.add(""));
        validateReturnObject(arrayList.add(0, null), errorMessage);
    }
    
    @Test
    public void addingAndRetrievingObjectsShouldNotFail() {
        validateReturnObject(arrayList.add(0));
        assertEquals(arrayList.capacity(), arrayList.INITIAL_CAPACITY);
        assertFalse(arrayList.isEmpty());
        assertEquals(arrayList.size(), 1);
        
        validateReturnObject(arrayList.add(0, 1));
        assertEquals(arrayList.capacity(), arrayList.INITIAL_CAPACITY);
        assertFalse(arrayList.isEmpty());
        assertEquals(arrayList.size(), 2);
        
        validateReturnObject(arrayList.add(2));
        assertEquals(arrayList.capacity(), arrayList.INITIAL_CAPACITY);
        assertFalse(arrayList.isEmpty());
        assertEquals(arrayList.size(), 3);
        
        validateReturnObject(arrayList.get(0), 1);
        validateReturnObject(arrayList.get(1), 0);
        validateReturnObject(arrayList.get(2), 2);
    }
    
    @Test
    public void addingMoreThanCapacityShouldGrowArray() {
        int count = arrayList.INITIAL_CAPACITY + 1;
        for (int i = 0; i < count; i++)
            validateReturnObject(arrayList.add(i));
        
        assertTrue(arrayList.capacity() > arrayList.INITIAL_CAPACITY);
        assertFalse(arrayList.isEmpty());
        assertEquals(arrayList.size(), count);
        
        for (int i = 0; i < count; i++)
            validateReturnObject(arrayList.get(i), i);
    }
    
    @Test
    public void removingObjectShouldNotFail() {
        validateReturnObject(arrayList.add(0));
        validateReturnObject(arrayList.add(1));
        validateReturnObject(arrayList.add(2));
        assertEquals(arrayList.capacity(), arrayList.INITIAL_CAPACITY);
        assertFalse(arrayList.isEmpty());
        assertEquals(arrayList.size(), 3);
        
        validateReturnObject(arrayList.remove(0));
        assertFalse(arrayList.isEmpty());
        assertEquals(arrayList.size(), 2);
        
        validateReturnObject(arrayList.get(0), 1);
        validateReturnObject(arrayList.remove(1));
        assertFalse(arrayList.isEmpty());
        assertEquals(arrayList.size(), 1);
        
        validateReturnObject(arrayList.get(0), 1);
        validateReturnObject(arrayList.remove(0));
        assertEquals(arrayList.capacity(), arrayList.INITIAL_CAPACITY);
        assertTrue(arrayList.isEmpty());
        assertEquals(arrayList.size(), 0);
    }
    
    @Test
    public void removingLargeNumberOfObjectShouldReleaseMemory() {
        int count = 1024 + 1;
        for (int i = 0; i < count; i++)
            validateReturnObject(arrayList.add(i));
        
        assertTrue(arrayList.capacity() > 1024);
        assertFalse(arrayList.isEmpty());
        assertEquals(arrayList.size(), count);
        
        for (int i = 0; i < count; i++)
            validateReturnObject(arrayList.remove(0));
        
        assertEquals(arrayList.capacity(), 0);
        assertTrue(arrayList.isEmpty());
        assertEquals(arrayList.size(), 0);
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
