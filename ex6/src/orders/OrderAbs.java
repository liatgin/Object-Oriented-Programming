package orders;
import java.io.File;
import java.util.Comparator;

/**
 * 
 * @author Liat Ginosar
 *
 */
public class OrderAbs extends Order implements Comparator<File> {
	
	public OrderAbs () {
		
	}
	/**
	 * 
	 */
	public int compare(File file1, File file2) {
		return file1.getAbsolutePath().compareTo(file2.getAbsolutePath());
	}
}
