package oop.ex7.main;

public class IllegalCodeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * constructor
	 */
	public IllegalCodeException (String errorMassage) {
		
		System.out.println(1);
		System.out.println(errorMassage);
	}
}