import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BinaryTreeTestForMarking {
	BinaryTree<Integer> tree;
	
	@Before
    public void beforeEachTestMethod() {
	    tree = new NonEmptyBinaryTree<Integer>(8);
	    tree = tree.insert(4)
	    	.insert(2)
	    	.insert(6)
	    	.insert(5)
	    	.insert(12)
	    	.insert(11)
	    	.insert(16);
    }
	
	@Test(timeout=1000)
	public void testInsert() {
        Assert.assertEquals("8 4 2 6 5 12 11 16", tree.preOrderShow());
	}

	@Test(timeout=1000)
	public void testRemoveNodeWithNoChild() {
        Assert.assertEquals("8 4 2 6 5 12 11", tree.delete(16).preOrderShow());
	}
	
	@Test(timeout=1000)
	public void testRemoveNodeWithOneChild() {
        Assert.assertEquals("8 4 2 5 12 11 16", tree.delete(6).preOrderShow());
	}
	
	@Test(timeout=1000)
	public void testRemoveNodeWithTwoChildren() {
		String result = tree.delete(4).preOrderShow();
		
	    Assert.assertEquals("8 5 2 6 12 11 16", result);
	}
	
	@Test(timeout=1000)
	public void testRemoveRootNode() {
		String result = tree.delete(8).preOrderShow();
		
		Assert.assertEquals("11 4 2 6 5 12 16", result);	
	}
}
