package oop.ex7.types;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import oop.ex7.blocks.Block;
import oop.ex7.main.IllegalCodeException;

/**
 * class Type.
 * represents a Variable type.
 */
public class Type {
	
	private String value;
	private String[]  values;
	public String regex=null;
	protected Block block;
	
	/**
	 * constructor.
	 * @param value a given value.
	 * @throws IllegalCodeException
	 */
	public  Type (String value) throws IllegalCodeException {
		this.value = value;
	
		isValidValue(value);
	}
	
	/**
	 * @return the value.
	 */
	public String getValue(){
		return value;
	}
	
	/**
	 * constructor.
	 * @param values an array of values.
	 * @throws IllegalCodeException
	 */
	public Type(String[] values) throws IllegalCodeException {
	
		this.values = values;
		
		if(!this.values[0].equals("")) {
			
			for(int i=0; i < this.values.length; i++){
		
				isValidValue(values[i]);
			}
		}
	}
	
	/**
	 * checks whether a given value is valid and throws an exception if not.
	 * @param value a given value.
	 * @throws IllegalCodeException
	 */
	public  void isValidValue(String value) throws IllegalCodeException {
		
		Pattern patern = Pattern.compile(regex);
		Matcher match = patern.matcher(value);
		
		if(!match.matches()){
		
		throw new WrongValueException("Wrong value");
		}
	}
}