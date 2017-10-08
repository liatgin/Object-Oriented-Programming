/**
 * HashString
 * helper class to OpenHashSet who keeps flag to each cell in the table that says the state of the cell:
 * new(empty cell), deleted(the value in the cell was deleted) or occupied (there is a value in the cell).
 */
public class HashString {
	public enum State {
	    NEW, DELTED, OCCUPIED 
	}
	private String string;
	private State state;
	
	/**
	 * constructor, build a new hashString with string value.
	 * @param str the value to add.
	 */
	public HashString (String str) {
		this.string = str;
		this.state = State.NEW;
	}
	/**
	 * @return the state value.
	 */
	public State getState() {
		return this.state;
	}
	/**
	 * @param newState the new state value.  
	 */
	public void setState(State newState) {
		this.state = newState;
	}
	
	/**
	 * @return the string value.
	 */
	public String getString() {
		return this.string;
	}
	/**
	 * @param str the new string value.
	 */
	public void setString(String str) {
		this.string = str; 
	}
}
