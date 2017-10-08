package filters;
import java.io.File;
/**
 * 
 * @author Liat Ginosar
 *
 */
public class FilterSmallerThan extends Filter {

	private String lowerBound;

	/**
	 * default constructor
	 */
	public FilterSmallerThan (File file, String lowerBound, int numOfLine) throws NegativeOrZeroException {
		this.lowerBound = lowerBound;
		if(lowerBound.equals("0") || lowerBound.contains("-") ) {
			throw new NegativeOrZeroException(); 
		}
	}
	/**
	 * 
	 */
	public boolean isPass(File file) {
		if(file.length() < Integer.parseInt(lowerBound))
			return true;
		return false;
	}
}
