package filters;
import java.io.File;

/**
 * 
 * @author Liat Ginosar
 *
 */
public class FilterFile extends Filter {
	private String name;
	
	public FilterFile (File file, String name) {
		this.name = name;
	}

	/**
	 * 
	 */
	public boolean isPass(File file) {
		if(file.getName().equals(this.name))
			return true;
		return false;
	}
}
