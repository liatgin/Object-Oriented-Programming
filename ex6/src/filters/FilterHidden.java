package filters;
import java.io.File;

/**
 * 
 * @author Liat Ginosar
 *
 */
public class FilterHidden extends Filter {

	private String value;

	/**
	 * default constructor
	 */
	public FilterHidden (File file, String isHidden , int numOfLine) throws YesOrNoException {
		
		//shold be Yes/No
		this.value = isHidden.substring(isHidden.indexOf("#")); 
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
				if(file.isHidden()) {
					return true;
				}
		
			case "NO" :
				if (!file.isHidden()) {
					return true;
				}
			break;
		}
		return false;
	}
}
