import static org.junit.Assert.*;
import org.junit.*;

/**
 * Implementation of the StackImpl test class.
 */
public class StackImplTest {
    private StackImpl stack;
    
    @Test
    public void constructsFromEmptyListAndReturnsCorrectErrors() {
        stack = new StackImpl(new ArrayList());
        assertTrue(stack.isEmpty());
        assertEquals(stack.size(), 0);
        
        ErrorMessage errorMessage = ErrorMessage.EMPTY_STRUCTURE;
        validateReturnObject(stack.top(), errorMessage);
        validateReturnObject(stack.pop(), errorMessage);
        
        for (int i = 0; i < 10; i++)
            stack.push(i);
        
        validateReturnObject(stack.top(), 9);
        
        int i = 9;
        while (!stack.isEmpty()) {
            validateReturnObject(stack.top(), i);
            validateReturnObject(stack.pop(), i);
            i--;
        }
        
        validateReturnObject(stack.top(), errorMessage);
        validateReturnObject(stack.pop(), errorMessage);
        
        assertTrue(stack.isEmpty());
        assertEquals(stack.size(), 0);
    }
    
    @Test
    public void constructsFromNonEmptyListAndReturnsCorrectErrors() {
        ArrayList list = new ArrayList();
        
        for (int i = 0; i < 10; i++)
            list.add(i);
        
        assertEquals(list.capacity(), list.INITIAL_CAPACITY * 2);
        assertFalse(list.isEmpty());
        assertEquals(list.size(), 10);
        
        stack = new StackImpl(list);
        assertFalse(stack.isEmpty());
        assertEquals(stack.size(), 10);
        
        for (int i = 10; i < 128; i++)
            stack.push(i);
        
        validateReturnObject(stack.top(), 127);
        
        int i = 127;
        while (!stack.isEmpty()) {
            validateReturnObject(stack.top(), i);
            validateReturnObject(stack.pop(), i);
            i--;
        }
        
        ErrorMessage errorMessage = ErrorMessage.EMPTY_STRUCTURE;
        validateReturnObject(stack.top(), errorMessage);
        validateReturnObject(stack.pop(), errorMessage);
        
        assertTrue(stack.isEmpty());
        assertEquals(stack.size(), 0);
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
