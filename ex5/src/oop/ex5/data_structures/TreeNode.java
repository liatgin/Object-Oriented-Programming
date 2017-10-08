package oop.ex5.data_structures;
/**
 * TreeNode
 */
public class TreeNode {
	
	private int height = 0;
	private int key = 0;
	private TreeNode parent;
	private TreeNode leftSon;
	private TreeNode rightSon;
	
	/**
	 * 
	 * @param key
	 */
	public TreeNode(int key) {
		this(key, null);
	}
	
	/**
	 * 
	 */
	public TreeNode() {
		this(0, null);
	}
	/**
	 * 
	 * @param newKey
	 * @param parent
	 */
	public TreeNode (int newKey, TreeNode parent) {
		this.key = newKey;
		this.parent = parent;
		this.leftSon = null;
		this.rightSon = null;
	}
	/**
	 * 
	 * @return
	 */
	public int getKey() {
		return this.key; 
	}
	/**
	 * 
	 * @return
	 */
	public TreeNode getleftSon() {
		return this.leftSon; 

	}
	/**
	 * 
	 * @return
	 */
	public TreeNode getRightSon() {
		return this.rightSon;
	}
	/**
	 * 
	 * @return
	 */
	public int getHeight() {
		return this.height;
	}
	/**
	 * 
	 * @return
	 */
	public TreeNode getParent() {
		return this.parent; 
	}
	/**
	 * 
	 * @param newHeight
	 */
	public void setHeight(int newHeight) {
		this.height = newHeight;
	}
	/**
	 * 
	 * @param key
	 */
	public void setKey(int key) {
		this.key = key;
		
	}
	/**
	 * 
	 * @param newRightSon
	 */
	public void setRightSon(TreeNode newRightSon) {
		this.rightSon = newRightSon; 
	}
	
	/**
	 * 
	 * @param newLeftSon
	 */
	public void setLeftSon( TreeNode newLeftSon) {
		this.leftSon = newLeftSon;
	}
	/**
	 * 
	 * @param newParent
	 */
	public void setParent(TreeNode newParent) {
		this.parent = newParent;
	}
	

	public int getRightSonHeight() {
		if (this.rightSon != null) {
			return this.rightSon.height; 
		}
		
		return -1;
	}
	
	public int getLeftSonHeight() {
		if (this.leftSon != null) {
			return this.leftSon.height; 
		}
		
		return -1;
	}

	public void updateHeights(){
		setHeight(Math.max(getLeftSonHeight(), getRightSonHeight()) + 1);
		
		if (parent != null)
		{
			parent.updateHeights();
		}
	}
}
