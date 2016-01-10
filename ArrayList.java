/**
 * Implementation of an array.
 * 
 * @author Jose Massada
 */
public class ArrayList implements List {
    /**
     * The list initial capacity, after adding one item.
     */
    public static final int INITIAL_CAPACITY = 8;

    /**
     * The items storage.
     */
    private Object[] items;

    /**
     * The number of items the list can currently store.
     */
    private int capacity;

    /**
     * The number of items currently stored.
     */
    private int size;
    
    /**
     * The default constructor.
     */
    public ArrayList() {
        // use default initialisation values
    }
    
    /**
     * The number of items the list can currently store.
     *
     * @return The number of items.
     */
    public int capacity() {
        return this.capacity;
    }
    
	/**
	 * Check if the list is empty.
	 * 
	 * @return True if the list is empty, false otherwise.
	 */
	public boolean isEmpty() {
        return (this.size == 0);
    }

	/**
	 * The number of items currently in the list.
	 * 
	 * @return The number of items.
	 */
	public int size() {
        return this.size;
    }
    
	/**
	 * Gets the element at the given position.
	 * 
	 * If the index is negative, greater than or equal than the size of the
     * list, then an index out of bounds error message is returned.
	 * 
	 * @param index The index of the item to be retrieved.
	 * @return The element or an appropriate error message, encapsulated in a
     *         ReturnObject.
	 */
	public ReturnObject get(int index) {
        // check if index is out of bounds
        if (index < 0 || index >= this.size)
            return new ReturnObjectImpl(ErrorMessage.INDEX_OUT_OF_BOUNDS);
        
        // return the item
        return new ReturnObjectImpl(this.items[index]);
    }

	/**
	 * Removes the element at the given position from the list and returns it.
     * The indices of elements after the removed element are updated
     * accordingly.
	 * 
	 * If the index is negative, greater then or equal than the size of the
     * list, then an index out of bounds error message is returned.
	 * 
	 * @param index The index of the item to be retrieved
	 * @return The element or an appropriate error message, encapsulated in a
     *         ReturnObject.
	 */
	public ReturnObject remove(int index) {
        // check if index is out of bounds
        if (index < 0 || index >= this.size)
            return new ReturnObjectImpl(ErrorMessage.INDEX_OUT_OF_BOUNDS);
        
        // save item for return
        Object item = this.items[index];
        
        // move items starting at the index by 1 position (if not last item)
        if (this.size > 1) {
            System.arraycopy(this.items, index + 1, this.items, index,
                this.size - 1);
        }
        
        // update the number of items in array
        this.size--;
        
        // release memory if list is empty and capacity is large
        if (this.size == 0 && this.capacity >= 1024) {
            this.items = null;
            this.capacity = 0;
        }
        
        // return the removed item
        return new ReturnObjectImpl(item);
    }
    
	/**
	 * Adds an element to the list, inserting it at the given position. The
     * indices of elements at and after that position are updated accordingly.
	 * 
	 * If the index is negative, greater than or equal than the size of the
     * list, then an index out of bounds error message is returned.
	 * 
	 * If a null object is provided, the request is ignored and an invalid
     * argument error message is returned.
	 * 
	 * @param index The index at which the item should be inserted.
	 * @param item The element to be inserted.
	 * @return An empty ReturnObject if the operation is successful, an
     *         appropriate error message otherwise.
	 */
	public ReturnObject add(int index, Object item) {
        // check if index is out of bounds
        if (index < 0 || index >= this.size)
            return new ReturnObjectImpl(ErrorMessage.INDEX_OUT_OF_BOUNDS);
        
        // check if object is null
        if (item == null)
            return new ReturnObjectImpl(ErrorMessage.INVALID_ARGUMENT);
        
        // grow the internal storage if needed
        grow();
        
        // move items starting at the index by 1 position
        System.arraycopy(this.items, index, this.items, index + 1, this.size);
        
        // copy the item and update the number of items in array
        items[index] = item;
        this.size++;
        
        return new ReturnObjectImpl(ErrorMessage.NO_ERROR);
    }
    
	/**
	 * Adds an element to the end of the list.
	 * 
	 * If a null object is provided, the request is ignored and an invalid
     * argument error message is returned.
	 * 
	 * @param item The element to be inserted.
	 * @return An empty ReturnObject if the operation is successful, an
     *         appropriate error message otherwise.
	 */
	public ReturnObject add(Object item) {
        // check if object is null
        if (item == null)
            return new ReturnObjectImpl(ErrorMessage.INVALID_ARGUMENT);
        
        // grow the internal storage if needed
        grow();
        
        // copy the item and update the number of items in array
        this.items[this.size] = item;
        this.size++;
        
        return new ReturnObjectImpl(ErrorMessage.NO_ERROR);
    }
    
    /**
     * Grows (if needed) the internal object storage by a factor of 2 and copies
     * over the data (if any).
     */
    private void grow() {
        // check if the internal storage needs to grow
        if (this.size == this.capacity) {
            // calculate new capacity
            if (this.capacity == 0)
                this.capacity = INITIAL_CAPACITY;
            else
                this.capacity *= 2;
            
            // create a new array with the new capacity
            Object[] items = new Object[this.capacity];
            
            // copy the data
            if (this.items != null && this.size > 0)
                System.arraycopy(this.items, 0, items, 0, this.size);
            
            // update the array object to point to the new array
            this.items = items;
        }
    }
}
