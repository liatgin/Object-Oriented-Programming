package oop.ex7.blocks;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import oop.ex7.main.IllegalCodeException;
import oop.ex7.main.Variable;
import oop.ex7.regex.RegexBox;

/**
 * 
 * class Method.
 * represents an method block.
 */
public class Method extends InnerBlock {
	

	private String returnedValueType;
	private String methodName;
	private String[] parameters;
	private String[] parameterTypes;

	
	/**
	 * constructor
	 * @param nameOfMethod the signature of the method.
	 * @param father the father of the method
	 * @param content the content of the method (all the lines in that block).
	 * @throws IllegalCodeException
	 */
	public Method(String nameOfMethod, Block father, ArrayList<String> content) throws IllegalCodeException {
				
		super(nameOfMethod, father, content);
		Pattern paternMethod = Pattern.compile(RegexBox.METHOD_REGEX);
		Matcher matchMethod = paternMethod.matcher(nameOfMethod);
		
	
		if(matchMethod.matches()){
			this.methodName = matchMethod.group(6);
			this.returnedValueType = Variable.normalizeReturnType(matchMethod.group(1));
		}
		else
			throw new IllegalCodeException("Wrong method block declaration");
		
		String paramsRepresntaion=matchMethod.group(7);
		this.parameters=paramsRepresntaion.split(",",-1);
		isMethodParametersValid();
	}
	
	/**
 	 * checks if the returned value type of the method is valid. 
	 * @throws WrongReturnedValueTypeException 
	 */
	public void isReturnedValueTypeValid() throws IllegalCodeException { 
		Pattern paternMethod = Pattern.compile(RegexBox.RETURN_TYPE_OF_METHOD);
		Matcher matchMethod = paternMethod.matcher(this.returnedValueType);
		if(!matchMethod.find()){
			throw new IllegalCodeException("Wrong returned value");
		}
	}
	
	/**
	 * checks if the method name is valid.
	 * @throws IllegalCodeException
	 */
	public void isNameMethodValid() throws IllegalCodeException {
		Pattern paternMethod = Pattern.compile(RegexBox.METHOD_NAME);
		Matcher matchMethod = paternMethod.matcher(this.methodName);
		
		if(!matchMethod.matches()){
			throw new IllegalCodeException("Wrong method name");
		}
	}
	
	
	/**
	 * @return true iff the parameters who were sent are valid(right order).
	 * @throws IllegalCodeException 
	 */
	public void isMethodParametersValid() throws IllegalCodeException {

		if (this.parameters.length == 1 && this.parameters[0].equals("")) {
			this.parameterTypes = new String[0];
			return;
		}
		
		this.parameterTypes = new String[this.parameters.length];
		ArrayList<String> parameterNames = new ArrayList<String>();
		
		for(int i = 0; i < this.parameters.length; i++){
			String parameter = this.parameters[i];
			Pattern paternParameter = Pattern.compile(RegexBox.DECLARATION_ON_VAR);
			Matcher matchParameter = paternParameter.matcher(parameter);
			
			if(parameter.equals("") || 
				!matchParameter.matches()) {
				throw new IllegalCodeException("Wrong parameter");
			}
			
			Variable var=new Variable(this, matchParameter.group(1), matchParameter.group(4), true);
		
			if (parameterNames.contains(var.getName())) {
				throw new IllegalCodeException("duplicate parameters");
			}
			
			parameterNames.add(var.getName());
			
			this.parameterTypes[i] = Variable.normalizeType(matchParameter.group(2) + matchParameter.group(3));
			this.localVaribles.add(var);
		}
	}
	

	/**
	 * @return the returned value of the method.
	 */
	public String getReturnedValue() {
		return this.returnedValueType;
	}
	
	/**
	 * @return the name of the method.
	 */
	public String getMethodName() {
		return this.methodName;
	}
	
	/**
	 * @return an array of the method parameters types
	 */
	public String[] getParameterTypes() {
		return this.parameterTypes;
	}
}