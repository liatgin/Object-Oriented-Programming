package filters;
import java.io.File;
/*
 * 
 */
public class FilterWritable extends Filter {

	private String value;
	File file;

	/**
	 * 
	 * @param file
	 * @param isWritable
	 * @param numOfLine
	 * @throws YesOrNoException
	 */
	public FilterWritable (File file, String isWritable , int numOfLine) throws YesOrNoException {
		this.file = file;
		
		//shold be Yes/No
		this.value = isWritable.substring(isWritable.indexOf("#")); 
		if(!this.value.equals("YES") && !this.value.equals("NO")) {
			throw new YesOrNoException();
		}
	}
	
	/**
	 * 
	 */
	public boolean isPass(File file) {
		switch (this.value) {
			case "YES":
				if(file.canWrite()) {
					return true;
				}
			case "NO" :
				if (!file.canWrite()) {
					return true;
			}
			break;
		}	
		return false;
	}
}
