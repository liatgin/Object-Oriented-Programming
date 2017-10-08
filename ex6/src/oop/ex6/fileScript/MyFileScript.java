package oop.ex6.fileScript;
import java.io.File;
import java.util.ArrayList;

import manager.TypeTwoException;

import parsing.Parser;
import parsing.Section;

/**
 * 
 * @author Liat Ginosar
 *
 */
public class MyFileScript {
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		
		File directory = new File(args[0]);
		File commandFile = new File(args[1]);

		Parser pars;
		
		try {
			pars = new Parser(directory, commandFile);
			pars.readFromFile(); 
		}
		
		catch(TypeTwoException ex) {
			System.err.println("ERROR");
			return;
		}
		
		for(Section section : pars.getSections()) {
			
			//prints the warnings of a specific section.
			for (String warning : section.getWarnings()) { 
				System.out.println(warning);
			}
			//prints the filtered and ordered files.
			ArrayList<File> finalList = section.listFiles(directory);
			for(File file : finalList) {
				System.out.println(file.getName());
				
			}
		}	
	}
}
