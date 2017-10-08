package oop.ex7.types;

import oop.ex7.main.IllegalCodeException;
import oop.ex7.regex.RegexBox;

/**
 * class Double variable.
 * represents a variable of the type Double.
 */
public class DoubleVariable extends Type {
	
	/**
	 * constructor.
	 * @param value a double value.
	 * @throws IllegalCodeException
	 */
	public DoubleVariable(String value) throws IllegalCodeException {
		
		super(value);
		isValidValue(value);
	}
	
	/**
	 * constructor.
	 * @param values an array of Double values.
	 * @throws IllegalCodeException
	 */
	public DoubleVariable(String[] values) throws IllegalCodeException {
		super(values);
	
	}
	
	/**
	 * checks whether the value is valid.
	 */
	public void isValidValue(String value) throws IllegalCodeException {
		regex=RegexBox.DOUBLE_VALUE;
		
		 super.isValidValue(value);	 
	}
}