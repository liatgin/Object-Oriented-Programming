package filters;
import java.io.File;

/**
 * 
 * @author Liat Ginosar
 *
 */
public class FilterNot extends Filter {
	
	private Filter filter;
	
	public FilterNot (Filter filter) {
		this.filter = filter;
	}
	
	/**
	 * 
	 */
	public boolean isPass(File file) {
		if (filter.isPass(file))
			return false;
		else
			return true;
	}
}
