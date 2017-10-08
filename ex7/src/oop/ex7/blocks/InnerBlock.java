package oop.ex7.blocks;

import java.util.ArrayList;

import oop.ex7.main.IllegalCodeException;
import oop.ex7.main.Tools;

/**
 * 
 * class InnerBlock.
 * Represents any block that is not the global block : method block or condition(if or while)
 * block.
 */
public class InnerBlock extends Block {

	/**
	 * constructor
	 * @param nameOfBlock the name of th block.
	 * @param father the father block.
	 * @param content the content of the block.
	 */
	public InnerBlock(String nameOfBlock, Block father,
			ArrayList<String> content) {
		super(nameOfBlock, father, content);
	}

	/**
	 * return true iff the given line is a legal line and false otherwise.
	 */
	protected boolean checkLineAllowed(String line) throws IllegalCodeException {
		switch (line) {
			case Tools.COMMENT:
			case Tools.EMPTY_LINE:
			case Tools.DECLARATION_ON_VAR:
			case Tools.ASSIGNING_IN_VAR:
			case Tools.DECLARATION_AND_ASSIGNINNG_IN_VAR:
			case Tools.IF_OR_WHILE:
			case Tools.RETURN_LINE:
			case Tools.METHOD_CALL:
				return true;
				
			default:
				throw new IllegalCodeException("No Such line");
		}
	}
}
