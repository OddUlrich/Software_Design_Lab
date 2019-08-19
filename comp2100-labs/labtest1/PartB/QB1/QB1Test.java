/**
 * Let's define some test cases without JUnit.
 * This will save some time to configure JUnit.
 * 
 * @author dongwookim
 *
 */
public class QB1Test {
	public static void main(String[] args) {
		BST tree1 = new BST();
		BST tree2 = new BST();
		BST tree3 = new BST();
		BST tree4 = new BST();


		for (int i = 1; i < 10; i++)
			tree1.insert(i);

		Integer[] tree2vals = {5, 2, 1, 7, 6, 8, 10};
		for(Integer i :tree2vals) 
			tree2.insert(i);

		Integer[] tree3vals = {30, 23, 1, 42, 6, 51, 37};
		for(Integer i :tree3vals) 
			tree3.insert(i);

		tree4.insert(7);

		System.out.println("Test 1: " + (tree1.oddNodeSum() == 36 ? "PASS" : "FAIL"));
		System.out.println("Test 2: " + (tree2.oddNodeSum() == 10 ? "PASS" : "FAIL"));
		System.out.println("Test 3: " + (tree3.oddNodeSum() == 24 ? "PASS" : "FAIL"));
		System.out.println("Test 4: " + (tree4.oddNodeSum() == 0 ? "PASS" : "FAIL"));

	}
}