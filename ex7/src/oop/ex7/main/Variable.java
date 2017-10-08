package oop.ex7.main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import oop.ex7.blocks.Block;
import oop.ex7.regex.RegexBox;
import oop.ex7.types.TypeFactory;

/**
 * class Variable.
 * represents a variable.
 */
public class Variable {

	private String type;
	private String name;
	private boolean value;
	private boolean isPrimitive=true;
	private Block block;


	/**
	 * constructor.
	 * @param block the block of the variable.
	 * @param type the type of the variable.
	 * @param name the name of the variable.
	 * @param value is the value was initiallized or not.
	 * @throws IllegalCodeException
	 */
	public Variable(Block block, String type, String name, boolean value) throws  IllegalCodeException {
		this.block = block;
		this.value = value;
		this.type=type;
		this.name = name;
	
		fixArrayType();
	}
	
	/**
	 * constructor.
	 * @param block the block of the variable.
	 * @param type the type of the variable.
	 * @param name the name of the variable.
	 * @throws IllegalCodeException
	 */
	public Variable(Block block, String type,String name) throws IllegalCodeException {
		this(block, type, name, false);
	}
	
	/**
	 * checks whether the the value and the type are vaild.
	 * @param type a given type.
	 * @param value a given value.
	 * @throws IllegalCodeException
	 */
	public static void checkValidPrimitive(String type, String value) throws IllegalCodeException {		
		TypeFactory.createPrimitive(type, value);
	}
	
	/**
	 * @return the variable block.
	 */
	public Block getBlock() {
		return block;
	}
	
	/**
	 * sends the var type to normalization.
	 * @throws IllegalCodeException
	 */
	private void fixArrayType() throws IllegalCodeException {
		this.type = normalizeType(this.type);
		this.isPrimitive = !this.type.contains("[]");
	}
	
	/**
	 * 
	 * @param type var type.
	 * @return the normalized type according to regex valid_type.
	 * @throws IllegalCodeException
	 */
	public static String normalizeType(String type) throws IllegalCodeException {
		return normalizeType(type, RegexBox.VALID_TYPE);
	}
	
	/**
	 * 
	 * @param the var type.
	 * @return the normalized type according to regex return_type_of_method.
	 * @throws IllegalCodeException
	 */
	public static String normalizeReturnType(String type) throws IllegalCodeException {
		return normalizeType(type, RegexBox.RETURN_TYPE_OF_METHOD);
	}
	
	/**
	 * 
	 * @param type the var type.
	 * @param regex a given regex.
	 * @return the type + "[]" if the var is an array and the regular type if
	 * the var is not an array. throw exception if the type is invalid.
	 * @throws IllegalCodeException
	 */
	private static String normalizeType(String type, String regex) throws IllegalCodeException {
		Pattern paternType = Pattern.compile(regex);
		Matcher matchType = paternType.matcher(type);
		
		if(!matchType.matches()) {
			throw new IllegalCodeException("Invalid type");
		}
		
		type = matchType.group(1);
		
		if (type != null) {
			if (matchType.group(2).contains("[")) {
				type += "[]";
			}
		}
		else {
			type = matchType.group(4);
		}
		return type;
	}
	
	/**
	 * @return the var name.
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * @return the var type.
	 */
	public String getType() {
		return this.type;
	}
	
	/**
	 * @return true if the value was initialized and false otherwise. 
	 */
	public boolean hasValue() {
		return this.value;
	}
	
	/**
	 * sets true in the value which means the value was initialized.
	 */
	public void setValue() {
		this.value = true;
	}
	
	/**
	 * @return true if the variable is primitive and false otherwise( the var is an array).
	 */
	public boolean getIsPrimitive(){
		return isPrimitive;
	}
}