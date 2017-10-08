import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeSet;

/**
 * @author Liat Ginosar
 *
 */
public class SimpleSetPerformanceAnalyzer {

	static final int DATA1 = 0;
	static final int DATA2 = 1;

	static final int CHAINED_HASH_SET = 0;
	static final int OPEN_HASH_SET = 1;
	static final int TREE_SET = 2;
	static final int LINKED_LIST = 3;
	static final int HASH_SET = 4;

	static Object[][] data = new Object[5][2];

	
	//array to keep all the classes names
	static final Class[] classes = {ChainedHashSet.class, OpenHashSet.class, TreeSet.class, LinkedList.class, HashSet.class};
	
	public static void main (String[] args) {

		initiateCells();

		loadFileContent("data1.txt", DATA1);
		loadFileContent("data2.txt", DATA2);

		checkContainsString(DATA1, "hi");
		checkContainsString(DATA1, "-13170890158");
		
		checkContainsString(DATA2, "23");
		checkContainsString(DATA2, "hi");
	}
	/**
	 * 
	 */
	public static void initiateCells (){
		data[TREE_SET][DATA1] = new TreeSet<String>();
		data[TREE_SET][DATA2] = new TreeSet<String>();

		data[LINKED_LIST][DATA1] = new LinkedList<String>();
		data[LINKED_LIST][DATA2] = new LinkedList<String>();

		data[HASH_SET][DATA1] = new HashSet<String>();
		data[HASH_SET][DATA2] = new HashSet<String>();
	}
	/**
	 * Initialize the 5 data structures according to a given file and print the time it took to initialize any data structure. 
	 * @param fileName the file to add to the data structures.
	 * @param column the column in the "data" array we should add the data file according to the data file name.
	 */
	public static void loadFileContent(String fileName, int column){

		String[] fileContent = Ex4Utils.file2array(fileName);
		long timeBefore = new Date().getTime();

		data[CHAINED_HASH_SET][column] = new ChainedHashSet(fileContent);
		long totalTimeToLoadFileToChainedHashSet = new Date().getTime() - timeBefore;
		System.out.println ("add " + fileName + " to ChainedHashSet: " + totalTimeToLoadFileToChainedHashSet + "miliseconds");
		
		data[OPEN_HASH_SET][column] = new OpenHashSet(fileContent);
		long totalTimeToLoadFileToOpenHashSet = new Date().getTime() - timeBefore;
		System.out.println ("add " + fileName + " to OpenHashSet: " + totalTimeToLoadFileToOpenHashSet + "miliseconds");


		timeBefore = new Date().getTime(); 
		
		for (String line : fileContent) {
			((TreeSet)data[TREE_SET][column]).add(line);
		}
		long totalTimeToLoadFileToTreeSet = new Date().getTime() - timeBefore; 
		
		
		for (String line : fileContent) {
			((LinkedList)data[LINKED_LIST][column]).add(line); 
		}
		long totalTimeToLoadFileToLinkedList = new Date().getTime() - timeBefore ; 
		
		  
		  
		for (String line : fileContent) {
			((HashSet)data[HASH_SET][column]).add(line);
		}
		long totalTimeToLoadFileToHashSet = new Date().getTime() - timeBefore ; 
		
		System.out.println ("add " + fileName + " to TreeSet: " + totalTimeToLoadFileToTreeSet + "miliseconds");
		System.out.println ("add " + fileName + " to LinkedList: " + totalTimeToLoadFileToLinkedList + "miliseconds");
		System.out.println ("add " + fileName + " to HashSet: " + totalTimeToLoadFileToHashSet + "miliseconds");
		System.out.println();
		System.out.println();
	}
	/**
	 * 
	 * @param column
	 * @param value
	 * @return
	 */
	public static long[] checkContainsString(int column, String value){
		
		long[] time = new long[5];
		
		long timeBefore;
		
		timeBefore = new Date().getTime();
		((ChainedHashSet)data[CHAINED_HASH_SET][column]).contains(value);		
		time[CHAINED_HASH_SET] = new Date().getTime() - timeBefore;
		
		time[OPEN_HASH_SET] = 0;
		
		//timeBefore = new Date().getTime();
		//((OpenHashSet)data[OPEN_HASH_SET][column]).contains(value);		
		//time[OPEN_HASH_SET] = new Date().getTime() - timeBefore;
				
		timeBefore = new Date().getTime();
		((TreeSet)data[TREE_SET][column]).contains(value);		
		time[TREE_SET] = new Date().getTime() - timeBefore;
		
		timeBefore = new Date().getTime();
		((LinkedList)data[LINKED_LIST][column]).contains(value);		
		time[LINKED_LIST] = new Date().getTime() - timeBefore;
		
		timeBefore = new Date().getTime();
		((HashSet)data[HASH_SET][column]).contains(value);		
		time[HASH_SET] = new Date().getTime() - timeBefore;
		
		for (int i = 0; i < time.length; i++) {
			System.out.println("Structure: " + classes[i].getName() + "\t\t by time of: " + time[i] + " miliseconds" + " to operate " + "contains(" + value + ")");
		}
		
		System.out.println();
		
		return time;	
	}
}
