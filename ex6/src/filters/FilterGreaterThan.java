package filters;
import java.io.File;

/**
 * 
 * @author Liat Ginosar
 *
 */
public class FilterGreaterThan extends Filter {

	private String upperBound;

	/**
	 * default constructor
	 */
	public FilterGreaterThan (File file, String upperBound, int numOfLine) throws NegativeOrZeroException {
		
		this.upperBound = upperBound;

		if(upperBound.equals("0") || upperBound.contains("-") ) {
			throw new NegativeOrZeroException(); 
		}
	}
	
	/**
	 * 
	 */
	public boolean isPass(File file) {
		if(file.length() > Integer.parseInt(upperBound))
			return true;
		return false;
	}
}
