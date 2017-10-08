package oop.ex7.main;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import java.util.Scanner;

import oop.ex7.blocks.GlobalBlock;
import oop.ex7.blocks.Method;

/**
 * class Sjavac
 */
public class Sjavac {

	/**
	 * main program.
	 * @param args the sjava file.
	 */
	public static void main (String args[]) {
		String sjava = args[0];

		try { 

			ArrayList<String> file = readFile(sjava);

			try {
				toParse(file);
				System.out.println("0");

			}
			catch(IllegalCodeException ex) {
				System.out.println("1");
			}

		}
		catch(IOException ex) {
			System.out.println("2");
		}
		catch (Exception ex) {
			ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
			PrintStream ps = new PrintStream(baos);
			ex.printStackTrace(ps);
			String stackTrace = baos.toString(); 
			System.out.print(stackTrace);
			System.out.println(ex.getMessage());
		}
	}

	
	/**
	 * reads the sjava file.
	 * @param sjava the sjava file.
	 * @return an array list of the sjava lines.
	 * @throws IOException
	 */
	public static ArrayList<String> readFile(String sjava) throws IOException{

		ArrayList<String> file=new ArrayList<>();
		File sourceFile = new File(sjava);

		Scanner sc = new Scanner(new FileInputStream(sourceFile));


		while(sc.hasNextLine()) {
			String s = sc.nextLine();

			file.add(s);
		}
		sc.close();
		return file;
	}
	
	/**
	 * 
	 * @param file the sjava file in array list of strings. (any cell is a line).
	 * @throws IllegalCodeException
	 */
	public static void toParse(ArrayList<String> file) throws IllegalCodeException {
		
		if (Tools.checkBracketsValidity(file)) { //the number of brackets is valid
			
		
			for(int i = 0; i< file.size(); i++) {
				Tools.replaceAllSpaces(file.get(i),i,file);
				
				Tools.checkAndReplacePoints(file.get(i),file,i);
			
			}

			GlobalBlock block = new GlobalBlock(file);
			
			block.parse(true);
			block.parse(false);
			
			ArrayList<Method> methods = block.getMethods();
			
			for (Method method : methods) {
				method.parseMethod(method.getReturnedValue(), null, method.getContent());
			}
		}
		else {
			throw new IllegalCodeException("Wrong number of brackets");
		}
	}
}