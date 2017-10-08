package filters;
import java.io.File;

/**
 * 
 * @author Liat Ginosar
 *
 */
public class FilterExecutable extends Filter {
	
	private String value;
	File file;
	
	/**
	 * default constructor
	 */
	public FilterExecutable (File file, String isExecutable , int numOfLine) throws YesOrNoException {
		this.file = file;	
		
		//shold be Yes/No
		this.value = isExecutable.substring(isExecutable.indexOf("#")); 
		if(!this.value.equals("YES") && !this.value.equals("NO")){
			throw new YesOrNoException();
		}
	}

	/**
	 * 
	 */
	public boolean isPass(File file) {
		switch (this.value) {
		case "YES":
			if(file.canExecute()) {
				return true;
			}
		
		case "NO" :
			if (!file.canExecute()) {
				return true;
			}
			break;
		}
		return false;
	}
}
