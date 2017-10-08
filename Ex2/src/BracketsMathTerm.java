
/**
 * @author Liat Ginosar
 *
 */
public class BracketsMathTerm extends MathTerm {
	
	private MathTerm internalTerm; 
	
	/**
	 * The constructor receives the MathTerm they will be rendered as the term inside the brackets.
	 * @param internalTerm The term that resides within the brackets.
	 */
	public BracketsMathTerm(MathTerm internalTerm) {
		this.internalTerm = internalTerm; 
	}
	
	/**
	 * 
	 * @return the following string representation: "\left( " + internal term representation + " \right)"
	 * (notice the space before and after the internal term).
	 */
	public String toLatex() {
		return ("\\left( " + internalTerm.toLatex() + " \\right)");
	}
}
