import static org.junit.Assert.*;
import org.junit.*;

/**
 * Implementation of the ReturnObjectImpl test class.
 */
public class ReturnObjectImplTest {
    @Test
    public void constructingWithObjectShouldNotFail() {
        String string = "some object";
        ReturnObjectImpl returnObject = new ReturnObjectImpl(string);
        
        assertFalse(returnObject.hasError());
        assertEquals(returnObject.getError(), ErrorMessage.NO_ERROR);
        assertSame(returnObject.getReturnValue(), string);
    }
    
    @Test
    public void constructingWithNullObjectShouldNotFail() {
        Object object = null;
        ReturnObjectImpl returnObject = new ReturnObjectImpl(object);
        
        assertFalse(returnObject.hasError());
        assertEquals(returnObject.getError(), ErrorMessage.NO_ERROR);
        assertNull(returnObject.getReturnValue());
    }
    
    @Test
    public void constructingWithErrorMessageShouldNotFail() {
        ErrorMessage message = ErrorMessage.EMPTY_STRUCTURE;
        ReturnObjectImpl returnObject = new ReturnObjectImpl(message);
        
        assertTrue(returnObject.hasError());
        assertSame(returnObject.getError(), message);
        assertNull(returnObject.getReturnValue());
    }
    
    @Test
    public void constructingWithNullErrorMessageShouldNotFail() {
        ErrorMessage message = null;
        ReturnObjectImpl returnObject = new ReturnObjectImpl(message);
        
        assertTrue(returnObject.hasError());
        assertNull(returnObject.getError());
        assertNull(returnObject.getReturnValue());
    }
}
