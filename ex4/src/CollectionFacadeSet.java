import java.util.Collection;

/**
 * @author Liat Ginosar
 *
 */
public class CollectionFacadeSet implements SimpleSet  {
	
	private Collection <String> certainCollection;
	/**
	 * Creates a new facade wrapping the specified collection.
	 * @param collection The Collection to wrap.
	 */
	public CollectionFacadeSet(java.util.Collection<java.lang.String> collection) {
		this.certainCollection = collection;
		
	}
		
	/**
	 * Add a specified element to the set.
	 * @newValue - New value to add to the set.
	 * @return False iff newValue already exists in the set. 
	 */
	public boolean add(String newValue) {
		if (this.certainCollection.contains(newValue)){
			this.certainCollection.add(newValue);
			return true;
		}
		return false;	
	}
	/**
	 * Look for a specified value in the set.
	 * @searchVal - Value to search for.
	 * @return True iff searchVal is found in the set.
	 */
	public boolean contains(String searchVal) {
		if(this.certainCollection.contains(searchVal)) 
			return true;	
		return false;
	}
	 /**
	  * Remove the input element from the set.
	  * @toDelete - Value to delete.
	  * @return True iff toDelete is found and deleted.
	  */
	public boolean delete(String toDelete) {
		if (this.certainCollection.contains(toDelete)) {
			this.certainCollection.remove(toDelete);
			return true;
		}
		return false;
	}
	
	/**
	 * size in interface SimpleSet/
	 * @return The number of elements currently in the set.
	 */
	public int size(){
		return this.certainCollection.size();
	}
}
