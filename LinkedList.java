/**
 * Implementation of a linked list.
 * 
 * @author Jose Massada
 */
public class LinkedList implements List {
    /**
     * A structure used to hold the object and it siblings.
     */
    protected class Node {
        public Object item;
        public Node prev;
        public Node next;
        
        public Node(Object item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    };
    
    /**
     * The items head.
     */
    protected Node head;
    
    /**
     * The items tail.
     */
    protected Node tail;

    /**
     * The number of items currently stored.
     *
     * We could iterate over the list to get the size but for performance we
     * save the current size in this member.
     */
    protected int size;
    
    /**
     * The default constructor.
     */
    public LinkedList() {
        // use default initialisation values
    }
    
	/**
	 * Check if the list is empty.
     *
     * Complexity: O(1).
	 * 
	 * @return True if the list is empty, false otherwise.
	 */
	public boolean isEmpty() {
        return (this.size == 0);
    }

	/**
	 * The number of items currently in the list.
	 * 
     * Complexity: O(1).
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
     * Complexity: O(1) if first or last element, otherwise O(n).
	 * 
	 * @param index The index of the item to be retrieved.
	 * @return The element or an appropriate error message, encapsulated in a
     *         ReturnObject.
	 */
	public ReturnObject get(int index) {
        // check if index is out of bounds
        if (index < 0 || index >= this.size)
            return new ReturnObjectImpl(ErrorMessage.INDEX_OUT_OF_BOUNDS);
        
        // find the node
        Node node = find(index);
        
        // return the item
        return new ReturnObjectImpl(node.item);
    }
    
	/**
	 * Removes the element at the given position from the list and returns it.
     * The indices of elements after the removed element are updated
     * accordingly.
	 * 
	 * If the index is negative, greater then or equal than the size of the
     * list, then an index out of bounds error message is returned.
	 * 
     * Complexity: O(1) if first or last element, otherwise O(n).
     *
	 * @param index The index of the item to be retrieved
	 * @return The element or an appropriate error message, encapsulated in a
     *         ReturnObject.
	 */
	public ReturnObject remove(int index) {
        // check if index is out of bounds
        if (index < 0 || index >= this.size)
            return new ReturnObjectImpl(ErrorMessage.INDEX_OUT_OF_BOUNDS);
        
        // find the node
        Node node = find(index);
        
        // update the pointers
        if (node.prev == null)
            this.head = node.next;
        else
            node.prev.next = node.next;
        
        if (node.next == null)
            this.tail = node.prev;
        else
            node.next.prev = node.prev;
        
        // update the number of items stored
        this.size--;
        
        // return the removed item
        return new ReturnObjectImpl(node.item);
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
     * Complexity: O(1) if adding to head or tail, otherwise O(n).
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
        
        // find the node
        Node node = find(index);
        
        // create the new node
        Node newNode = new Node(item, node.prev, node);
        
        // update the pointers
        if (node.prev == null)
            this.head = newNode;
        else
            node.prev.next = newNode;
        
        node.prev = newNode;
        
        // update the number of items stored
        this.size++;
        
        return new ReturnObjectImpl(ErrorMessage.NO_ERROR);
    }
    
	/**
	 * Adds an element to the end of the list.
     *
     * Complexity: O(1).
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
        
        // create the node
        Node newNode = new Node(item, this.tail, null);
        
        // add node to tail
        if (this.tail == null) {
            this.head = newNode;
            this.tail = newNode;
        } else {
            this.tail.next = newNode;
            this.tail = newNode;
        }
        
        // update the number of items stored
        this.size++;
        
        return new ReturnObjectImpl(ErrorMessage.NO_ERROR);
    }
    
    /**
     * Gets the node at the given position.
     *
     * Complexity: O(n).
     *
     * @param index The element index.
     * @param return The node at the given position.
     */
    protected Node find(int index) {
        Node node;
        
        if (index == 0)
            node = this.head;
        else if (index == (this.size - 1))
            node = this.tail;
        else {
            node = this.head;
            for (int i = 0; i < index; i++)
                node = node.next;
        }
        return node;
    }
}
