package oop.ex7.types;

import oop.ex7.main.IllegalCodeException;

public class WrongValueException extends IllegalCodeException {

	/**
	 * 
	 * @param errorMassage
	 */
	public WrongValueException(String errorMassage) {
		super(errorMassage);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}