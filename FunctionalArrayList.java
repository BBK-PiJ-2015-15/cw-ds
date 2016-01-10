/**
 * Implementation of a functional list that extends an array list.
 *
 * @author Jose Massada
 */
public class FunctionalArrayList extends ArrayList implements FunctionalList {
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
        
        return new ReturnObjectImpl(this.items[0]);
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
        FunctionalArrayList newList = new FunctionalArrayList();
        if (this.size > 1) {
            // allocate memory
            newList.ensureCapacity(this.size - 1);
            
            // copy data and update the number of items stored
            for (int i = 1; i < this.size; i++)
                newList.items[i - 1] = this.items[i];
            
            newList.size = this.size - 1;
        }
        return newList;
    }
}
