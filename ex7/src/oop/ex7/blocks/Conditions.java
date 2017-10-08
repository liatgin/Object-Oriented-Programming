package oop.ex7.blocks;

import java.util.ArrayList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import oop.ex7.main.IllegalCodeException;
import oop.ex7.regex.RegexBox;

/**
 * class conditions
 */
public class Conditions extends InnerBlock {

	protected String condition;
	
	/**
	 * constructor
	 * @param nameOfBlock the name of the block.
	 * @param father the block father
	 * @param content the block content
	 * @throws IllegalCodeException
	 */
	public Conditions(String nameOfBlock, Block father, ArrayList<String> content) throws IllegalCodeException {
		super(nameOfBlock, father, content);
	}
	
	/**
	 * 
	 * checks whether the boolean condition is valid and throws an exception if not.
	 */
	public void isValidCondition() throws IllegalCodeException {

		Pattern paternCondition = Pattern.compile(RegexBox.CONDITION_REGEX);
		Matcher matchCondition = paternCondition.matcher(this.condition);
		if(matchCondition.matches()){

			validAssigningPossibilities("boolean", this.condition);
		}
	}
	
	/**
	 * @return the boolean condition.
	 */
	public String getCondition() {
		return this.condition;
	}
}