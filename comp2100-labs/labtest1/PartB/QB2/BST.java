import java.util.*;

public class BST {
	Node root;

	public Integer commonAncestorRecurse(Node cur, int min, int max) {
		if (cur == null) {
			return 0;
		}

		if (cur.value.compareTo(min) < 0 && cur.value.compareTo(max) < 0) {
			// both min and max are in the right subtree.
			return commonAncestorRecurse(cur.right, min, max);

		} else if (cur.value.compareTo(min) > 0 && cur.value.compareTo(max) > 0) {
			// both min and max are in the left subtree.
			return commonAncestorRecurse(cur.left, min, max);

		} else if (cur.value.compareTo(min) == 0 || cur.value.compareTo(max) == 0) {
			// cur.value == min or max.
			return cur.parent.value;
		}

		// min is in left subtree, max is in right subtree.
		return cur.value;

	}

	public Integer leastCommonAncestor(int x, int y) {
		// TODO: Implement this method
		if (x > y) {
			return commonAncestorRecurse(root, x, y);
		} else {
			return commonAncestorRecurse(root, y, x);
		}
	}
	
	public class Node {
		Integer value;
		Node parent;
		Node left;
		Node right;

		public Node(int value) {
			this.value = value;
			this.parent = null;
			this.left = null;
			this.right = null;
		}
	}

	public BST() {
		root = null;
	}

	public Node find(int value) {
		Node current = root;
		while(current != null) {
			if(current.value.compareTo(value) < 0) {
				current = current.right;
			}else if (current.value.compareTo(value) > 0){
				current = current.left;
			}else if(current.value.compareTo(value) == 0) {
				return current;
			}
		}
		return null;
	}
	
	/**
	 * This implementation of insert follows the pseudo code in the lecture slide.
	 * Node that we did not use recursion here.
	 * 
	 * @param value value of inserted node
	 * @return inserted node (not the entire tree)
	 */
	public Node insert(int value) {
		Node parent = null;
		Node current = root;
		while (current != null) {
			if(current.value.compareTo(value) < 0) {
				parent = current;
				current = current.right;
			}else if (current.value.compareTo(value) > 0){
				parent = current;
				current = current.left;
			}
		}
		
		if (parent == null && current == null) {
			root = new Node(value);  // no parent = root is empty
			return root;
		}else {
			Node newNode = new Node(value);
			
			if(parent.value.compareTo(value) < 0) {
				parent.right = newNode;
				newNode.parent = parent;
			}else if(parent.value.compareTo(value) > 0) {
				parent.left = newNode;
				newNode.parent = parent;
			}
			return newNode;
		}
	}
}