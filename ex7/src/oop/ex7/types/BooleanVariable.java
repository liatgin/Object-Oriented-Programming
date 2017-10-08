package oop.ex7.types;

import oop.ex7.main.IllegalCodeException;
import oop.ex7.regex.RegexBox;

/**
 * class Boolean variable.
 * represents a variable of the type Boolean.
 */
public class BooleanVariable extends Type {

	/**
	 * constructor.
	 * @param value a boolean value.
	 * @throws IllegalCodeException
	 */
	public BooleanVariable(String value) throws IllegalCodeException {
		super(value);
	}
	
	/**
	 * constructor.
	 * @param value an array of a boolean values.
	 * @throws IllegalCodeException
	 */
	public BooleanVariable(String[] value) throws IllegalCodeException {
		super(value);
	}
	
	/**
	 * checks whether the value is valid.
	 */
	public void isValidValue(String value) throws IllegalCodeException {
		regex = RegexBox.CONDITION_REGEX;
		 super.isValidValue(value);	 
		 
	}
}