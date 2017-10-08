package oop.ex7.types;

import oop.ex7.main.IllegalCodeException;
import oop.ex7.regex.RegexBox;

/**
 * class String value. 
 * represents a variable of the type string.
 */
public class StringVariable extends Type {

	/**
	 * constructor.
	 * @param value a certain string value.
	 * @throws IllegalCodeException
	 */
	public StringVariable(String value) throws IllegalCodeException {
		super(value);	
	}
	
	/**
	 * constructor
	 * @param value an array of string values. 
	 * @throws IllegalCodeException
	 */
	public StringVariable(String[] value) throws IllegalCodeException {
		super(value);
		
	}
	
	/**
	 * checks whether the value is valid.
	 */
	public void isValidValue(String value) throws IllegalCodeException {
		regex = RegexBox.STRING_VALUE;
		 super.isValidValue(value);	 
	}

}