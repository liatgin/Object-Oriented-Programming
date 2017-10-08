package filters;

import java.io.File;
/**
 * 
 * @author Liat Ginosar
 *
 */
public class FilterSuffix extends Filter {
	String suffix;
	File file;

	/**
	 * default constructor
	 */
	public FilterSuffix(File file, String suffix) {
		this.suffix = suffix;
		this.file = file;
	}
	
	/**
	 * 
	 */
	public boolean isPass(File file) {
		int suffixLength = this.suffix.length();
		if(file.getName().substring((this.suffix.length() - suffixLength) - 1).equals(this.suffix))
			return true;
		return false;
	}
}
