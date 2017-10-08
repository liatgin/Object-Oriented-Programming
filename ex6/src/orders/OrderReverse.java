package orders;
import java.io.File;

/**
 * 
 * @author Liat Ginosar
 *
 */
public class OrderReverse extends Order {
	private Order order;
	
	public OrderReverse(Order order) {
		this.order = order;
	}
	
	/**
	 * 
	 */
	public int compare(File file1, File file2) {
		return -order.compare(file1, file2);
	}
}
