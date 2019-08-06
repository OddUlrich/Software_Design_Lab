---
title: "Lab 3 - Trees"
summary: Trees
layout: page
---

## Task 1 - Binary Search Tree [0.6 marks]

The main objective of this part is to understand how to insert and delete nodes in a Binary Search Tree (BST). The partial code for this part could be found [here](https://gitlab.cecs.anu.edu.au/u1009226/comp2100-labs/lab3).

 * Go through and read the file `BinaryTree.java`. It is just a simple abstract class for a BST. Go through the implementation of `EmptyBinaryTree.java` and `NonEmptyBinaryTree.java` which inherit `BinaryTree` class.
   * We use a recursive definition of binary search tree in this implementation. A tree consist of 1) root node 2) left sub-tree (which is again a tree), and 3) right sub-tree (which is again a tree).
 * Implement the method `insert()` in `NonEmptyBinaryTree.java`.
 * Implement the method `delete()` in `NonEmptyBinaryTree.java`. If the target node, which we want to delete, has two children, replace the target node its successor in its descendant (See lecture slides for details).
 * Use `BinaryTreeTest.java` to check whether your implementation is correct or not.

Use the provided test code to check the correctness of your implementation. Please be aware that we may use additional test cases to verify of your code. Please implement your code within a block indicated by comments; 'YOUR CODE STARTS HERE' and 'YOUR CODE ENDS HERE' for each task.

## Task 2 - Red-Black Tree [0.4 marks]

Red-Black Tree (R-B Tree) is a special Binary Search Tree (BST). It has all the properties of BST and some extras. For each node of the R-B Tree, it stores a **color**, it should only be red or black. Here are some properties of R-B Trees:

 * Each node should be either black or red.
 * The root node should be black.
 * The null leaf node should be black.
 * For each red node, its children nodes must be black.
 * Each node should have the same number of black nodes from itself to all its children.

The main objective of this part is to implement the insert part of the red-black tree. The code for this part could be found [here](https://gitlab.cecs.anu.edu.au/u1009226/comp2100-labs/lab3). Read the code in 'RedBlackTree.java', implement your `insert()` method and try your code with the 'RedBlackTreeDemo.java'. Note that to complete `insert()` function, you also need to implement `rotateRight()` correctly.

Use the provided test code to check the correctness of your implementation. Please be aware that we may use additional test cases to verify of your code. Please implement your code within a block indicated by comments; 'YOUR CODE STARTS HERE' and 'YOUR CODE ENDS HERE' for each task.

## Submission Guideline

* Assignment deadline: Friday, 16 August 2019, 11:55 PM
* Submission mode: Electronic, via Wattle (Lab 3)
* Submission format (IMPORTANT):
  * Upload your final version of NonEmptyBinaryTree.java (for task 1) and RBTree.java (for task 2) to Wattle.
    * Do not change file name.
    * Do not upload any other files.
  * Do not change the structure of the source code including class name, package structure, etc. You are only allowed to edit the part indicated by comments (See source code files for details).
  * Violation of the submission format will have their assignment not evaluated by an autograder and get zero marks.

Reference: [Introduction to Algorithms (Cormen, Leiserson, Rivest, Stein) Chap. 13](https://cs.anu.edu.au/courses/comp2100/lectures/notes/red-black-tree.pdf).

