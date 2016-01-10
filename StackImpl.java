/**
 * An implementation of a stack that uses a {@List} as the underlying
 * data structure.
 * 
 * Not all operations on a stack will always be successful. For
 * example, a programmer may try to pop an element from an empty
 * stack. Since we have not covered exceptions yet, we need another
 * mechanism to report errors. In order to do that, methods of this
 * list will return a {@see ReturnObject} that will contain either an
 * object or an error value of the right kind (as defined in {@see
 * ErrorMessage}).
 * 
 * @author Jose Massada
 */
public class StackImpl extends AbstractStack {
    /**
     * Creates a new stack using the provided list as the underlying data
     * structure.
     * 
     * Note: This constructor does not check whether the provided list
     * is null. Programmers must do their own checks. If a null list
     * is provided, a NullPointerException will be thrown at runtime
     * as soon as any operation is attempted on the underlying list.
     * 
     * @param list The underlying list.
     */
    public StackImpl(List list) {
        super(list);
    }
    
    /**
     * Gets the underlying data structure.
     *
     * @return THe underlying data structure.
     */
    public List list() {
        return internalList;
    }
    
    /**
	 * Check if the list is empty.
	 * 
	 * @return True if the list is empty, false otherwise.
	 */
	public boolean isEmpty() {
        return internalList.isEmpty();
    }

	/**
	 * The number of items currently in the list.
     *
	 * @return The number of items.
	 */
	public int size() {
        return internalList.size();
    }

	/**
	 * Adds an element to the top of the stack.
	 * 
	 * @param item The new item to be added.
	 */
	public void push(Object item) {
        // add item to tail
        internalList.add(item);
    }

	/**
	 * Gets the element at the top of the stack. The stack is left unchanged.
	 * 
	 * @return If stack is not empty, the item on the top is returned, an
     *         appropriate error otherwise.
	 */
	public ReturnObject top() {
        // check if stack is empty
        if (internalList.isEmpty())
            return new ReturnObjectImpl(ErrorMessage.EMPTY_STRUCTURE);
    
        // return tail (top of stack)
        return internalList.get(internalList.size() - 1);
    }

	/**
	 * Gets the element at the top of the stack. The element is removed from the
     * stack.
	 * 
	 * @return If stack is not empty, the item on the top is returned, an
     *         appropriate error otherwise.
	 */
	public ReturnObject pop() {
        // check if stack is empty
        if (internalList.isEmpty())
            return new ReturnObjectImpl(ErrorMessage.EMPTY_STRUCTURE);
        
        // remove tail (top of stack) and return item
        return internalList.remove(internalList.size() - 1);
    }
}
