import static org.junit.Assert.*;
import org.junit.*;

/**
 * Implementation of the FunctionalArrayList test class.
 */
public class FunctionalArrayListTest {
    private FunctionalArrayList list;

    @Before
    public void createFunctionalArrayList() {
        list = new FunctionalArrayList();
    }

    @Test
    public void retrievingHeadOnAnEmptyListShouldReturnError() {
        validateReturnObject(list.head(), ErrorMessage.EMPTY_STRUCTURE);
    }

    @Test
    public void retrievingHeadShouldBeSameObject() {
        FunctionalArrayList list = new FunctionalArrayList();
        
        String string = "some object";
        
        validateReturnObject(list.add(string));
        validateReturnObject(list.add(1));
        validateReturnObject(list.add(2));
        validateReturnObject(list.add(3));
        
        validateReturnObject(list.head(), string);
    }
    
    @Test
    public void extractingAllElementsButHeadShouldNotFail() {
        validateReturnObject(list.add(5));
        
        FunctionalList newList = list.rest();
        assertTrue(newList.isEmpty());
        assertEquals(newList.size(), 0);
        
        validateReturnObject(list.add(6));
        validateReturnObject(list.add(7));
        
        newList = list.rest();
        assertFalse(newList.isEmpty());
        assertEquals(newList.size(), 2);
        
        validateReturnObject(newList.get(0), 6);
        validateReturnObject(newList.get(1), 7);
    }
    
    @Test
    public void extractingAllElementsButHeadOnAnEmptyListShouldNotFail() {
        FunctionalList newList = list.rest();
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
