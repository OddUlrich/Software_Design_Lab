
/**
 * NonEmptyBinaryTree - this is a binary search tree that is either an inner node
 * of the tree or a leaf node.  Leaf nodes will have 2 empty nodes attached to 
 * the right and the left subtrees.  Note that the insert and remove operation return 
 * the changed tree and they will generally modify existing trees. 
 * 
 * 
 * @author Eric McCreath - GPLv2
 * @author Huy Pham - Modified to remove unused methods for the purpose of the lab exercise.
 * @author dongwookim - add javadocs
 */


public class NonEmptyBinaryTree <T extends Comparable<T>> extends BinaryTree <T> {

	T data;		// data of the root node of this tree
	BinaryTree<T> left, right;	// left and right sub-trees
	
	/**
	 * Create a NonEmptyBinaryTree tree with root node.
	 * The tree will not have sub-trees.
	 * 
	 * @param data the data of the root node.
	 */
	public NonEmptyBinaryTree(T data) {
		this.data = data;
		left = new EmptyBinaryTree<T>();
		right = new EmptyBinaryTree<T>();
	}
	
	/** 
	 * Create a NonEmptyBinaryTree with left and right sub-trees
	 * @param data value of the root node
	 * @param left left sub-tree of the new NonEmptyBinaryTree tree
	 * @param right right sub-tree of the new NonEmptyBinaryTree tree
	 */
	public NonEmptyBinaryTree(T data, BinaryTree<T> left, BinaryTree<T> right) {
		this.data = data;
		this.left = left;
		this.right = right;
	}
	
	/**
	 * Insert a new node whose value is d into the existing tree.
	 * This function should return the binary tree with d inserted.
	 * If the tree has already node with d, do not create a new node 
	 * and return the original tree.
	 * 
	 * Hint: You can implement insert function recursively. 
	 * (Each subtree (left or right) is a tree which has insert function)
	 * 
	 * @param d data of the new node
	 * @return BinaryTree<T> 
	 */
	public BinaryTree<T> insert(T d) {
		// TODO: Add your implementation here
		// ########## YOUR CODE STARTS HERE ##########
		if (find(d)) {
			return this;
		}

		// Find the leaf position to insert this node.
		if (d.compareTo(data) < 0) {
			left = left.insert(d);  // If left child is empty, it returns a new non-empty node.
		} else {  // d.compareTo(data) > 0
			right = right.insert(d);  // If right child is empty, it returns a new non-empty node.
		}

		return this;
		// ########## YOUR CODE ENDS HERE ##########
	}
	
	/**
	 * Returns the size of the tree (number of nodes)
	 */
	public int size() {
		return 1 + left.size() + right.size();
	}

	/**
	 * Compute the height of {@code this} tree
	 */
	@Override
	public int height() {
		return 1 + Math.max(left.height(), right.height());
	}

	/**
	 * Preorder traversing 
	 */
	@Override
	public String preOrderShow() {
		if (left.isEmpty() && right.isEmpty()) return data + "";
		else {
			String leftStr = left.preOrderShow();
			String rightStr = right.preOrderShow();
			return data + (leftStr.isEmpty() ? leftStr : " " + leftStr) + (rightStr.isEmpty() ? rightStr : " " + rightStr);
		}
	}


	/**
	 * Remove node with data {@code d}
	 * This function should return the binary tree with d removed.
	 * If the tree don't have a node with d, return the original tree.
	 * 
	 * Hint: You can implement insert function recursively. 
	 * (Each subtree (left or right) is a tree which has delete function)
	 * 
	 * @param d value of the node that should be removed
	 * @return BinaryTree<T> The binary tree without d.
	 */
	@Override
	public BinaryTree<T> delete(T d) {
		// TODO: Add your implementation here
		// ########## YOUR CODE STARTS HERE ##########
		if (!find(d)) {
			return this;
		}

		if (d.compareTo(data) < 0) {
			left = left.delete(d);
		} else if (d.compareTo(data) > 0) {
			right = right.delete(d);
		} else { //d.compareTo(data) == 0
			// Case 00: have no child.
			if (size() == 1) {
				return new EmptyBinaryTree<T>();
			} else {
				// Case 01: have one child.
				if (left.isEmpty()) {
					return right;
				}
				else if (right.isEmpty()) {
					return left;
				}
				else {
					// Case 02: have two children, replaced with successor.
					T successor = right.smallest();
					right.delete(successor);
					NonEmptyBinaryTree<T> sucNode = new NonEmptyBinaryTree<T>(successor);
					sucNode.left = this.left;
					sucNode.right = this.right;
					return sucNode;
				}
			}
		}

		return this;
		// ########## YOUR CODE ENDS HERE ##########
	}

	/**
	 * NonEmptyBinaryTree is by definition non-empty. It will return false always.
	 */
	@Override
	public boolean isEmpty() {
		return false;
	}

	/**
	 * This function will find the biggest node in a tree.
	 */
	@Override
	public T biggest() {
        if (right.isEmpty()) return data;
        else return right.biggest();
	}

	/**
	 * This function will find the smallest node in a tree.
	 */
	@Override
	public T smallest() {
		if (left.isEmpty()) return data;
		else return left.smallest();
	}

	/**
	 * Helper functions for visualizing tree.
	 */
	@Override
	public String treeshow() {
		if (left.isEmpty() && right.isEmpty()) return " " + data + " ";
		String stl = left.treeshow();
		String str = right.treeshow();
		String stal[] = stl.split("\n");
		String star[] = str.split("\n");
		int lenl = stal[0].length();
		int lenr = star[0].length();
		StringBuffer res = new StringBuffer();
		String strdata = data + "";
		int fullsize = lenl + strdata.length() + lenr;
		
	
		res.append(repeat(" ", (lenl)) + strdata + repeat(" ", lenr ) + "\n");
		int lcentre = (left.isEmpty() ? 0 : centre(stal[0]));
		int rcentre = (right.isEmpty() ? 0 :centre(star[0]));
		
		res.append(repeat(" ",lcentre) + "+" + repeat("-",lenl-lcentre-1) + "+" + repeat("-",rcentre-1+strdata.length()) +  "+" + repeat(" ",lenr-rcentre -1) + "\n");
		res.append(repeat(" ",lcentre) + (left.isEmpty()? " " : "|") + repeat(" ",lenl-lcentre-1) +  repeat(" ",rcentre + strdata.length()) +  (right.isEmpty()? " " : "|") + repeat(" ",lenr-rcentre-1) +"\n");
		
		for(int i = 0;i<Math.max(stal.length, star.length);i++) {
			res.append( (i<stal.length ? stal[i] : repeat(" ", lenl)) +repeat(" ",strdata.length()) +  (i<star.length? star[i] : repeat(" ", lenr)) + "\n");
		}
		return res.toString();
	}

	protected int centre(String string) {
		int i = 0;
		while (i < string.length() && string.charAt(i) == ' ') i++;
		return i;
	}

	protected String repeat(String string, int n) {
		String res = "";
		for (int i = 0; i<n;i++) res += string;
		return res;
	}

	/**
	 * Find whether a specific data is in the tree or not.
	 */
	@Override
	public boolean find(T d) {
		if (data == d) return true;
		else if (d.compareTo(data) < 0) return left.find(d);
		else return right.find(d);
	}
}
