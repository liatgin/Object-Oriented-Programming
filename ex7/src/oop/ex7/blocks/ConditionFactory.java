package oop.ex7.blocks;

import java.util.ArrayList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import oop.ex7.main.IllegalCodeException;
import oop.ex7.regex.RegexBox;


/**
 * class ConditionFactory
 */
public class ConditionFactory {

	/**
	 * Creates a new condition block.
	 * @param nameOfBlock the name of the block
	 * @param content the content of the block
	 * @param block the father block.
	 * @return a new condition block.
	 * @throws IllegalCodeException
	 */
	public static Conditions createCondition(String nameOfBlock,ArrayList<String> content,Block block ) throws IllegalCodeException {

		Pattern paternIf = Pattern.compile(RegexBox.IF_REGEX);
		Matcher matchIf = paternIf.matcher(nameOfBlock);
		Pattern paternWhile = Pattern.compile(RegexBox.WHILE_REGEX);

		Matcher matchWhile = paternWhile.matcher(nameOfBlock);

		if(matchIf.find()){
			return new If(nameOfBlock,block,content);
		}

		else if(matchWhile.find()){
			return new While(nameOfBlock,block,content);
		}
		else throw new IllegalCodeException("Block is not exist");

	}
}