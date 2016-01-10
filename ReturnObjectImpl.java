/**
 * Implementation of a wrapper (ReturnObject) containing either an object (the
 * result of an operation on a data structure) or an error value.
 *
 * @author Jose Massada
 */
public class ReturnObjectImpl implements ReturnObject {
    
    private Object object;
    private ErrorMessage message;

    public ReturnObjectImpl(Object object) {
        this.object = object;
        this.message = ErrorMessage.NO_ERROR;
    }
    
    public ReturnObjectImpl(ErrorMessage message) {
        this.message = message;
    }

    /**
	 * Returns whether there has been an error.
     *
	 * @return True if there's an error, false otherwise.
	 */
    public boolean hasError() {
        return (this.message != ErrorMessage.NO_ERROR);
    }

    /**
	 * Returns the error message. 
	 * 
	 * This method must return NO_ERROR if and only if
	 * {@hasError} returns false.
	 * 
	 * @return The error message.
	 */
	public ErrorMessage getError() {
        return this.message;
    }

    /**
	 * Returns the object wrapped in this ReturnObject, i.e. the
	 * result of the operation if it was successful, or null if
	 * there has been an error.
	 * 
	 * Note that the output of this method must be null if {@see
	 * hasError} returns true, but the opposite is not true: if
	 * {@see hasError} returns false, this method may or may not
	 * return null.
	 * 
	 * @return The return value from the method or null if there has been an
     *         error.
	 */
	public Object getReturnValue() {
        return this.object;
    }
}
