package filters;
import java.io.File;

/**
 * 
 * @author Liat Ginosar
 *
 */
public class FilterContains extends Filter {
	
	private String subString;
	
	/**
	 * constructor
	 * @param subStringToCheck
	 */
	public FilterContains (File file, String subStringToCheck) {
		this.subString = subStringToCheck;
		
	}
	
	/**
	 * 
	 */
	public boolean isPass(File file) {
		if(file.toString().contains(subString)) {
			return true;		
		}
		return false;
	}
}
