package oop.ex7.blocks;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import oop.ex7.main.IllegalCodeException;
import oop.ex7.main.Tools;
import oop.ex7.main.Variable;
import oop.ex7.regex.RegexBox;
/**
 * Class Block
 * represents any block in the program : inner block
 *(methods, and if and while block) and global block which 
 *includes the whole program block.
 */
public abstract class Block {

	private static final int NOT_FOUND = -1;
	protected String nameOfBlock;
	protected Block father;
	protected ArrayList<Variable> localVaribles = new ArrayList<Variable>();
	protected ArrayList <Method> methods = new ArrayList<Method>();
	protected ArrayList<String> content= new ArrayList<String>();

	/**
	 * constructor.
	 * @param nameOfBlock the name of the block.
	 * @param father the father block.
	 * @param content the lines that inside that block.
	 */
	public Block (String nameOfBlock, Block father, ArrayList<String> content) {
		this.nameOfBlock = nameOfBlock;
		this.father = father;
		this.content = content;	
	}

	/**
	 * @return an array list of the local variables. 
	 */
	public ArrayList<Variable> getLocalVariables() {
		return this.localVaribles;
	}

	/**
	 * @return the block`s father.
	 */
	public Block getFather() {
		return this.father;
	}
	
	/**
	 * @return an array list of the block`s content.
	 */
	public ArrayList<String> getContent(){
		return this.content;
	}
	
	/**
	 * 
	 * @param line a line to check its legality.
	 * @return true iff the given line is legal line.
	 * @throws IllegalCodeException
	 */
	protected abstract boolean checkLineAllowed(String line) throws IllegalCodeException;
	
	/**
	 * @return an array list of the methods blocks.
	 */
	public ArrayList<Method> getMethods() {
		return methods;
	}
	
	/**
	 * sends a certain method to parse.
	 * @param returnedValueType the type value the method returners.
	 * @param father the father of the method.
	 * @param content lines that inside that method.
	 * @throws IllegalCodeException
	 */
	public void parseMethod (String returnedValueType, Block father, ArrayList<String> content) throws IllegalCodeException {
		parseMethod(returnedValueType, father, content, false);
	}
	
	/**
	 * parse a given method.
	 * @param returnedValueType the type value the method returners.
	 * @param father the father of the method.
	 * @param content lines that inside that method block.
	 * @param addMethods a boolean var who says if this method already exist in the list of the methods or not.
	 * @throws IllegalCodeException
	 */
	public void parseMethod (String returnedValueType, Block father, ArrayList<String> content, boolean addMethods) throws IllegalCodeException { 

		if (Tools.checkBracketsValidity(content)) { //the number of brackets is valid

			for(int i = 0; i < content.size(); i ++) {

				String line = content.get(i);

				line = Tools.whichKindOfLine(content.get(i));

				if (!checkLineAllowed(line)) {
					// Skip this line
					continue;
				}
				
				switch (line) {

					case Tools.COMMENT:
					case Tools.EMPTY_LINE:
						break;
					
					case Tools.METHOD_REGEX : {

						ArrayList<String> methodContent = Tools.findNewContent(content, content.get(i), i);
						Method method = new Method(content.get(i), this, methodContent);
						
						if (addMethods) {
							methods.add(method);
						}

						i = i + methodContent.size() + 1;
						
						break;
					}
	
					case Tools.DECLARATION_ON_VAR : {
	
						Pattern paternDeclareOnVar = Pattern.compile(RegexBox.DECLARATION_ON_VAR);
						Matcher matchDeclareOnVar = paternDeclareOnVar.matcher(content.get(i));
						
						if (!matchDeclareOnVar.matches()) {
							throw new IllegalCodeException("Invalid declaration");
						}
						
						Variable newVar = new Variable(this, matchDeclareOnVar.group(1),matchDeclareOnVar.group(4));//new varible split according to capturing group
						if(isNonGlobalVarExist(newVar.getName()) != null) {
							throw new IllegalCodeException("duplicate varaiable");
						}
						this.localVaribles.add(newVar);
						break;
					}
	
					case Tools.ASSIGNING_IN_VAR : {
						Pattern paternAssignOnVar = Pattern.compile(RegexBox.ASSIGNING_ON_VAR);
						Matcher matchAssignOnVar = paternAssignOnVar.matcher(content.get(i));
						if(!matchAssignOnVar.matches()){
							throw new IllegalCodeException("Invalid assignment");
						}
						String nameOfVar = matchAssignOnVar.group(2);//splitedLine[0];
						String value = matchAssignOnVar.group(7);//splitedLine[1];
						String type = null;
						
						if (nameOfVar != null) {
							Variable targetVar = isVarExist(nameOfVar);

							if (targetVar == null) {
								throw new IllegalCodeException("Varaible is not exist");
							}
							
							type = targetVar.getType();

							targetVar.setValue();
						}
						else {
							type = getArrayType(matchAssignOnVar.group(1));
							
							if (type == null) {
								throw new IllegalCodeException("Illegal assignment");
							}
						}
						
						validAssigningPossibilities(type, value);
						break;	
					}

					case Tools.DECLARATION_AND_ASSIGNINNG_IN_VAR : {
						String type = null;
						String value = null;
						Pattern paternDeclareAndAssignOnVar = Pattern.compile(RegexBox.DECLARATION_AND_ASSIGING_ON_VAR);
						Matcher matchDeclareAndAssignOnVar = paternDeclareAndAssignOnVar.matcher(content.get(i));
						if(!matchDeclareAndAssignOnVar.matches()) {
							throw new IllegalCodeException("Invalid declaration and assignment");
						}
						
						type = Variable.normalizeType(matchDeclareAndAssignOnVar.group(1));
						value=matchDeclareAndAssignOnVar.group(5);
	
						validAssigningPossibilities(type, value);
	
						Variable newVar = new Variable(this, matchDeclareAndAssignOnVar.group(1), matchDeclareAndAssignOnVar.group(4), true);//new varaible split according to capturing group
						
						if (isNonGlobalVarExist(newVar.getName()) != null) {
							throw new IllegalCodeException("duplicate varaiable");
						}
						
						this.localVaribles.add(newVar);
						break;
					}
	
					case Tools.IF_OR_WHILE : {
	
						ArrayList<String> newContent = Tools.findNewContent(content, content.get(i), i); 
						Block newBlock = ConditionFactory.createCondition(content.get(i), newContent, this);
						newBlock.parseMethod(returnedValueType,newBlock, newContent, addMethods);
						i = i + newContent.size() + 1;// jumps over the new block
						break;
					}
	
					case Tools.METHOD_CALL : {
						Pattern paternCall = Pattern.compile(RegexBox.METHOD_CALL);
						Matcher matchCall = paternCall.matcher(content.get(i));
						if(matchCall.matches()){
							checkMethodCall(null, matchCall.group(2), matchCall.group(3));
						}
					}
					case Tools.RETURN_LINE : {
						String returnedvar = null;
						Pattern paternReturn = Pattern.compile(RegexBox.RETURN_LINE);
						Matcher matchReturn = paternReturn.matcher(content.get(i));
						if(matchReturn.matches()){
							returnedvar = matchReturn.group(3);
						}
	
						if(returnedvar!=null) {
							validAssigningPossibilities(returnedValueType, returnedvar);		
						}
						break;
					}
	
					default: 
						throw new IllegalCodeException("No Such line");
				}
			}
		}
		else {
			throw new IllegalCodeException("Wrong number of brackets"); 
		}
	}

	
	/**
	 * checks whether a new assigning is possible according to the types of the new assisgment value and the variable to assign to.
	 * @param destination the type of the var to assign into him.
	 * @param source the type of the new variable to assign into destination variable.  
	 * @return true iff the types of the destination and source are equals or if destination
	 * is double and source is int. return false otherwise.
	 */
	private boolean canAssign(String destination, String source) {
		destination = destination.trim();
		source = source.trim();
		
		if (destination.equals(source) || (destination.equals("double") && source.equals("int"))) {
			return true;
		}
		return false;
	}
	
	
	/**
	 * @param nameOfVar variable to check.
	 * @return the variable if its not a global variable and null if its a global variable.
	 */
	public  Variable isNonGlobalVarExist(String nameOfVar) {
		Variable var = isVarExist(nameOfVar);
		
		if (var != null && var.getBlock().getFather() == null) {
			var = null;
		}
		
		return var;
	}
	
	/**
	 * search for a given name of a variable.
	 * @param nameOfVar the name of the variable to search for.
	 * @return the variable itself if it exist and null otherwise.
	 */
	public  Variable isVarExist(String nameOfVar) {

		Pattern paternArrayElement = Pattern.compile(RegexBox.ARRAY_ELEMENT);
		Matcher matchArrayElement = paternArrayElement.matcher(nameOfVar);

		if(matchArrayElement.find()){
			nameOfVar=matchArrayElement.group(1);
		}

		Block block = this;
		
		while (block != null) {
			ArrayList<Variable> localVariables = block.getLocalVariables();
			
			int indexOfVar = findIndexOf(nameOfVar, localVariables);
			
			if(indexOfVar != NOT_FOUND) {
				return localVariables.get(indexOfVar);
			}
			
			block = block.getFather();
		}
		return null;
	}

	/**
	 * 
	 * @param values a string of an array parameters. 
	 * @return an array list of strings that contains the parameters of the array if exist.
	 */
	public String[] splitArrayValue(String values) {
		Pattern paternVarInsideBraket = Pattern.compile(RegexBox.VAR_INSIDE_BRAKET_ARRAY);
		Matcher matchVarInsideBraket = paternVarInsideBraket.matcher(values);
		if(matchVarInsideBraket.matches()){
			return matchVarInsideBraket.group(1).split(",", -1);
		}
		return null;

	}
	/** 
	 * @param var a variable to search for.
	 * @param localVariables a list of the local variables that we have.
	 * @return the index of the given variable if it was found in the local variables list and -1 otherwise. 
	 */
	public  int findIndexOf(String nameOfVar, ArrayList<Variable> localVariable) {

		for (int i = 0; i < localVariable.size(); i++ ) {

			if(localVariable.get(i).getName().equals( nameOfVar) ) {
				return i;
			}
		}
		return NOT_FOUND;
	}

	/**
	 * checks whether a certain assigning is legal and throws exception if its not  legal assignment. 
	 * @param type the value type
	 * @param value the value
	 * @throws IllegalCodeException
	 */
	public  void validAssigningPossibilities(String type, String value) throws IllegalCodeException {
		
		if (type.equals("int") || type.equals("double")) {
			Pattern paternExp=Pattern.compile(RegexBox.DOUBLE_OPERATOR_EXPRESSION);
			Matcher matchExp = paternExp.matcher(value);
			
			if(!matchExp.matches()){
				throw new IllegalCodeException("Not a legal expression");
			}

			validSimpleAssigningPossibilities(type, matchExp.group(1));
			
			if (matchExp.group(12) != null) {
				validSimpleAssigningPossibilities(type, matchExp.group(13));
			}
		}
		else {
			validSimpleAssigningPossibilities(type, value);
		}
	}
	
	/**
	 * 
	 * @param value the array to check.
	 * @return the type of a given array and throws an exception if the array is not exist or if its type isnt legal.
	 * @throws IllegalCodeException
	 */
	public String getArrayType(String value) throws IllegalCodeException {
		Pattern paternArrayElement = Pattern.compile(RegexBox.MINUS_ARRAY_ELEMENT);
		Matcher matchArrayElement = paternArrayElement.matcher(value);
		if(!matchArrayElement.matches()) {
			return null;
		}
		
		String arrayName = matchArrayElement.group(2);
		Variable arrayVar = isVarExist(arrayName);
		
		if (arrayVar == null) {
			throw new IllegalCodeException("Array does not exist");
		}
		
		if (arrayVar.getIsPrimitive()) {
			throw new IllegalCodeException("Variable is not an array");
		}
		
		if (!arrayVar.hasValue()) {
			throw new IllegalCodeException("The variable was not initialized"); 
		}
		
		if (matchArrayElement.group(1) != null) {
			throw new IllegalCodeException("Minus only allowed on numbers");
		}
		
		String arrayType = arrayVar.getType();
		String elementVarName = matchArrayElement.group(5);
		
		if (elementVarName != null) {
			Variable elementVar = isVarExist(elementVarName);

			if (elementVar == null) {
				throw new IllegalCodeException("Variable does not exist");
			}
			
			if (!elementVar.getIsPrimitive() || 
				!elementVar.getType().equals("int")) {
				throw new IllegalCodeException("Invalid value");
			}
		}
		
		return arrayType.replace("[]", "");
	}
	
	
	/**
	 * checks whether a certain assigning is legal and throws exception if its not  legal assignment. 
	 * @param type the value type
	 * @param value the value
	 * @throws IllegalCodeException
	 */
	public void validSimpleAssigningPossibilities(String type,String value) throws IllegalCodeException {
		value = value.trim();
		
		Pattern paternBoolean = Pattern.compile(RegexBox.BOOLEAN_VALUE);
		Matcher matchBoolean = paternBoolean.matcher(value);
		if (matchBoolean.matches()) {
			if (!canAssign(type,"boolean")) {
				throw new IllegalCodeException("Invalid target type");
			}
			return;
		}
		
		Pattern paternVarName = Pattern.compile(RegexBox.MINUS_VARIABLE_NAME);
		Matcher matchVarName = paternVarName.matcher(value);
		if(matchVarName.matches()){
			Variable var = isVarExist(matchVarName.group(2));

			if(var == null) {
				throw new IllegalCodeException("The variable does not exist");
			}
			
			if(!canAssign(type, var.getType())){
				throw new IllegalCodeException("The Varible type isnt equal to the target type");
			}
		
			if (!var.hasValue()) {
				throw new IllegalCodeException("The variable was not initialized"); 
			}
			
			if (matchVarName.group(1) != null && 
				!var.getType().equals("double") && 
				!var.getType().equals("int")) {
				throw new IllegalCodeException("Minus allowed only on numbers");
			}
			
			return;
		}

		Pattern paternMethodName = Pattern.compile(RegexBox.MINUS_METHOD_CALL);
		Matcher matchMethod = paternMethodName.matcher(value);
		if(matchMethod.matches()){
			String methodName = matchMethod.group(3);
			checkMethodCall(type, methodName, matchMethod.group(4));
			
			if (matchMethod.group(1) != null && 
				!type.equals("double") && !type.equals("int")) {
				throw new IllegalCodeException("Minus allowed only on numbers");
			}
			
			return;
		}

		String elementArrayType = getArrayType(value);
		
		if (elementArrayType != null) {
			if (type.contains("[]") || 
				!canAssign(type, elementArrayType)) {
				throw new IllegalCodeException("Invalid target type");
			}
			
			return;
		}
		
		String[] splitValues = splitArrayValue(value); 
		if(splitValues==null){
			
			Variable.checkValidPrimitive(type, value);
		}
		else {
			Pattern paternArrayType = Pattern.compile(RegexBox.VALID_ARRAY_TYPE);
			Matcher matchArrayType = paternArrayType.matcher(type);
			if(!matchArrayType.matches()){
				throw new IllegalCodeException("the value doesnt match the target type");
			}
			
			if (splitValues.length == 1 && splitValues[0].trim().equals("")) {
				// Return an empty array (OK)
				return;
			}
			
			String arrayType = matchArrayType.group(1);
			
			for(int j=0; j<splitValues.length; j++){
				splitValues[j] = splitValues[j].trim();
				
				Pattern paternElementBoolean = Pattern.compile(RegexBox.BOOLEAN_VALUE);
				Matcher matchElementBoolean = paternElementBoolean.matcher(splitValues[j]);
				if (matchElementBoolean.matches()) {
					if (!canAssign(arrayType,"boolean")) {
						throw new IllegalCodeException("Invalid target type");
					}
					continue;
				}
				
				Pattern paternElementVar = Pattern.compile(RegexBox.MINUS_VARIABLE_NAME);
				Matcher matchElementVar = paternElementVar.matcher(splitValues[j]);
				if(matchElementVar.matches()){
					String splitVarName = matchElementVar.group(2);
					Variable splitVar= isVarExist(splitVarName);
					if(splitVar!=null){
						if(!canAssign(arrayType, splitVar.getType())){
							throw new IllegalCodeException("the value doesnt match the target type");
						}
						
						if (!splitVar.hasValue()) {
							throw new IllegalCodeException("Element variable not initialized");
						}
						
						if (matchElementVar.group(1) != null && 
							!splitVar.getType().equals("double") && 
							!splitVar.getType().equals("int")) {
							throw new IllegalCodeException("Minus only allowed on numbers");
						}
					}
					else{
						throw new IllegalCodeException("Variable does not exist");
					}
				}
				else
				{
					Variable.checkValidPrimitive(arrayType, splitValues[j]);
				}
			}
		}
	}
	
	
	
	/**
	 * checks whether a call to method is legal by check if this method is already exist and if it exists
	 * checks its return type and its parameters. 
	 * @param type the return type of the method.
	 * @param name the name of the method.
	 * @param parameters the parameters  of the method
	 * @throws IllegalCodeException
	 */
	protected void checkMethodCall(String type, String name, String parameters) throws IllegalCodeException {
		
		for(Method method : getGlobalMethods()){
			if (!method.getMethodName().equals(name)) {
				continue;
			}
			
			if(type != null && !canAssign(type, method.getReturnedValue())) {
				throw new IllegalCodeException("Illegal method return type");
			}
			
			String[] paramterTypes = method.getParameterTypes();
			String[] values = parameters.split(",", -1);
			
			if (values.length == 1 && values[0].trim().equals("")) {
				values = new String[0];
			}
			
			if (values.length != paramterTypes.length) {
				throw new IllegalCodeException("Invalid parameter count");
			}
			
			for (int i = 0; i < values.length; i++) {
				validAssigningPossibilities(paramterTypes[i], values[i].trim());
			}
			
			return;
		}
		
		throw new IllegalCodeException("Method not found");
	}
	
	/**
	 * @return the global block. 
	 */
	public Block getGlobalBlock() {
		Block block = this;
		
		while (block.getFather() != null) {
			block = block.getFather();
		}
		return block;
	}
	
	/**
	 * @return the methods of the global block.
	 */
	protected ArrayList<Method> getGlobalMethods() {
		return getGlobalBlock().getMethods();
	}
}
