package oop.ex7.blocks;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import oop.ex7.main.IllegalCodeException;
import oop.ex7.regex.RegexBox;


/**
 * class If.
 */
public class If extends Conditions{
	
	/**
	 * constructor.
	 * @param nameOfBlock the name of the block
	 * @param containerBlock the father block
	 * @param content the content of the block.
	 * @throws IllegalCodeException
	 */
	public If(String nameOfBlock, Block containerBlock, ArrayList<String> content) throws IllegalCodeException {
		
		super(nameOfBlock, containerBlock, content);
		
		Pattern paternIf = Pattern.compile(RegexBox.IF_REGEX);
		Matcher matchIf = paternIf.matcher(nameOfBlock);
		
		if (!matchIf.matches()) {
			throw new IllegalCodeException("Invalid if block");
		}
		
		this.condition = matchIf.group(1);
		super.isValidCondition();
		
	}
}