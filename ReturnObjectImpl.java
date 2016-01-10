/**
 * Implementation of a wrapper structure containing either an object (the
 * result of an operation on a data structure) or an error value.
 *
 * @author Jose Massada
 */
public class ReturnObjectImpl implements ReturnObject {
    /**
     * The object. Can be null.
     */
    private Object object;
    /**
     * The error message. Can be null.
     */
    private ErrorMessage message;

    /**
     * Construct with an object.
     */
    public ReturnObjectImpl(Object object) {
        this.object = object;
        this.message = ErrorMessage.NO_ERROR;
    }
    
    /** 
     * Construct with an error message.
     */
    public ReturnObjectImpl(ErrorMessage message) {
        this.message = message;
    }

    /**
	 * Check if there's an error message.
     *
	 * @return True if there's an error, false otherwise.
	 */
    public boolean hasError() {
        return (this.message != ErrorMessage.NO_ERROR);
    }

    /**
	 * Gets the error message.
	 * 
	 * This method returns NO_ERROR if and only if {@hasError} returns false.
	 * 
	 * @return The error message.
	 */
	public ErrorMessage getError() {
        return this.message;
    }

    /**
	 * Gets the object wrapped in this ReturnObject, i.e. the
	 * result of the operation if it was successful, or null if
	 * there has been an error.
	 * 
	 * Note that the output of this method returns null if {@see
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
