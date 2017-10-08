package parsing;
import java.io.File;
import java.util.ArrayList;

import orders.Order;

import filters.Filter;


/**
 * 
 * @author Liat Ginosar
 *
 */
public class Section {
	private Filter filter;
	private Order order;
	private ArrayList<String> warnings = new ArrayList<String>();
	
	/**
	 * 
	 * @param newFilter
	 * @param newOrder
	 */
	public Section(Filter newFilter, Order newOrder, ArrayList<String> warnings) {
		this.filter = newFilter;
		this.order = newOrder;
		this.warnings = warnings;
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<String> getWarnings() {
		return this.warnings;	
	}
	
	/**
	 * 
	 * @param directory
	 * @return
	 */
	public ArrayList<File> listFiles (File directory) {
		
		ArrayList<File> listFilter = filter.doFilterAction(directory);
		order.doOrderAction(listFilter);
		return listFilter;	
	}
}
