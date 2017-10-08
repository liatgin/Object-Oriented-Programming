
/**
 * @author Liat Ginosar
 *
 */
public class SimpleMathTerm extends MathTerm {
	
	private String name;
	private int precisionDigits;
	
	/**
	 * 
	 * @param termName A string of either a single letter variable (x,y,z,a,b..) or a number (may be a floating point number).
	 */
	public SimpleMathTerm(String termName) {
		name = termName;
	}
	
	/**
	 * 
	 * @return true if this term represents a number.
	 */
	public boolean isNumeric() {
		if (name.length() > 1) {// must be a number
			return true;
		}
		else {
			switch (name) {
				case "0": 
					return true;
				
				case "1": 
					return true;
				
				case "2": 
					return true;
				
				case "3": 
					return true;
				
				case "4": 
					return true;
				
				case "5": 
					return true;
				
				case "6": 
					return true;
				
				case "7": 
					return true;
				
				case "8": 
					return true;
				
				case "9": 
					return true;
			}
		}
				return false;	
	}
	
	/**
	 * Sets the number of digits of precision in case this term represents a number.
	 * @param precisionDigits Number of digits right of the floating point on the latex representation.
	 */
	public void setPrecisionDigits(int precisionDigits) {
		this.precisionDigits = precisionDigits;
	}
	
	/**
	 * Generates the latex representation of the this simple math term.
	 * @return the Latex representation. If this term represents a variable,
	 * this method returns the variable name. Otherwise, if the term represents
	 * a number it should be trimmed to according to the precision parameter.
	 */
	public String toLatex() {
		if (isNumeric()) {
			double nameOfNumber = Double.parseDouble(name);
			nameOfNumber = Math.round(nameOfNumber*Math.pow(10, precisionDigits))/Math.pow(10, precisionDigits); 
			String nameOfNumberString = String.valueOf(nameOfNumber);
			return (nameOfNumberString);
		}
		else {
			return name;
		}
	}
}
