package oop.ex5.data_structures;
import java.util.Iterator;

/**
 * AvlTree
 */
public class AvlTree {	

	private BinarySearchTree binaryTree;
	private static final int NOT_FOUND = -1;

	/**
	 *  A default constructor.
	 */
	public AvlTree() {
		binaryTree = new BinarySearchTree();
	}

	/**
	 * A data constructor -
	 * a constructor that builds the tree by adding the elements in the input array one-by-one.
	 * If the same value appears twice (or more) in the list, it is ignored.
	 * @param data value to add to tree.
	 */
	public AvlTree (int[] data) {
		this();

		for (int i = 0; i < data.length; i++) {
			this.add(data[i]);
		}
	}

	/**
	 * A copy constructor -
	 * a constructor that builds the tree a copy of an existing tree.
	 * @param tree an AvlTree.
	 */
	public  AvlTree (AvlTree tree) {
		this();

		while(tree.binaryTree.iterator().hasNext()){
			this.add(tree.binaryTree.iterator().next());
		}
	}


	/**
	 * Add a new node with key newValue into the tree.
	 * @param newValue new value to add to the tree.
	 * @return false if newValue already exist in the tree.
	 */
	public boolean add (int newValue) {
		if(this.contains(newValue) != NOT_FOUND) {
			return false;
		}
		binaryTree.add(newValue);
		fixViolations(binaryTree.findTreeNode( newValue));
		return true;
	}

	/**
	 * Does tree contain a given input value.
	 * @param searchVal
	 * @return if val is found in the tree, return the depth of its node (where 0 is the root).
	 * otherwise  - return -1
	 */
	public int contains (int searchVal) {
		return binaryTree.contains(searchVal);
	}

	/**
	 * Remove a node from the tree if it exists.
	 * @param toDelete value to delete.
	 * @return true if toDelete is found and deleted.
	 */
	public boolean delete (int toDelete) {
		if(this.contains(toDelete) == NOT_FOUND) {
			return false;	
		}
		
		TreeNode parentOfDeleted = this.binaryTree.delete(toDelete);
		
		fixViolations(parentOfDeleted);
		/* Note that if we deleted the root node (parent = null), we won't have to fix any
		 * violations. This is because if it had no children, then there's nothing to fix, and if it
		 * had a single child, that child doesn't have any children (since it's an AVL)
		 * then that child would become the new root without any balancing needed.
		 */
		
		return true;
	}

	/**
	 * @return number of nodes in the tree.
	 */
	public int size() {
		return binaryTree.size();
	}


	private void fixViolations (TreeNode startFrom) {
		if(startFrom == null) {
			return;
		}

		TreeNode parent = startFrom.getParent();  
		if(startFrom.getRightSonHeight() - startFrom.getLeftSonHeight() > 1 
				|| startFrom.getRightSonHeight() - startFrom.getLeftSonHeight() <-1 ) {
			balance(startFrom, null, null);
		}
		fixViolations(parent);
	}

	/**
	 * @return iterator to the AVL Tree. The returned iterator can pass over the tree nodes in ascending (from min to max) order.
	 */
	public Iterator <Integer> iterator() {
		return binaryTree.iterator(); 
	}


	/**
	 * This method calculates the minimum number of nodes in an AVL tree of height h,
	 * @param h height of the tree (a non-negative number)
	 * @return minimum number of nodes in the tree.
	 */
	public static int findMinNodes (int h) {
		if(h == 0)
			return 1;
		return(findMinNodes( h - 1 ) + findMinNodes( h - 2 ) + 1 );
	}


	private int balance(TreeNode current, TreeNode first_violation, TreeNode second_violation){
		TreeNode first = first_violation;
		TreeNode second = second_violation;

		if(first == null){
			if(current.getleftSon() == null){
				return(balance(current, current.getRightSon(), null));
			}
			else if(current.getRightSon() == null){
				return (balance(current, current.getleftSon(), null));
			}
			else{
				if(current.getleftSon().getHeight() - current.getRightSon().getHeight() > 1){
					return (balance(current, current.getleftSon(), null));
				}
				else if(current.getleftSon().getHeight() - current.getRightSon().getHeight() < -1){
					return (balance(current, current.getRightSon(), null));
				}
				else{
					if(current.getleftSon().getHeight() > current.getRightSon().getHeight()){
						return (balance(current, current.getleftSon(), null));
					}
					else{
						return (balance(current, current.getRightSon(), null));
					}
				}
			}
		}
		else if(second == null){
			if(first.getleftSon() == null){
				return(balance(current, first, first.getRightSon()));
			}
			else if(first.getRightSon() == null){
				return(balance(current, first, first.getleftSon()));
			}
			else{
				if(first.getleftSon().getHeight() - first.getRightSon().getHeight() > 1){
					return (balance(current,first, first.getleftSon()));
				}
				else if(first.getleftSon().getHeight() - first.getRightSon().getHeight() < -1){
					return (balance(current,first, first.getRightSon()));
				}
				else{
					if(first.getleftSon().getHeight() > first.getRightSon().getHeight()){
						return (balance(current, first, first.getleftSon()));
					}
					else{
						return (balance(current, first, first.getRightSon()));
					}
				}
			}
		}
		else{
			rotation(current, first, second);
			return 0;
		}
	}

	private static boolean compareNodes(TreeNode node1, TreeNode node2)
	{
		if (node1 == null && node2 == null) {
			return true;
		}
		else if (node1 == null || node2 == null) {
			return false;
		}

		return (node1.getKey() == node2.getKey());
	}

	private void rotation(TreeNode current, TreeNode first, TreeNode second){

		if (current.getHeight() < 2) {
			return;
		}


		////my try LL///////////////////////////////////////////////////////
		if(compareNodes(current.getleftSon(), first) && compareNodes(first.getleftSon(), second)) {
			current.setLeftSon(first.getRightSon());
			if (current.getleftSon() != null) {
				current.getleftSon().setParent(current);
			}

			first.setParent(current.getParent());
			if (first.getParent() == null) {
				binaryTree.setRoot(first);
			}
			else {
				TreeNode parent = first.getParent();

				if (compareNodes(parent.getleftSon(), current)) {
					parent.setLeftSon(first);
				}
				else {
					parent.setRightSon(first);
				}
			}

			first.setRightSon(current);
			current.setParent(first);

			//updates lower nodes first.
			second.updateHeights();
			current.updateHeights();
		}
		else if(compareNodes(current.getRightSon(), first) && compareNodes(first.getleftSon(), second)) {
			//my try RL////////////////////////////////
			first.setLeftSon(second.getRightSon());
			if (first.getleftSon() != null) {
				first.getleftSon().setParent(first);
			}

			current.setRightSon(second);
			second.setParent(current);
			second.setRightSon(first);
			first.setParent(second);

			first.updateHeights();
			rotation(current, second, first);//// second RR

		}
		else if(compareNodes(current.getRightSon(), first) && compareNodes(first.getRightSon(), second)) {
			//////////RR////////////////
			current.setRightSon(first.getleftSon());
			if (current.getRightSon() != null) {
				current.getRightSon().setParent(current);
			}

			first.setParent(current.getParent());
			if (first.getParent() == null) {
				binaryTree.setRoot(first);
			}
			else {
				TreeNode parent = first.getParent();

				if (compareNodes(parent.getleftSon(), current)) {
					parent.setLeftSon(first);
				}
				else {
					parent.setRightSon(first);
				}
			}

			first.setLeftSon(current);
			current.setParent(first);

			current.updateHeights();
			second.updateHeights();			
		}
		else
		{
			///////////LR////////
			first.setRightSon(second.getleftSon());
			if (first.getRightSon() != null) {
				first.getRightSon().setParent(first);
			}

			second.setLeftSon(first);
			first.setParent(second);
			current.setLeftSon(second);
			second.setParent(current);

			first.updateHeights();
			rotation(current, second, first);
		}		
	}
}
