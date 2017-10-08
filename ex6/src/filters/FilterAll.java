package filters;
import java.io.File;

/**
 * 
 * @author Liat Ginosar
 *
 */
public class FilterAll extends Filter {
	
	/**
	 * 
	 */
	public boolean isPass(File file) {
		return true;
	}
}
