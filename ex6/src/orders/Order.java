package orders;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * 
 * @author Liat Ginosar
 *
 */
public abstract class Order implements Comparator<File> {
	
	/**
	 * 
	 */
	public abstract int compare (File file1, File file2);
	
	/**
	 * 
	 * @param filteredFiles
	 */
	public void doOrderAction(ArrayList<File> filteredFiles) {
		Collections.sort(filteredFiles, this);
	}	
}
