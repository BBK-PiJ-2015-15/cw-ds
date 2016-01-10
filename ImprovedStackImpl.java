/**
 * An implementation of a stack with additional methods. 
 * 
 * Classes implementing this interface must use a {@see List} as the
 * underlying data structure to store the elements on the stack. 
 * 
 * Not all operations on a stack will always be successful. For
 * example, a programmer may try to pop an element from an empty
 * stack. Since we hace not covered exceptions yet, we need another
 * mechanism to report errors. In order to do that, methods of this
 * list will return a {@see ReturnObject} that will contain either an
 * object or an error value of the right kind (as defined in {@see
 * ErrorMessage}).
 * 
 * @author Jose Massada
 */
public class ImprovedStackImpl implements ImprovedStack {
    /**
     * The underlying data structure.
     */
    private StackImpl stack;

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
    public ImprovedStackImpl(List list) {
        this.stack = new StackImpl(list);
    }
    
    /**
	 * Returns true if the stack is empty, false otherwise. 
	 * 
	 * @return true if the stack is empty, false otherwise. 
	 */
	public boolean isEmpty() {
        return stack.isEmpty();
    }

	/**
	 * Returns the number of items currently in the stack.
	 * 
	 * @return the number of items currently in the stack
	 */
	public int size() {
        return stack.size();
    }

	/**
	 * Adds an element at the top of the stack. 
	 * 
	 * @param item the new item to be added
	 */
	public void push(Object item) {
        stack.push(item);
    }

	/**
	 * Returns the element at the top of the stack. The stack is
	 * left unchanged.
	 * 
	 * @return If stack is not empty, the item on the top is returned. If the
	 *         stack is empty, an appropriate error.
	 */
	public ReturnObject top() {
        return stack.top();
    }

	/**
	 * Returns the element at the top of the stack. The element is
	 * removed frmo the stack.
	 * 
	 * @return If stack is not empty, the item on the top is returned. If the
	 *         stack is empty, an appropriate error.
	 */
	public ReturnObject pop() {
        return stack.pop();
    }
    
	/**
	 * Returns a copy of this stack with the items reversed, the top
	 * elements on the original stack is at the bottom of the new
	 * stack and viceversa.
	 * 
	 * @return a copy of this stack with the items reversed. 
	 */
	public ImprovedStack reverse() {
        List list = stack.list();
        LinkedList newList = new LinkedList();
    
        for (int i = list.size() - 1; i >= 0; i--) {
            ReturnObject returnObject = list.get(i);
            newList.add(returnObject.getReturnValue());
        }
        return new ImprovedStackImpl(newList);
    }
    
	/**
	 * Removes the given object from the stack if it is
	 * there. Multiple instances of the object are all removed.
	 *
	 * Classes implementing this method must use method .equals() to
	 * check whether the item is in the stack or not.
	 * 
	 * @param object the object to remove
	 */
	public void remove(Object object) {
        List list = stack.list();
        
        for (int i = 0; i < list.size(); i++) {
            ReturnObject returnObject = list.get(i);
            if (returnObject.getReturnValue() == object) {
                list.remove(i);
                i--;
            }
        }
    }
}
