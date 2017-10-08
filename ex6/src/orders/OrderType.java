package orders;
import java.io.File;
import java.util.Comparator;

/**
 * 
 * @author Liat Ginosar
 *
 */
public class OrderType extends Order implements Comparator<File> {
	
	public OrderType () {
		
	}
	
	/**
	 * 
	 */
	public int compare(File file1, File file2) {
		 String type1 = file1.toString().substring(file1.toString().indexOf("."));
		 String type2 = file2.toString().substring(file2.toString().indexOf("."));
		 return type1.compareTo(type2);
	}
}
