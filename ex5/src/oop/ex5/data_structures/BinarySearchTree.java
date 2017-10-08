package oop.ex5.data_structures;
import java.util.Iterator;
/**
 * BinarySearchTree
 */
public class BinarySearchTree {

	private TreeNode root = new TreeNode();

	private static final int NOT_FOUND = -1;
	private static final int NO_CHILDREN = 0;
	private static final int ONE_CHILD = 1;
	private static final int TWO_CHILDREN = 2;

	private int size = 0;

	/**
	 * default constructor
	 */
	public BinarySearchTree() {
		root = null;
	}
	
	/**
	 * 
	 * BinarySearchTreeIterator
	 *
	 */
	private class BinarySearchTreeIterator implements Iterator<Integer> {
		
		private TreeNode minNode;

		private BinarySearchTreeIterator() {
			minNode =  findMin(root); 
		}
		
		public boolean hasNext() {
			if(successor(minNode) != null){
				return true;
			}
			return false;
		}

		public Integer next() {
			minNode = successor(minNode);
			return minNode.getKey();
		}

		public void remove() {
			////// 
			
		}
	}
	
	
	

	/**
	 * Add a new node with key newValue into the tree.
	 * @param newKey new value to add to the tree.
	 * @return false if newValue already exist in the tree.
	 */
	public boolean add(int newKey) {
		TreeNode newNode = new TreeNode(newKey);
		return add(root, newNode);
	}

	/**
	 * 
	 * @param root
	 * @param nodeToAdd
	 * @return
	 */
	private boolean add (TreeNode treeNode, TreeNode nodeToAdd) {

		if (contains(nodeToAdd.getKey()) !=  NOT_FOUND)//the key is  already exists.
			return false;


		if(root == null) {// the tree is empty.
			this.root = nodeToAdd;
			size ++;
			return true;
		}

		if(treeNode.getRightSon() == null && nodeToAdd.getKey() > treeNode.getKey() ) {
			treeNode.setRightSon(nodeToAdd);
			nodeToAdd.setParent(treeNode);
			treeNode.updateHeights();
			size++; 
			return true;

		}

		if(treeNode.getleftSon() == null && nodeToAdd.getKey() < treeNode.getKey()) { 
			treeNode.setLeftSon(nodeToAdd);
			nodeToAdd.setParent(treeNode);
			treeNode.updateHeights();
			size++;
			return true;
		}

		//the tree has two sons, need to add new value to one of them		
		else {
			if(nodeToAdd.getKey() > treeNode.getKey() && treeNode.getRightSon() != null) {
				return add(treeNode.getRightSon() , nodeToAdd);
			}

			if(nodeToAdd.getKey() < treeNode.getKey() && treeNode.getleftSon() != null) {
				return add(treeNode.getleftSon(), nodeToAdd);
			}
		}
		return false;///////not suppose the reach here.
	}




	/**
	 * Does tree contain a given input value.
	 * @param searchVal
	 * @return if val is found in the tree, return the depth of its node (where 0 is the root).
	 * otherwise  - return -1
	 */
	public int contains ( int searchVal)  {
		return contains (root, searchVal, 0);

	}

	private int contains( TreeNode root, int searchVal, int level ) {
		if (root == null) { 
			return NOT_FOUND;
		}

		if (root.getKey() == searchVal) {
			return level;
		}

		else if (root.getKey() > searchVal) {
			return contains(root.getleftSon() , searchVal, level + 1);
		}

		else {
			return contains (root.getRightSon(), searchVal, level + 1);
		}
	}


	/**
	 * 
	 * @param root
	 * @param searchVal
	 * @return
	 */
	private TreeNode findTreeNode ( TreeNode root, int searchVal) {
		if (root == null) { 
			return null;
		}

		if (root.getKey() == searchVal) {
			return root;
		}

		else if (root.getKey() > searchVal) {
			return findTreeNode(root.getleftSon() , searchVal);
		}

		else {
			return findTreeNode (root.getRightSon(), searchVal);
		}
	}
	
	public TreeNode findTreeNode (int searchVal) {
		return findTreeNode(this.root, searchVal );	
	}
	

	/**
	 * Remove a node from the tree if it exists.
	 * @param toDelete value to delete.
	 * @return true if toDelete is found and deleted.
	 */
	public TreeNode delete (int toDelete) {

		if(contains(toDelete) == NOT_FOUND) {
			return null;
		}
		
		return delete(findTreeNode(this.root, toDelete)); 
	}
	
	private TreeNode delete (TreeNode nodeToDelete) {
		if (nodeToDelete == null) {
			return null;
		}
		
		int nodeNumOfChild = howManyChildren(nodeToDelete);

		switch(nodeNumOfChild) {
		
			case NO_CHILDREN : {	
				TreeNode parent = nodeToDelete.getParent();
				if (parent == null)
				{
					root = null;
					size = 0;
				}
				else
				{
					if (parent.getleftSon().getKey() == nodeToDelete.getKey()){
						parent.setLeftSon(null);
					}
					else if (parent.getRightSon().getKey()== nodeToDelete.getKey()){
						parent.setRightSon(null);
					}
					
					size --;
					parent.updateHeights();
				}
				
				return parent;
			}
	
			case ONE_CHILD: {
				TreeNode newNode;
				
				if(nodeToDelete.getRightSon() != null) { //has only right child	
					newNode = nodeToDelete.getRightSon();
					newNode.setParent(nodeToDelete.getParent());
					
					if (newNode.getParent() == null) {
						root = newNode;
					}
					else {
						newNode.getParent().setRightSon(newNode);
					}
					
					nodeToDelete.setRightSon(null);
				}
				else { // has only left child
					newNode = nodeToDelete.getleftSon();
					newNode.setParent(nodeToDelete.getParent());
					
					if (newNode.getParent() == null) {
						root = newNode;
					}
					else {
						newNode.getParent().setLeftSon(newNode);
					}
					
					nodeToDelete.setLeftSon(null);
				}
				
				size--;
				newNode.updateHeights();
				return newNode.getParent();
			}
	
			case TWO_CHILDREN : {
				TreeNode succes = successor(nodeToDelete);
				int temp = succes.getKey();
				nodeToDelete.setKey(temp);
				return delete(succes);
			}
		}
		return null;
	}

	/**
	 * 
	 * @param node
	 * @return
	 */
	private int howManyChildren(TreeNode node) {

		if (node.getleftSon() == null && node.getRightSon() == null) 
			return NO_CHILDREN ;
		if(node.getleftSon() != null && node.getRightSon() != null )  
			return TWO_CHILDREN;
		else
			return ONE_CHILD; 
	}
	/**
	 * 
	 * @param key
	 * @return
	 */
	private TreeNode successor(TreeNode node) {
		if (node == null || root == null) {
			return null;
		}

		if (node.getRightSon() != null) {
			return findMin(node.getRightSon());
		}

		else if (node.getKey() == findMax(root).getKey()) {
			return null;
		}

		else {
			TreeNode parent = node.getParent();

			while(parent != null && parent.getleftSon() != null) {
				parent = parent.getParent();
			}
			return parent;
		}
	}

	/**
	 * 
	 * @param node
	 * @return
	 */
	private TreeNode findMin(TreeNode node) {
		if(root == null) {
			return null;
		}

		while (node.getleftSon() != null) {
			node = node.getleftSon();
		}

		return node;
	}

	/** 
	 * @param node
	 * @return
	 */
	private TreeNode findMax(TreeNode node) {
		if(root == null) {
			return null;
		}

		while (node.getRightSon() != null) {
			node = node.getRightSon();
		}
		return node;
	}

	/**
	 * @return number of nodes in the tree.
	 */
	public int size() {
		return this.size;

	}
	
	/**
	 * 
	 * @param newSize
	 */
	public void setSize(int newSize) {
		this.size = newSize;
	}

	/**
	 * 
	 * @return
	 */
	public TreeNode getRoot() {
		return this.root;
	}
	
	public void setRoot(TreeNode newRoot) {
		this.root = newRoot;
	}
	
	/**
	 * @return
	 */
	public Iterator<Integer>  iterator() {
		BinarySearchTreeIterator binaryTreeIterator = this.new BinarySearchTreeIterator();
		return binaryTreeIterator; 
	}	
}