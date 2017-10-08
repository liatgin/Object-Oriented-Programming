package orders;
import java.io.File;
import java.util.Comparator;

/**
 * 
 * @author Liat Ginosar
 *
 */
public class OrderSize extends Order implements Comparator<File> {
	
	public OrderSize () {
		
	}
	
	/**
	 * 
	 */
	public int compare(File file1, File file2) {
		
		if(file1.length() > file2.length()) {
			return 1;
		}
		else if (file1.length() == file2.length()) {
			return 0;
		}
		else
			return -1;
	}
}
