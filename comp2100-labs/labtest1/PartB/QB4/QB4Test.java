/**
 * Let's define some test cases without JUnit.
 * This will save some time to configure JUnit.
 * 
 * @author dongwookim
 *
 */
public class QB4Test {
    public static void main(String[] args) {
        RBTree<Integer> tree = constructRBTreeManually();
        
        // setColour(true) will change the colour of the node to red
        tree.search(3).setColour(false);
        System.out.println("Test 1-A: " + (!tree.testProp3() ? "PASSED" : "FAILED"));
        tree.search(3).setColour(true);
        System.out.println("Test 1-B: " + (tree.testProp3() ? "PASSED" : "FAILED"));

        // Test properties together
        tree.search(11).setColour(false);
        System.out.println("Test 2-A: " + (!tree.testProp3() ? "PASSED" : "FAILED"));
        tree.search(11).setColour(true);
        System.out.println("Test 2-B: " + (tree.testProp3() ? "PASSED" : "FAILED"));

        // node 7 is the root.
        tree.search(7).setColour(true);
        System.out.println("Test 3-A: " + (tree.testProp3() ? "PASSED" : "FAILED"));
        tree.search(7).setColour(false);
        System.out.println("Test 3-B: " + (tree.testProp3() ? "PASSED" : "FAILED"));
    }
    
    /**
     * This function constructs a valid red-black tree via binary tree insertion.
     * Insertion order is important to keep the valid RBtree structure
     * 
     * @return redblack tree with keys from 0 to 19
     */
	private static RBTree<Integer> constructRBTreeManually() {
		RBTree<Integer> tree = new RBTree<Integer>();
		
		tree.insert(7);
		tree.insert(3);
		tree.insert(1);
		tree.insert(0);
		tree.insert(2);
		tree.insert(5);
		tree.insert(4);
		tree.insert(6);
		tree.insert(11);
		tree.insert(9);
		tree.insert(8);
		tree.insert(10);
		tree.insert(15);
		tree.insert(13);
		tree.insert(12);
		tree.insert(14);
		tree.insert(17);
		tree.insert(16);
		tree.insert(18);
		tree.insert(19);
		
		// adjust colours
        tree.search(3).setColour(true);
        tree.search(11).setColour(true);
        tree.search(13).setColour(true);
        tree.search(17).setColour(true);
        tree.search(19).setColour(true);
		return tree;
	}    
}
