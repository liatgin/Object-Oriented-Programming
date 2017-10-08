import java.util.ArrayList;
import java.util.List;

/**
 * @author Liat Ginosar
 *
 */
public class OpenHashSet extends SimpleHashSet implements SimpleSet  {
	private List<HashString> stringList = new ArrayList<HashString>(super.capacity);

	/**
	 * A default constructor. Constructs a new, empty table with default initial capacity (16),
	 * upper load factor (0.75)
	 * and lower load factor (0.25).
	 */
	public OpenHashSet()
	{
		this((float)0.75, (float)0.25);
	}
	/**
	 * Constructs a new, empty table with the specified load factors, and the default 
	 * initial capacity (16).
	 * @param upperLoadFactor The upper load factor of the hash table.
	 * @param lowerLoadFactor  The lower load factor of the hash table.
	 */
	public OpenHashSet(float upperLoadFactor, float lowerLoadFactor) {
		super.upperLoadFactor = upperLoadFactor;
		super.lowerLoadFactor = lowerLoadFactor;
	}
	/**
	 * Data constructor - builds the hash set by adding the elements one by one.
	 * Duplicate values should be ignored. The new table has the default values of
	 * initial capacity (16), upper load factor (0.75), and lower load factor (0.25).
	 * @param data Values to add to the set.
	 */
	public OpenHashSet(String[] data) {
		this();

		int targetPlace = 0;
		HashString hashedString = null;
		for (int i = 0; i <= data.length; i++) {
			hashedString = new HashString(data[i]);
			targetPlace = computeHash(hashedString.getString());
			if(!stringList.contains(hashedString.getString())) {//avoiding duplicating values
				stringList.add(targetPlace, hashedString);
			}
		}
	}
	/**
	 * 
	 * @param value
	 * @return
	 */
	private int computeHash(String value) {
		int i = 0;
		int place;
		do { 
			place = Math.abs(value.hashCode() + (i + (i*i)) / 2);
			i++;
		}
		while (isOcupied(place));
		return place;
	}

	/**
	 * 
	 * @param indexOfElement
	 * @return
	 */
	private boolean isOcupied(int indexOfElement) {
		 if (stringList.get(indexOfElement).getState() == HashString.State.OCCUPIED)
			 return true;
		 return false;
	}
	/**
	 * 
	 * @return
	 */
	private boolean checkIsFull () {
		int relevantCapacity = capacity();
		if(stringList.size() / relevantCapacity > this.upperLoadFactor)
			return true;
		return false;
	}

	/**
	 * 
	 * @return
	 */
	private boolean checkIsEmpty () {
		int relevantCapacity = capacity();
		if(stringList.size() / relevantCapacity < this.lowerLoadFactor)
			return true;
		return false;

	}
	/**
	 * Add a specified element to the set.
	 * @newValue - New value to add to the set.
	 * @return False iff newValue already exists in the set.
	 */
	public boolean add(String newValue) {
		if (this.contains(newValue))
			return false;
		int place = computeHash(newValue);
		HashString hashString = new HashString(newValue);
		stringList.add(place, hashString);
		hashString.setState(HashString.State.OCCUPIED);//updates the state of the index in the list
		if (checkIsFull()){
			reHash();
		}
		return true;
	}

	private void reHash () {
		int oldSize = stringList.size();

		List<HashString> oldStringList = stringList;
		if(checkIsFull()){
			stringList = new ArrayList<HashString>(oldSize * 2);
		}
		else
			if(checkIsEmpty()) {
				stringList = new ArrayList<HashString>(oldSize / 2);
			}

		for (int i = 0; i <= oldSize; i++) {
			int newPlace;
			if( isOcupied(i) ) {
				newPlace = computeHash(oldStringList.get(i).getString());
				stringList.add(newPlace, oldStringList.get(i));
			}
		}
	}
	/**
	 * @return The current capacity (number of cells) of the table. 
	 */
	public int capacity() {
		return stringList.size();
	}

	/**
	 * Look for a specified value in the set.
	 * @searchVal - Value to search for.
	 * @return True iff searchVal is found in the set
	 */
	public boolean contains(String searchVal) {
		int placeOfValue = computeHash(searchVal);
		if (stringList.get(placeOfValue).getString() == "searchVal")
			return true;
		return false;
	}
	/**
	 *Remove the input element from the set.
	 * @toDelete - Value to delete
	 * @return True iff toDelete is found and deleted.
	 */
	public boolean delete(String toDelete) {
		if(stringList.contains(toDelete) == false)
			return false;
		int placeOfValue = computeHash(toDelete);
		stringList.get(placeOfValue).setState(HashString.State.DELTED);
		stringList.get(placeOfValue).setString(null);
		if(checkIsEmpty())
			reHash();
		return true;
	}
	/**
	 *@return The number of elements currently in the set.  
	 */
	public int size() {
		int counterElements = 0;
		for(int i = 0; i <= stringList.size(); i++) {
			if (isOcupied(i))
				counterElements++;
		}
		return counterElements;

	}
}
