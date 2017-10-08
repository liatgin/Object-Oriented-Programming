
/**
 * @author Liat Ginosar
 *
 */
public class SumMathTerm extends MathTerm {
	
	private MathTerm subTerm;
	private MathTerm superTerm;
	private MathTerm sumTerm;
	/**
	 * The constructor receives the 3 MathTerm that comprises the sum math term.
	 * @param subTerm The term beneath the sigma.
	 * @param superTerm  The term above the sigma.
	 * @param sumTerm The summed term.
	 */
	public SumMathTerm(MathTerm subTerm, MathTerm superTerm, MathTerm sumTerm) {
		this.subTerm = subTerm;
		this.superTerm = superTerm;
		this.sumTerm = sumTerm;
	}
	
	/**
	 * Generates the latex representation of this sum math term.
	 */
	public String toLatex() {
		return
		
	}
}
