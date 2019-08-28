import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;


/**
 * IMPORTANT: "RBTree<Integer>.Node<Integer>" looks ugly. This is due to the fact that at that time we had sent
 * the source code including {@link RBTree} to students. Ideally, those methods should be moved to {@link RBTree}.
 * 
 * @author huy.pham
 */
public class RBTreeTestForMarking {
    // Check if root and every leaf is black.
    private boolean rootLeafCheck(RBTree<Integer>.Node<Integer> root) {
        return root.colour == RBTree.Colour.BLACK && leafCheck(root.l) && leafCheck(root.r);
    }

    // Helper method for rootLeafCheck()
    private boolean leafCheck(RBTree<Integer>.Node<Integer> node) {
        return node.value == null ? node.colour == RBTree.Colour.BLACK : leafCheck(node.l) && leafCheck(node.r);
    }

    // Check if a node is red, then boths its children must be black.
    private boolean redParentCheck(RBTree<Integer>.Node<Integer> root) {
        return redNodeCheck(root.l) && redNodeCheck(root.r);
    }

    // Helper method for redParentCheck()
    private boolean redNodeCheck(RBTree<Integer>.Node<Integer> node) {
        if (node.colour == RBTree.Colour.RED && node.parent.colour == RBTree.Colour.RED) {
            return false;
        }

        return node.value == null ? true : redNodeCheck(node.l) && redNodeCheck(node.r);
    }
    
    // Check if every simple path from a node to a descendant leaf contains the same number of black nodes.
    private boolean checkEqualNoOfBlackNodesForEveryLeaf(RBTree<Integer>.Node<Integer> root) {
        // TODO
    	return false;
    }

	@Test
	public void testGivenScenario() {
        RBTree<Integer> tree = new RBTree<Integer>();
        for(int i=0; i<20; ++i) {
            tree.insert(i);
        }

        Assert.assertEquals("7 3 1 0 2 5 4 6 11 9 8 10 15 13 12 14 17 16 18 19", tree.preOrder());
        Assert.assertTrue(tree.search(17) != null);
        Assert.assertTrue(tree.search(5) != null);
        Assert.assertTrue(tree.search(-3) == null);
        Assert.assertTrue(tree.search(26) == null);
	}
    
	@Test
	public void testGivenScenarioRedBlackTreeProperties() {
        RBTree<Integer> tree = new RBTree<Integer>();
        for(int i=0; i<20; ++i) {
            tree.insert(i);
        }

        Assert.assertTrue(rootLeafCheck(tree.search(7)));
        Assert.assertTrue(redParentCheck(tree.search(7)));
	}
	
	@Test
	public void testCase2RotateLeft() {
        RBTree<Integer> tree = new RBTree<Integer>();
        
        // Construct a valid tree.
        tree.insert(8);
        tree.insert(10);
        
        tree.insert(3);
        tree.search(10).colour = RBTree.Colour.BLACK;
        
        tree.insert(5);
       
        Assert.assertTrue(rootLeafCheck(tree.search(5)));
        Assert.assertTrue(redParentCheck(tree.search(5)));
	}
	
	@Test
	public void testCase2RotateRight() {
        RBTree<Integer> tree = new RBTree<Integer>();
        
        // Construct a valid tree.
        tree.insert(8);
        tree.insert(3);
        tree.insert(10);
        tree.search(3).colour = RBTree.Colour.BLACK;
        
        tree.insert(9);
        
        Assert.assertTrue(rootLeafCheck(tree.search(9)));
        Assert.assertTrue(redParentCheck(tree.search(9)));
	}
}
