import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
/**
 * @author Liat Ginosar
 *
 */
public class ChainedHashSet extends SimpleHashSet implements SimpleSet  {

	private List<LinkedList<String>> stringLists;
	private float upperLoadFactor = 0;
	private float lowerLoadFactor = 0;

	/**
	 * Constructs a new, empty table with the specified load factors, and the default initial capacity (16). 
	 */
	public ChainedHashSet() {
		super.capacity = 16;
	}

	/**
	 * Constructs a new, empty table with the specified load factors, and the default initial capacity (16).
	 * @param upperLoadFactor The upper load factor of the hash table.
	 * @param lowerLoadFactor The lower load factor of the hash table.
	 */
	public ChainedHashSet(float upperLoadFactor, float lowerLoadFactor) {
		this.upperLoadFactor = upperLoadFactor;
		this.lowerLoadFactor = lowerLoadFactor;
	}
	/**
	 * Data constructor - builds the hash set by adding the elements one by one.
	 * Duplicate values should be ignored. The new table has the default values of
	 * initial capacity (16), upper load factor (0.75), and lower load factor (0.25).
	 * @param data Values to add to the set.
	 */
	public ChainedHashSet(String[] data) {

		/**
		 * creating our structure and initiating it's values with null
		 */
		stringLists = new ArrayList<LinkedList<String>>(super.capacity);
		for (int i = 0; i < super.capacity; i++) {
			stringLists.add(null);
		}


		this.upperLoadFactor = (float) 0.75;
		this.lowerLoadFactor =  (float) 0.25;
		for (int i = 0; i < data.length; i++) { // put the new strings one by one in the table
			int place = computeHash(data[i]);

			if (stringLists.get(place)==null){
				stringLists.set(place, new LinkedList<String>());
			}

			LinkedList<String> targetLinkedList = stringLists.get(place);			
			if (!targetLinkedList.contains(data[i]))
				targetLinkedList.add(data[i]);
		}
	}
	/**
	 * Add a specified element to the set.
	 * @newValue - New value to add to the set.
	 * @return False iff newValue already exists in the set.
	 */
	public boolean add(String newValue) {
		if (this.contains(newValue)) {
			return false;
		}

		int place = computeHash(newValue);
		stringLists.get(place).add(newValue);

		if(checkIsFull() == true)//check upper load factor.
			rehashElements();
		return true;
	}
	/**
	 * 
	 * @param value
	 * @return
	 */
	private int computeHash(String value) {
		return Math.abs(value.hashCode() % super.capacity);
	}
	/**
	 * 
	 * @return
	 */
	private boolean checkIsFull() {
		int relevantCapacity = capacity();
		if(stringLists.size() / relevantCapacity > this.upperLoadFactor)
			return true;
		return false;
	}
	private boolean checkIsEmpty() {
		int relevantCapacity = capacity();
		if(stringLists.size() / relevantCapacity < this.lowerLoadFactor)
			return true;
		return false;
	}
	/**
	 * 
	 */
	private void rehashElements () {
		int oldSize = stringLists.size();

		List<LinkedList<String>> oldStringLists = stringLists;
		if(checkIsFull()){
			stringLists = new ArrayList<LinkedList<String>>(oldSize * 2);
		}
		else
			if(checkIsEmpty()) {
				stringLists = new ArrayList<LinkedList<String>>(oldSize / 2);
			}

		for (int i = 0; i <= oldSize; i++) {
			String firstValue = oldStringLists.get(i).get(0);
			int newPlace = computeHash(firstValue);
			stringLists.add(newPlace, oldStringLists.get(i));
		}
	}


	/**
	 * capacity in class SimpleHashSet.
	 * @return The current capacity (number of cells) of the table.
	 */
	public int capacity() {
		return stringLists.size();
	}
	/**
	 * Look for a specified value in the set.
	 * @searchVal - Value to search for.
	 * @return True if searchVal is found in the set. 
	 */
	public boolean contains(String searchVal) {
		int placeToSearch = computeHash(searchVal);
		if(stringLists.get(placeToSearch)!=null){			
			if(stringLists.get(placeToSearch).contains(searchVal)){
				return true;
			}
		}
		return false;
	}
	/**
	 * Remove the input element from the set.
	 * @toDelete - Value to delete.
	 * @return True iff toDelete is found and deleted.
	 */
	public boolean delete(String toDelete) {
		for(int i = 0; i<= stringLists.size(); i++) {
			if(stringLists.get(i).contains((toDelete))) {// checks if toDelte exist
				stringLists.get(i).remove(toDelete);
				if(checkIsEmpty() == true)
					rehashElements();
				return true;
			}
		}
		return false;
	}
	/**
	 * @return The number of elements currently in the set.
	 */
	public int size() {
		int counterOfElements = 0;
		for(int i= 0; i <= stringLists.size(); i++) {
			counterOfElements += stringLists.get(i).size();
		}
		return counterOfElements;
	}
}
