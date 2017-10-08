package oop.ex7.blocks;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import oop.ex7.main.IllegalCodeException;
import oop.ex7.regex.RegexBox;

/**
 * 
 * class While
 * represents a while block.
 */
public class While extends Conditions {
	
	/**
	 * constructor.
	 * @param nameOfBlock the name of the block.
	 * @param containerBlock the father of the block.
	 * @param content the content of the block.
	 * @throws IllegalCodeException
	 */
	public While(String nameOfBlock, Block containerBlock,ArrayList<String> content) throws IllegalCodeException {

		super(nameOfBlock, containerBlock, content);

		Pattern paternWhile = Pattern.compile(RegexBox.WHILE_REGEX);
		Matcher matchWhile = paternWhile.matcher(nameOfBlock);

		if (!matchWhile.matches()) {
			throw new IllegalCodeException("Invalid while block");
		}
		
		this.condition = matchWhile.group(1);
		super.isValidCondition();
	}
}

