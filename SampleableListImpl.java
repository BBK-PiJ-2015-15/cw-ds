/**
 * An implementation of a sampleable list.
 * 
 * @author Jose Massada
 */
public class SampleableListImpl extends ArrayList implements SampleableList {
	/**
	 * Returns a list containing all odd indices, including 0 or an empty list
     * if the list is empty.
	 * 
     * Complexity: O(n).
     *
	 * @return The list.
	 */
	public SampleableList sample() {
        SampleableListImpl list = new SampleableListImpl();
        
        if (this.size > 0) {
            // allocate space
            int count = (this.size + 1) / 2;
            list.ensureCapacity(count);
        
            // copy items
            for (int i = 0; i < this.size; i++) {
                if ((i % 2) == 0)
                    list.items[i / 2] = this.items[i];
            }
            
            // update size
            list.size = count;
        }
        return list;
    }
}
