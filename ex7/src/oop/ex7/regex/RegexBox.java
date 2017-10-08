package oop.ex7.regex;

/**
 * class RegexBox
 * All the regular expressions which were used in the program.
 */
public class RegexBox {
	
	public final static String VARIABLE_NAME = "([A-Za-z][_A-Za-z0-9]*|_[_A-Za-z0-9]+)"; 
	public final static String MINUS_VARIABLE_NAME ="(-)?\\s*" + VARIABLE_NAME;
	
	public final static String METHOD_NAME ="(([A-Za-z][A-Za-z0-9]*)\\s*\\((.*)\\))";
	
	public final static String METHOD_CALL ="\\s*" + METHOD_NAME + "\\s*;?\\s*";
	public final static String MINUS_METHOD_CALL ="\\s*(-)?" + METHOD_CALL;
	
	
	public final static String VALID_TYPE_WITH_SPACE = "\\s*(int|double|String|boolean|char)(\\s*\\[\\s*\\]\\s*|\\s+)";
	
	public final static String VALID_TYPE = "\\s*(int|double|String|boolean|char)((\\s*\\[\\s*\\]\\s*)|\\s*)";
	
	public final static String INTEGER_KIND_OF_TYPE = "-?\\s*"+VARIABLE_NAME+"\\s*|\\s*-?\\s*"+METHOD_NAME+"\\s*|\\s*-?\\s*[0-9]+\\s*";
	
	public final static String INTEGER_VALUE = INTEGER_KIND_OF_TYPE+"\\s*|"+INTEGER_KIND_OF_TYPE+"[+\\-*/]"+INTEGER_KIND_OF_TYPE + "\\s*";
	
	public final static String DOUBLE_KIND_OF_TYPE = "\\s*-?\\s*(\\d+[.]?\\d*|[.]\\d+)\\s*";
	
	public final static String DOUBLE_VALUE = DOUBLE_KIND_OF_TYPE+"\\s*|"+DOUBLE_KIND_OF_TYPE+"[+\\-*/]"+DOUBLE_KIND_OF_TYPE + "\\s*";;
	
	public final static String CHAR_VALUE = "\\s*'.'\\s*";
	
	public final static String STRING_VALUE = "\\s*\".*\"\\s*";
	
	public final static String BOOLEAN_VALUE="\\s*(true|false)\\s*";
	
	public final static String METHOD_REGEX = "\\s*((" + VALID_TYPE_WITH_SPACE + ")|void\\s+)(([A-Za-z][_A-Za-z0-9]*)\\s*\\((.*)\\))\\s*\\{\\s*"; 
	
	public final static String DECLARATION_ON_VAR = "\\s*(" + VALID_TYPE_WITH_SPACE + ")" + VARIABLE_NAME + "\\s*(\\;)?";//to check place of the group
	
	public final static String DECLARATION_AND_ASSIGING_ON_VAR="\\s*(" + VALID_TYPE_WITH_SPACE + ")" + VARIABLE_NAME + "\\s*=\\s*(.*)(\\;)?";
	
	public final static String COMMENT_LINE = "\\s*//(.*)";
	
	public final static String ALL="(.*)";
	
	public final static String EMPTY_LINE = "^\\s*$";
	
	public final static String RETURN_LINE ="\\s*(return)(\\s+(.*))?\\s*(\\;)?";

	public static final String ARRAY_VALUE_ELEMENT = DOUBLE_VALUE + "|" + VARIABLE_NAME;
	public static final String ARRAY_VALUE = "\\s*\\{\\s*(" + ARRAY_VALUE_ELEMENT + "(," + ARRAY_VALUE_ELEMENT + ")*)?\\s*\\}\\s*";///to do

	public static final String ARRAY_ELEMENT = "\\s*" + VARIABLE_NAME + "\\s*\\[\\s*(" + "\\d+|-?\\d+\\s*[+\\-/\\*]\\s*-?\\d+|" + MINUS_VARIABLE_NAME + ")\\s*\\]\\s*" ;

	public static final String MINUS_ARRAY_ELEMENT = "\\s*(-)?" + ARRAY_ELEMENT;
	
	public final static String CONDITION_REGEX="\\s*(" + BOOLEAN_VALUE + "|"+METHOD_NAME+"|"+VARIABLE_NAME + "|" + ARRAY_ELEMENT + ")\\s*";
	
	public final static String IF_REGEX = "\\s*if\\s*\\(" + CONDITION_REGEX + "\\)\\s*\\{\\s*";
	
	public final static String WHILE_REGEX = "\\s*while\\s*\\(" + CONDITION_REGEX + "\\)\\s*\\{\\s*";
	
	public final static String ASSIGNING_ON_VAR = "\\s*(" + VARIABLE_NAME + "|" + ARRAY_ELEMENT + ")\\s*=\\s*(.*)\\s*;?"; //? is in case we replaced the ";" with ""

	public static final String VALID_INDEX = "\\s*\\d+\\s*([+\\-/\\*]\\s*-?\\d+)?\\s*";
	
	public static final String INTEGER_REGEX="\\s*-?\\s*\\d+((\\+|-|/|\\*)-?\\d+)?\\s*";
	
	public static final String MORE_THAN_ONE_SPACE = "\\s+";
	
	public static final String RETURN_TYPE_OF_METHOD= VALID_TYPE + "|\\s*(void)\\s*";
	
	public static final String CLOSE_BRACKET = "\\}";
	
	public static final String VAR_INSIDE_BRAKET_ARRAY="\\s*\\{(.*)\\}\\s*";
	
	public final static String VALID_ARRAY_TYPE = "(int|double|String|boolean|char)\\s*\\[\\s*\\]";
	
	public final static String DOUBLE_EXPRESSION = DOUBLE_KIND_OF_TYPE + "|" + MINUS_ARRAY_ELEMENT + "|\\s*-?\\s*" + METHOD_NAME + "\\s*|\\s*-?\\s*" + VARIABLE_NAME + "\\s*";
	
	public final static String DOUBLE_OPERATOR_EXPRESSION = "(" + DOUBLE_EXPRESSION + ")(\\s*[+\\-*/](" + DOUBLE_EXPRESSION + "))?";
	
	public final static String EXPRESSION_REGEX = "((" + DOUBLE_EXPRESSION + ")(\\s*[+\\-*/](" + DOUBLE_EXPRESSION + "))?)|(" + STRING_VALUE + ")|(" + CHAR_VALUE + ")|(" + BOOLEAN_VALUE + ")|(" + ARRAY_VALUE + ")";

}