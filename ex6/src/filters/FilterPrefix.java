package filters;
import java.io.File;

/**
 * 
 * @author Liat Ginosar
 *
 */
public class FilterPrefix extends Filter {
	String prefix;
	File file;
	
	public FilterPrefix(File file, String prefix) {
		this.prefix = prefix;
		this.file = file;
	}
	
	/**
	 * 
	 */
	public boolean isPass(File file) {
		int prefixLength = this.prefix.length();
		if(file.getName().substring(-1, prefixLength).equals(prefix)) {
			return true;
		}
		return false;
	}
}
