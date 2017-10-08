/**
 * SimpleHashSet
 */
public abstract  class SimpleHashSet implements SimpleSet {
	protected float upperLoadFactor = 0;
	protected float lowerLoadFactor = 0;
	protected int capacity;
	/**
	 * Constructs capacity of size 16.
	 */
	public SimpleHashSet() {
		this.capacity = 16;
	}
	/**
	 * @param upperLoadFactor the upperLoadFactor. 
	 * @param lowerLoadFactor the lowerLoadFactor.
	 */
	public SimpleHashSet(float upperLoadFactor, float lowerLoadFactor) {
		this.upperLoadFactor = upperLoadFactor;
		this.lowerLoadFactor = lowerLoadFactor;
	}

	/**
	 * @return The current capacity (number of cells) of the table.
	 */
	public abstract int capacity();
}
