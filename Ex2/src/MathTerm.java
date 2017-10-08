
/**
 * @author Liat Ginosar
 *
 */
public class MathTerm {

	private MathTerm exponentTerm;
	private boolean isBarred;
	private boolean isNegated;
	/**
	 * default constructor
	 */
	public MathTerm() {
		
	}
	
	/**
	 * 
	 * @return the exponent math term.
	 */
	public MathTerm getExponentTerm() {
		return this.exponentTerm;
	}
	
	/**
	 * 
	 * @return returns whether this math term was set to be barred.
	 */
	public boolean getIsBarred() {
		return this.isBarred;
	}
	
	/**
	 * 
	 * @return True if this term should be negated.
	 */
	public boolean getIsNegated() {
		return this.isNegated;
	}
	
	/**
	 * This method gets a math term to be placed as an exponent for the current math term.
	 * @param exponentTerm The MathTerm to be placed as an exponent of the current term.
	 */
	public void setExponentTerm(MathTerm exponentTerm){
		this.exponentTerm = exponentTerm;
	}
	
	/**
	 * Setting whether this MathTerm should be barred or not (a straight line on top of the term: see Latex's \overline{}).
	 * @return true if we want this term to be barred.
	 */
	public void setIsBarred(boolean isBarred) {
		this.isBarred = isBarred ;
	}
	
	/**
	 * Sets whether this math term should be negated (see Latex's \neg{}).
	 * @param isNegated
	 */
	void setIsNegated(boolean isNegated) {
		this.isNegated = isNegated;
	}
	
	/**
	 * This method should be implemented in any of MathTerm derivatives (inheriting classes)
	 * @return
	 */
	String toLatex() {
		return "";
	}
	
}
