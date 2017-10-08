package parsing;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import orders.Order;

import manager.SubSectionNameException;
import manager.TypeTwoException;

import filters.Filter;


/**
 * 
 * @author Liat Ginosar
 *
 */
public class Parser {
	
	private File commandFile;
	private File directory;
	private ArrayList<Section> sections = new ArrayList<Section>();
	//private ArrayList<String> warnings;
	
	/**
	 * 
	 * @param directory
	 * @param commandFile
	 */
	public Parser (File directory, File commandFile) {
		this.directory = directory;
		this.commandFile = commandFile;	
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Section> getSections() {
		return this.sections;
	}
	
	/**
	 * 
	 * @return
	 */
	//public ArrayList<String> getWarnings() {
	//	return this.warnings;
	//}
	
	/**
	 * 
	 * @throws TypeTwoException
	 */
	public void readFromFile() throws TypeTwoException {
		Scanner sc = null;
		
		try {
			sc = new Scanner(this.commandFile);
			String filterName;
			String orderName;
			int numOfLine = 1;
			
	
			while(sc.hasNext()) {
				
				ArrayList<String> warnings = new ArrayList<String>();
				
				if(!sc.next().equals("Filter")) {
					throw new SubSectionNameException();
				}
				
				// Creates new filter
				filterName = sc.next();
				numOfLine ++;
				Filter newFilter = FilterFactory.createFilter(this.directory, filterName , numOfLine, warnings);
			
				if(!sc.next().equals("Order")) {
					throw new SubSectionNameException();
				}
	
				//Creates new order
				orderName = sc.next();
				numOfLine ++;
				Order newOrder = OrderFactory.createOrder(this.directory, orderName ,numOfLine ,warnings);
			
				Section newSection = new Section(newFilter, newOrder, warnings); 
				// updates the arrayList with new section.
				sections.add(newSection);
			}
		}
		
		catch(FileNotFoundException ex) {
			throw new TypeTwoException();
		}
		
		finally {
			if (sc != null)
				sc.close();
		}	
	}
}
	
