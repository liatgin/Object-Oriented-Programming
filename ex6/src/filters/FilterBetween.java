package filters;
import java.io.File;

/**
 * 
 * @author Liat Ginosar
 *
 */
public class FilterBetween extends Filter {
	
	private int lowerBound ;
	private int upperBound ; 
	
	/**
	 * constructor
	 */
	public FilterBetween (File file, String lowerBound, String upperBound ,int numOfLine) throws BetweenParametersException, NegativeOrZeroException {
		
		this.lowerBound = Integer.parseInt(lowerBound);
		this.upperBound = Integer.parseInt(upperBound);
	
		if(this.lowerBound > this.upperBound ) {
			throw new BetweenParametersException();
		}
		if(this.lowerBound < 0 || this.upperBound < 0) {
			throw new NegativeOrZeroException(); 
		}
	}
	
	/**
	 * 
	 */
	public boolean isPass(File file) {
		if(file.length() >= this.lowerBound && file.length() <= this.upperBound) {
			return true;
		}
		return false;
	}
}
