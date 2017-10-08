package oop.ex7.main;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import oop.ex7.regex.RegexBox;


/** 
 * class Tools
 * Contains a useful methods which are used in many classes in the program.
 */
public class Tools {
	public static final String	CLOSE_BRACKET = "}";
	public static final String	OPEN_BRACKET = "{";
	public static final int NOT_FOUND = -1;
	public static final int VALID_BRACKETS = 0;
	public static final String COMMENT = "comment";
	public static final String EMPTY_LINE = "empty line"; 
	public static final String DECLARATION_ON_VAR = "declaration on var";
	public static final String ASSIGNING_IN_VAR = "assigning in var";
	public static final String DECLARATION_AND_ASSIGNINNG_IN_VAR = "assign and declaration on var";
	public static final String IF_OR_WHILE = "if or while line";
	public static final String RETURN_LINE = "return";
	public static final String METHOD_REGEX = "method";
	public static final String METHOD_CALL = "method call";

	
	/**
	 * checks which kind of line the given line is and throws an excption if
	 * the line is not a legal line.
	 * @param line a certain line.
	 * @return a string that represents which line is the given line is.
	 * @throws NoSuchLineException
	 */
	public static String whichKindOfLine(String line) throws IllegalCodeException {

		Pattern paternComment = Pattern.compile(RegexBox.COMMENT_LINE);
		Matcher matchComment = paternComment.matcher(line);
		
		if (matchComment.matches()) {
			return COMMENT;
		}

		Pattern paternEmpty = Pattern.compile(RegexBox.EMPTY_LINE);
		Matcher matchEmpty = paternEmpty.matcher(line);
	
		if (matchEmpty.matches()) {
			return EMPTY_LINE;
		}

		Pattern paternDeclareOnVar = Pattern.compile(RegexBox.DECLARATION_ON_VAR);
		Matcher matchDeclareOnVar = paternDeclareOnVar.matcher(line);
		
		if (matchDeclareOnVar.matches()) {
			
			return DECLARATION_ON_VAR;
		}
		Pattern paternDeclareAndAssignOnVar = Pattern.compile(RegexBox.DECLARATION_AND_ASSIGING_ON_VAR);
		Matcher matchDeclareAndAssignOnVar = paternDeclareAndAssignOnVar.matcher(line);
		
		if (matchDeclareAndAssignOnVar.matches()) {
			
			return DECLARATION_AND_ASSIGNINNG_IN_VAR;
		}
		Pattern paternAssignOnVar = Pattern.compile(RegexBox.ASSIGNING_ON_VAR);
		Matcher matchAssignOnVar = paternAssignOnVar.matcher(line);
		if (matchAssignOnVar.matches()) {
			return ASSIGNING_IN_VAR;
		}

		Pattern paternIf = Pattern.compile(RegexBox.IF_REGEX);
		
		Matcher matchIf = paternIf.matcher(line);
		Pattern paternWhile = Pattern.compile(RegexBox.WHILE_REGEX);
		Matcher matchWhile = paternWhile.matcher(line);
		if (matchIf.matches() || matchWhile.matches()) {
			return IF_OR_WHILE;
		}
		
		Pattern paternReturn = Pattern.compile(RegexBox.RETURN_LINE);
		Matcher matchReturn = paternReturn.matcher(line);
		if(matchReturn.matches()) {
			return RETURN_LINE;
		}
		
		Pattern paternMethod = Pattern.compile(RegexBox.METHOD_REGEX);
		Matcher matchMethod = paternMethod.matcher(line);
		if(matchMethod.matches()) {
			
			return METHOD_REGEX;
		}
		
		Pattern patternMethodCall = Pattern.compile(RegexBox.METHOD_CALL);
		Matcher matchMethodCall = patternMethodCall.matcher(line);
		if (matchMethodCall.matches()) {
			return METHOD_CALL;
		}
		
		Pattern paternCloseBracket = Pattern.compile(RegexBox.CLOSE_BRACKET);
		Matcher matchCloseBracket = paternCloseBracket.matcher(line);
		if(matchCloseBracket.matches()) {
			
			return CLOSE_BRACKET;
		}
		else {	
			return "No such kind of line";
		}
	}
	
	
	/**
	 * @return true iff the number of brackets in the block content is valid and false otherwise;
	 */
	public static  boolean checkBracketsValidity(ArrayList<String> content) {
		int counterOfbrackets = 0;

		for(int i = 0; i < content.size(); i ++) {

			if (content.get(i).contains(OPEN_BRACKET) && !content.get(i).contains(CLOSE_BRACKET)) { //not an array declaration
				counterOfbrackets ++;
			}
			else if (content.get(i).contains(CLOSE_BRACKET) && !content.get(i).contains(OPEN_BRACKET)) { //not an array declaration
				counterOfbrackets--;
			}
		}

		if(counterOfbrackets == VALID_BRACKETS) {
			return true;
		}
		else return false;
	}
	
	
	
	/**
	 * finds the content (all the lines) of the new block. 
	 * @param startLine the first line of the block.
	 * @param indexOfLine the index of the start line.
	 * @return an array list which contains the block content.
	 */
	public static ArrayList<String> findNewContent(ArrayList<String> content, String startLine, int indexOfLine) throws IllegalCodeException {

		indexOfLine++; //start of block content
		ArrayList<String> newContent = new ArrayList<>();

		int counterBrackts = 1;

		while(counterBrackts != 0) {
			if (indexOfLine >= content.size()) {
				throw new IllegalCodeException("Missing closing bracket");
			}
			
			if(content.get(indexOfLine).contains(OPEN_BRACKET) && !content.get(indexOfLine).contains(CLOSE_BRACKET)) {//not an array declaration
			
				counterBrackts ++;
			}
			
			else if (content.get(indexOfLine).contains(CLOSE_BRACKET) && !content.get(indexOfLine).contains(OPEN_BRACKET)) {//not an array declaration
				
				counterBrackts --;
				
			}
			newContent.add(content.get(indexOfLine));
			indexOfLine ++;
		}
		ArrayList<String> newContent1=new ArrayList<>();
		for(int i=0;i<newContent.size()-1;i++){
			newContent1.add(newContent.get(i));
		}
		return newContent1;
	}

	/**
	 * replace all the spaces of a given line in only one space.(normalize the line).
	 * @param line a given line.
	 * @param index the index of the given line.
	 * @param file a given file.
	 */
	public static void replaceAllSpaces(String line,int index, ArrayList<String> file) {
		
		Pattern paternDeclareAndAssignOnVar = Pattern.compile(RegexBox.MORE_THAN_ONE_SPACE);
		Matcher matchDeclareAndAssignOnVar = paternDeclareAndAssignOnVar.matcher(line);
		if(matchDeclareAndAssignOnVar.find()){
			line=matchDeclareAndAssignOnVar.replaceAll(" ");
			file.set(index, line);
		}
	}
		

	/**
	 * checks if the given line should contains the ";" literal in the end of
	 * it and if so checks if it really contains that literal (throws exception if not)
	 * and replace the ";" in empty string. 
	 * @param line a given line.
	 * @param file a given file.
	 * @param indexOfLine the index of the given lie.
	 * @throws IllegalCodeException
	 */
	public static void checkAndReplacePoints(String line, ArrayList<String> file ,int indexOfLine) throws IllegalCodeException {
		Pattern paternReturn = Pattern.compile(RegexBox.RETURN_LINE);
		Matcher matchReturn = paternReturn.matcher(line);
		Pattern paternDeclare = Pattern.compile(RegexBox.DECLARATION_ON_VAR);
		Matcher matchDeclare = paternDeclare.matcher(line);
		
		Pattern paternAssign = Pattern.compile(RegexBox.ASSIGNING_ON_VAR);
		Matcher matchAssign = paternAssign.matcher(line);
		
		Pattern paternDeclareAndAssign = Pattern.compile(RegexBox.DECLARATION_AND_ASSIGING_ON_VAR);
		Matcher matchDeclareAndAssign  = paternDeclareAndAssign.matcher(line);
		
		Pattern patternCall = Pattern.compile(RegexBox.METHOD_CALL);
		Matcher matchCall = patternCall.matcher(line);
		
		if(matchDeclare.matches() || matchAssign.matches() || matchDeclareAndAssign.matches() ||matchReturn.matches() ||
				matchCall.matches()) {
			
			if(line.contains(";")) {
				line = line.replaceAll(";", "");
				file.set(indexOfLine, line);
			}
			else {
				throw new IllegalCodeException("Missing ';' expression");
			}
		}
	}
}