public class BST {
	Node root;


	private Integer nextOddSum(Node parent) {
		// Parent node must be not null;

		if (parent.left != null) {
			if (parent.right != null) {
				return nextOddSum(parent.left) + nextOddSum(parent.right);
			} else { // parent.right == null
				return parent.value + nextOddSum(parent.left);
			}
		} else {  // parent.left == null
			if (parent.right != null) {
				return parent.value + nextOddSum(parent.right);
			} else {  // parent.right == null
				return 0;
			}
		}
	}

	public Integer oddNodeSum() {
		//TODO: Implement this function
		Integer sum = 0;

		if (root == null) {
			return 0;
		}

		Node curNode = root;
		sum += nextOddSum(curNode);

		return sum;
	}
	
	public class Node {
		Integer value;
		Node parent;
		Node left;
		Node right;

		public Node(Integer value) {
			this.value = value;
			this.parent = null;
			this.left = null;
			this.right = null;
		}
	}

	public BST() {
		root = null;
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