package oop.ex7.blocks;

import java.util.ArrayList;

import oop.ex7.main.IllegalCodeException;
import oop.ex7.main.Tools;

/**
 * class global block.
 */
public class GlobalBlock extends Block {

	boolean methodsOnly;
	
	/**
	 * constructor
	 * @param content the global block content.
	 */
	public GlobalBlock(ArrayList<String> content) {
		super("", null, content);
	}
	
	/**
	 * return true iff a given line is legal and false otherwise.
	 */
	protected boolean checkLineAllowed(String line) throws IllegalCodeException {
		switch (line) {
			case Tools.COMMENT:
			case Tools.EMPTY_LINE:
			case Tools.METHOD_REGEX:
				return true;
				
			case Tools.DECLARATION_ON_VAR:
			case Tools.DECLARATION_AND_ASSIGNINNG_IN_VAR:
				return !methodsOnly;
				
			default:
				throw new IllegalCodeException("No Such line");
		}
	}
	
	
	/**
	 * sends method to parse.
	 * @param methodsOnly a boolean var that says if a given line is a method or not.
	 * @throws IllegalCodeException
	 */
	public void parse(boolean methodsOnly) throws IllegalCodeException {
		this.methodsOnly = methodsOnly;
		parseMethod("", null, content, methodsOnly);
	}
}
