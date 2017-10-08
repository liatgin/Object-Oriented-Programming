package filters;
import java.io.File;
import java.util.ArrayList;


public abstract class Filter {
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<File> doFilterAction (File directory) {

		ArrayList<File> filteredFiles = new ArrayList<>();

		for(File file : directory.listFiles()) {
			if(isPass(file)) {
				filteredFiles.add(file);
			}
		}
		return filteredFiles; 
	}
	
	/**
	 * 
	 * @param file
	 * @return
	 */
	public abstract boolean isPass(File file);
		
}
