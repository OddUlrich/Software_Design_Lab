import java.util.*;

public class BST {
	Node root;

	public Integer leastCommonAncestor(int x, int y) {
		// TODO: Implement this method
		Node curNode = null;
		Node leftNode = null;
		Node rightNode = null;

		if (x == y) {
			return find(x).value;
		} else if (x < y) {
			leftNode = find(x);
			rightNode = find(y);
		} else {
			leftNode = find(y);
			rightNode = find(x);
		}

		if (leftNode.right == rightNode) {
			return leftNode.parent.value;
		} else if (rightNode.left == leftNode) {
			return rightNode.parent.value;
		}

		curNode = leftNode;

		// The least common ancestor must be smaller than the greater one and greater than the smaller one.
		while (curNode.parent != null
				&& curNode.parent.value > leftNode.value
				&& curNode.parent.value < rightNode.value) {
			curNode = curNode.parent;
		}

		return curNode.value; // remove after implementation
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