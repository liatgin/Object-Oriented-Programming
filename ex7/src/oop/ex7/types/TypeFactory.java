package oop.ex7.types;

import oop.ex7.main.IllegalCodeException;

/**
 * class Type factory.
 */
public class TypeFactory {
	
	/**
	 * Creates a new type variables.
	 * @param type the type of the var.
	 * @param value the value of the var.
	 * @return a new Variable with the relevant type.
	 * @throws IllegalCodeException
	 */
	public static Type createPrimitive(String type, String value) throws IllegalCodeException {
		switch (type) {
		case "int":
			return new IntegerVariable(value);		
		case "double":
			return new DoubleVariable(value);
		case "char":
			return new CharacterVariable(value);
		case "boolean":
			return new BooleanVariable(value);
		case "String":
			return new StringVariable(value);
		}
		
		throw new IllegalCodeException("Invalid initialization value");
	}
}

