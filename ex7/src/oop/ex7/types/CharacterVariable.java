package oop.ex7.types;

import oop.ex7.main.IllegalCodeException;
import oop.ex7.regex.RegexBox;

/**
 * class Character variable.
 * represents a variable of the type Character.
 */
public class CharacterVariable extends Type {

	/**
	 * constructor.
	 * @param value a char value.
	 * @throws IllegalCodeException
	 */
	public CharacterVariable(String value) throws IllegalCodeException {
		super(value);
	}
	
	/**
	 * constructor.
	 * @param values an array of char values.
	 * @throws IllegalCodeException
	 */
	public CharacterVariable(String[] values) throws IllegalCodeException {
		super(values);
	}
	
	/**
	 * checks whether the value is valid.
	 */
	public void isValidValue(String value) throws IllegalCodeException {
		regex = RegexBox.CHAR_VALUE;
		 super.isValidValue(value);	 
	}
}
