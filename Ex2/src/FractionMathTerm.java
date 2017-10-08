
/**
 * @author Liat Ginosar
 *
 */
public class FractionMathTerm extends BinaryMathTerm {
	
	private MathTerm firstTerm;
	private MathTerm secondTerm; 
	/**
	 * Constructs a new Fraction term.
	 * @param firstTerm Term on the numerator ("Mone").
	 * @param secondTerm Term on the denominator ("Mechane").
	 */
	public FractionMathTerm(MathTerm firstTerm, MathTerm secondTerm) {
		this.firstTerm = firstTerm;
		this.secondTerm = secondTerm;
	}
	
	/**
	 * latex representation of this fraction math term using the \frac latex command.
	 */
	public String toLatex() {
		return ("\\frac" + firstTerm.toLatex() + secondTerm.toLatex());
		
	}
}
