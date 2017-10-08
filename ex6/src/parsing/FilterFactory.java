package parsing;
import java.io.File;
import java.util.ArrayList;

import manager.TypeOneException;

import filters.Filter;
import filters.FilterAll;
import filters.FilterBetween;
import filters.FilterContains;
import filters.FilterExecutable;
import filters.FilterFile;
import filters.FilterGreaterThan;
import filters.FilterHidden;
import filters.FilterNot;
import filters.FilterPrefix;
import filters.FilterSuffix;
import filters.FilterWritable;



/**
 * 
 * @author Liat Ginosar
 *
 */
public class FilterFactory {
	
	/**
	 * 
	 * @param file
	 * @param filterString
	 * @param numOfLine
	 * @param warnings
	 * @return
	 */
	public static Filter createFilter(File file, String filterString ,int numOfLine, ArrayList<String> warnings) {
		
		String [] splitFilter = filterString.split("#");
		
		try {
			Filter newFilter;
			int paramCount = 1;// in order to check whether it is a NOT filter
			
			if(splitFilter[0].equals("greater_than")) {
				newFilter = new FilterGreaterThan(file, splitFilter[1], numOfLine );	
			}
			else if(splitFilter[0].equals("between")) {
				paramCount = 2;
				newFilter = new FilterBetween(file, splitFilter[1], splitFilter[2], numOfLine);	
			}
			else if(splitFilter[0].equals("smaller_than")) {
				newFilter = new FilterGreaterThan(file, splitFilter[1], numOfLine );	
			}
			else if(splitFilter[0].equals("file")) {
				newFilter = new FilterFile(file, splitFilter[1]);	
			}
			else if(splitFilter[0].equals("contains")) {
				newFilter = new FilterContains(file, splitFilter[1]);	
			}
			else if(splitFilter[0].equals("prefix")) {
				newFilter = new FilterPrefix(file, splitFilter[1]);	
			}
			else if(splitFilter[0].equals("suffix")) {
				newFilter = new FilterSuffix(file, splitFilter[1]);	
			}
			else if(splitFilter[0].equals("writable")) {
				newFilter = new FilterWritable(file, splitFilter[1], numOfLine );	
			}
			else if(splitFilter[0].equals("excecutable")) {
				newFilter = new FilterExecutable( file,splitFilter[1], numOfLine );	
			}
			else if(splitFilter[0].equals("hidden")) {
				newFilter = new FilterHidden(file, splitFilter[1], numOfLine );	
			}
			else if(filterString.equals("all")) {
				newFilter = new FilterAll();
			}
			else {
				throw new WrongFilterNameException();
			}
			
			if (splitFilter.length > paramCount + 1 && 
					splitFilter[paramCount + 1].equals("NOT")) {
				newFilter = new FilterNot(newFilter);
			}
			
			return newFilter;
		}
		catch(TypeOneException ex) {
			warnings.add("Warning in line " + numOfLine);
		}
		
		return createFilter(file, "all" ,numOfLine, warnings);
	}
}

