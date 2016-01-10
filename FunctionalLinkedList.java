/**
 * Implementation of a functional list that extends a linked list.
 *
 * @author Jose Massada
 */
public class FunctionalLinkedList extends LinkedList implements FunctionalList {
    /**
     * Gets the element at the beginning of the list.
     * 
     * If the list is empty, an appropriate error is returned. 
     *
     * @return A copy of the element at the beginning of the list or
     *         an error if the list is empty.
     */
    public ReturnObject head() {
        if (this.size == 0)
            return new ReturnObjectImpl(ErrorMessage.EMPTY_STRUCTURE);
        
        return new ReturnObjectImpl(this.head);
    }

    /**
     * Gets a list with the elements in this list except the
     * head. The elements are in the same order. The original list
     * does not change or is affected by changes in the new list.
     * 
     * If the list is empty, another empty list is returned.
     *
     * @return The new list.
     */
    public FunctionalList rest() {
        FunctionalLinkedList newList = new FunctionalLinkedList();
        
        // do a deep copy O(n)
        if (this.size > 1) {
            // first node
            Node node = this.head.next;
            Node newNode = new Node(node.item, null, null);
            
            // set head and tail
            newList.head = newList.tail = newNode;
            
            // update size
            newList.size = 1;
            
            // append other nodes
            node = node.next;
            while (node != null) {
                newNode = new Node(node.item, newList.tail, null);
                newList.tail.next = newNode;
                newList.tail = newNode;
                newList.size++;
                
                node = node.next;
            }
        }
        return newList;
    }
}
