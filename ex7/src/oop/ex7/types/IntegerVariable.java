package oop.ex7.types;

import oop.ex7.main.IllegalCodeException;
import oop.ex7.regex.RegexBox;

/**
 * class Integer variable.
 * represents a variable of the type integer.
 */
public class IntegerVariable extends Type {

	/**
	 * constructor.
	 * @param value a given integer value.
	 * @throws IllegalCodeException
	 */
	public IntegerVariable(String value) throws IllegalCodeException {
		
		super(value);
	}
	
	/**
	 * constructor.
	 * @param values an array of an integer values.
	 * @throws IllegalCodeException
	 */
	public IntegerVariable(String[] values) throws IllegalCodeException {
		
		super(values);
	}

	
	/**
	 * checks whether the value is valid.
	 */
	public void isValidValue(String value) throws IllegalCodeException {
		
		regex = RegexBox.INTEGER_REGEX;
		
		 super.isValidValue(value);
	}
}
	
	