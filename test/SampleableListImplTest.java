import static org.junit.Assert.*;
import org.junit.*;

/**
 * Implementation of the SampleableListImpl test class.
 */
public class SampleableListImplTest {
    private Object[] objects;
    private SampleableListImpl list;

    public SampleableListImplTest() {
        objects = new Object[] { 0, 1, 2, 3, 4, 5, 6 };
    }
    
    @Before
    public void createList() {
        list = new SampleableListImpl();
        
        assertEquals(list.capacity(), 0);
        assertTrue(list.isEmpty());
        assertEquals(list.size(), 0);
    }

    @Test
    public void constructsCorrectListWithObjects() {
        int count = objects.length;
        
        for (int i = 0; i < count; i++)
            validateReturnObject(list.add(objects[i]));
        
        assertEquals(list.capacity(), list.INITIAL_CAPACITY);
        assertFalse(list.isEmpty());
        assertEquals(list.size(), count);
        
        int expectedCount = (count + 1) / 2;
        
        SampleableList newList = list.sample();
        assertFalse(newList.isEmpty());
        assertEquals(newList.size(), expectedCount);
        
        for (int i = 0; i < expectedCount; i++)
            validateReturnObject(newList.get(i), objects[i * 2]);
    }
    
    @Test
    public void constructsEmptyListWithNoObjects() {
        SampleableList newList = list.sample();
        assertTrue(newList.isEmpty());
        assertEquals(newList.size(), 0);
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
