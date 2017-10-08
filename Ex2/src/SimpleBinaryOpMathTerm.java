
/**
 * @author Liat Ginosar
 *
 */
public class SimpleBinaryOpMathTerm extends BinaryMathTerm {
	private MathTerm firstTerm;
	private MathTerm secondTerm;
	private char sign;
	/**
	 * instantiate a new SimpleBinaryOpMathTerm.
	 * @param firstTerm The first term of the binary operation.
	 * @param secondTerm The second term of the binary operation.
	 * @param sign The operation sign. Can be any of the following:
	 * "+,-,*,<,>,=". If sign == '*', you should use the \cdot latex command (a\cdotb).
	 * Otherwise you can use sign itself.
	 */
	public SimpleBinaryOpMathTerm(MathTerm firstTerm, MathTerm secondTerm, char sign) {
		this.firstTerm = firstTerm;
		this.secondTerm = secondTerm;
		this.sign = sign;
	}
	
	/**
	 * @return The latex representation of the operation: "firstTerm operationSign secondTerm".
	 */
	public String toLatex() {
		switch (sign) {
			case '*': {
				return (firstTerm.toLatex() + "\\cdot" + secondTerm.toLatex());
				break;
			}
			case '+': {
				return (firstTerm.toLatex() + "+" + secondTerm.toLatex());
				break;
			}
			case '-': {
				return (firstTerm.toLatex() + "-" + secondTerm.toLatex());
				break;
			}
			case '>': {
				return (firstTerm.toLatex() + ">" + secondTerm.toLatex());
				break;
			}
			case '<': {
				return (firstTerm.toLatex() + "<" + secondTerm.toLatex());
				break;
			}
			case '=': {
				return ((firstTerm.toLatex() + "=" + secondTerm.toLatex()));
				break;
			}
				
		}
		return "";
		
	}
}
